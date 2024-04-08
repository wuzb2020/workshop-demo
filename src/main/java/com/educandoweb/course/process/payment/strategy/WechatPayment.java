package com.educandoweb.course.process.payment.strategy;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.process.payment.result.PayResult;
import lombok.extern.slf4j.Slf4j;

/**
 * 微信付款
 */
@Slf4j
public class WechatPayment extends PaymentStrategy{

    @Override
   public PayResult pay(Order order) {
        log.info("微信支付成功");
        return PayResult.success("微信支付成功");
    }
}
