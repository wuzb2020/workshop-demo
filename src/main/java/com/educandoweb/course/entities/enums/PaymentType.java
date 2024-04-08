package com.educandoweb.course.entities.enums;

import com.educandoweb.course.process.payment.strategy.AlipayPayment;
import com.educandoweb.course.process.payment.strategy.FlashPayment;
import com.educandoweb.course.process.payment.strategy.PaymentStrategy;
import com.educandoweb.course.process.payment.strategy.WechatPayment;

public enum PaymentType {
    Alipay("支付宝支付", AlipayPayment.class),
    Wechat("微信支付", WechatPayment.class),
    Flash("云闪付支付", FlashPayment.class);


    private String name;
    private Class<? extends PaymentStrategy> strategy;

    PaymentType(String name, Class<? extends PaymentStrategy> strategy) {
        this.name = name;
        this.strategy = strategy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getStrategy() {
        return strategy;
    }

    public void setStrategy(Class strategy) {
        this.strategy = strategy;
    }
}
