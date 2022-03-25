package com.jiashn.springsecurity.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: jiangjs
 * @Description:
 * @Date: 2022/3/19 15:35
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult<T> {
    private int code;
    private String msg;
    private T data;

    public static <T> JsonResult<T> success(T data){
        return JsonResult.<T>builder()
                .code(JsonResultEnum.SUCCESS.getCode())
                .msg(JsonResultEnum.SUCCESS.getMsg())
                .data(data)
                .build();
    }

    public static <T> JsonResult<T> fail(){
        return JsonResult.<T>builder()
                .code(JsonResultEnum.FAILURE.getCode())
                .msg(JsonResultEnum.FAILURE.getMsg())
                .data(null)
                .build();
    }

    public static <T> JsonResult<T> fail(int code,String msg){
        return JsonResult.<T>builder()
                .code(code)
                .msg(msg)
                .data(null)
                .build();
    }
}