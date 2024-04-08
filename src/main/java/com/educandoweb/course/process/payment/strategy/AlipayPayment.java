package com.educandoweb.course.process.payment.strategy;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.process.payment.result.PayResult;
import lombok.extern.slf4j.Slf4j;

/**
 * 支付宝付款
 */
@Slf4j
public class AlipayPayment extends PaymentStrategy {
    @Override
    public PayResult pay(Order order) {
        log.info("支付宝支付成功");
        return PayResult.success("支付宝支付成功");
    }
}
