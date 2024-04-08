package com.educandoweb.course.process.payment.strategy;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.process.payment.result.PayResult;

/**
 * 支付抽象类
 */
public abstract class PaymentStrategy {
    public abstract PayResult pay(Order order);
}
