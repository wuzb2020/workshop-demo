package com.educandoweb.course.process.payment.result;

import lombok.Data;

@Data
public class PayResult {
    private Boolean success;
    private String message;

    private PayResult(){

    }

    public static PayResult success(String message) {
        PayResult payResult = new PayResult();
        payResult.success = true;
        payResult.message = message;
        return payResult;
    }

    public static PayResult fail(String message) {
        PayResult payResult = new PayResult();
        payResult.success = false;
        payResult.message = message;
        return payResult;
    }
}
