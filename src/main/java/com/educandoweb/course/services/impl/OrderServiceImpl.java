package com.educandoweb.course.services.impl;

import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educandoweb.course.dto.OrderPageDTO;
import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.Sku;
import com.educandoweb.course.entities.enums.OrderStatus;
import com.educandoweb.course.entities.enums.PaymentType;
import com.educandoweb.course.entities.enums.SkuStatus;
import com.educandoweb.course.exception.OrderException;
import com.educandoweb.course.mapper.OrderMapper;
import com.educandoweb.course.process.payment.context.PaymentContext;
import com.educandoweb.course.process.payment.result.PayResult;
import com.educandoweb.course.process.payment.strategy.PaymentStrategy;
import com.educandoweb.course.services.OrderService;
import com.educandoweb.course.services.SkuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper mapper;
    @Autowired
    private SkuService skuService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 初始化数据库中的sku库存
     */
    @PostConstruct
    public void initRedisStock() {
        LambdaQueryWrapper<Sku> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Sku::getSkuStatus, SkuStatus.LISTING.getCode());//查询上架的商品

        List<Sku> all = skuService.findAll(wrapper);
        //当前库存存入redis
        all.forEach(
                sku -> redisTemplate.opsForValue().set(sku.getRedisKey(),
                        Optional.ofNullable(sku.getStock()).map(Object::toString).orElse("0"))
        );
    }

    @Override
    public Order findById(Long id) {
        Order obj = mapper.selectById(id);
        return obj;
    }

    @Override
    public IPage<Order> gePage(OrderPageDTO orderPageDTO) {
        Page page = new Page();
        page.setCurrent(orderPageDTO.getPage());
        page.setSize(orderPageDTO.getSize());

        LambdaQueryWrapper<Order> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Order::getUserId, orderPageDTO.getUserId());
        wrapper.gt(Objects.nonNull(orderPageDTO.getStartTime()), Order::getMoment, orderPageDTO.getStartTime());
        wrapper.lt(Objects.nonNull(orderPageDTO.getEndTime()), Order::getMoment, orderPageDTO.getEndTime());

        IPage<Order> iPage = mapper.selectPage(page, wrapper);
        return iPage;
    }

    @Transactional
    @Override
    public Order save(Long skuId, PaymentType type) {

        //1.获取秒杀的sku
        Sku sku = skuService.findById(skuId);

        //2.执行秒杀
        performSecondKill(sku);

        Order order = null;
        try {
            //3.生成订单
            order = createOrder(sku);

            //4.支付订单
            pay(order, sku, type);
        } catch (Exception e) {
            redisTemplate.opsForValue().increment(sku.getRedisKey());//操作失败，库存回退
            throw e;
        }
        return order;
    }

    private Order createOrder(Sku sku) {
        Order order = new Order();
        order.setSkuId(sku.getId());
        order.setOrderStatus(OrderStatus.PAID.getCode());
        order.setMoment(new Date());
        order.setPrice(sku.getPrice());
        order.setUserId(1L);//正常情况下用户id是通过session或threadLoacal获取的，demo直接写死1
        mapper.insert(order);
        return order;
    }

    /**
     * 扣减库存
     */
    private synchronized void performSecondKill(Sku sku) {

        //检验重复下单
        checkCanOrder(sku.getId());

        // 使用Redis原子操作来减少库存
        Long stock = redisTemplate.opsForValue().decrement(sku.getRedisKey());
        if (stock < 0) {
            // 如果库存减少后小于0，表示已经卖完，实际生产中需考虑回写数据库
            redisTemplate.opsForValue().increment(sku.getRedisKey()); // 库存恢复
            throw new OrderException(OrderException.ExceptionType.NOSTOCK);
        }

    }

    /**
     * 判断是否可以下单，如果已下单返回false
     * 防止60秒内重复下单
     * @return
     */
    private void checkCanOrder(Long skuId) {

        Boolean success = redisTemplate.opsForValue().setIfAbsent("userSecondKill:" + 1, skuId.toString(),60,TimeUnit.SECONDS);//正常情况下用户id是通过session或threadLoacal获取的，demo直接写死1

        if (!success) {//重复下单直接抛异常
            throw new OrderException(OrderException.ExceptionType.REORDER);
        }
    }


    /**
     * 2.【20分】支付回调需要优雅的使用策略模式(支付成功、支付失败、重复支付)
     *
     * @param order
     * @param type
     */
    private void pay(Order order, Sku sku, PaymentType type) {
        //获取支付方式
        PaymentStrategy paymentStrategy = getPaymentStrategy(type.getStrategy());

        //执行支付操作
        PaymentContext context = new PaymentContext(paymentStrategy);
        PayResult result = context.pay(order);

        //支付失败抛异常到前端，让前端处理
        if (!result.getSuccess()) {

            redisTemplate.opsForValue().decrement(sku.getRedisKey());//支付失败库存回退
            //这里简单的抛个异常到前端，实际项目中应该是定义特定的异常

            throw new OrderException(OrderException.ExceptionType.PAYFAIL);
        }
    }

    private PaymentStrategy getPaymentStrategy(Class<? extends PaymentStrategy> cls) {
        PaymentStrategy paymentStrategy = ReflectUtil.newInstance(cls);
        return paymentStrategy;
    }
}
