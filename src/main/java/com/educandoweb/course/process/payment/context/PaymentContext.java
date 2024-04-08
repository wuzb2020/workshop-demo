package com.educandoweb.course.process.payment.context;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.process.payment.result.PayResult;
import com.educandoweb.course.process.payment.strategy.PaymentStrategy;

public class PaymentContext {
    private PaymentStrategy strategy;

    public PaymentContext(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public PayResult pay(Order order) {
       return  strategy.pay(order);
    }
}
