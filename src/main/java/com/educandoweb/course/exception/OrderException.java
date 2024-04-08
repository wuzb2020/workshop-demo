package com.educandoweb.course.exception;

public class OrderException extends RuntimeException {

    public OrderException(ExceptionType type) {
        super(type.getMsg());
    }

    public enum ExceptionType {
        REORDER("重复下单"),
        PAYFAIL("支付失败"),
        NOSTOCK("无库存");

        private String msg;

        ExceptionType(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

}
