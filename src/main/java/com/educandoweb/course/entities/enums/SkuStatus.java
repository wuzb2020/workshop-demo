package com.educandoweb.course.entities.enums;

public enum SkuStatus    {
	LISTING(1),//上架
    UNLISTED(2);//下架
    private int code;

    private SkuStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
