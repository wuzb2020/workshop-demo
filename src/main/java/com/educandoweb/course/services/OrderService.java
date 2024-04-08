package com.educandoweb.course.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.educandoweb.course.dto.OrderPageDTO;
import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.enums.PaymentType;

public interface OrderService {
    Order findById(Long id);

    Order save(Long skuId, PaymentType type);

    IPage<Order> gePage(OrderPageDTO orderPageDTO);
}
