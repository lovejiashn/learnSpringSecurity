package com.jiashn.springsecurity.utils;

import lombok.Data;

/**
 * @author jiangjs
 */
public enum JsonResultEnum {
    /**
     * 成功
     */
    SUCCESS(2000,"成功"),
    /**
     * 失败
     */
    FAILURE(5000,"失败"),
    /**
     * 未找到服务
     */
    NOT_FIND(4004,"未找到服务");

    private final int code;
    private final String msg;

    JsonResultEnum(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
