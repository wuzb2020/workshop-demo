package com.educandoweb.course.process.payment.strategy;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.process.payment.result.PayResult;
import lombok.extern.slf4j.Slf4j;

/**
 * 云闪付付款
 */
@Slf4j
public class FlashPayment extends PaymentStrategy{


    @Override
   public PayResult pay(Order order) {
        log.info("云闪付支付失败，余额不足");
        return PayResult.fail("云闪付支付失败，余额不足");
    }
}
