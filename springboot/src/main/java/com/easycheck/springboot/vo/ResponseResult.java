package com.easycheck.springboot.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseResult<T> {

    private Integer code;//业务状态码  200-成功  1-失败
    private String message;//提示信息

    private T data;//响应数据

    public static <T> ResponseResult<T> success(String message) {
        return ResponseResult.<T>builder().code(200).message(message).build();
    }

    public static <T> ResponseResult<T> success(String message, T data) {
        return ResponseResult.<T>builder().code(200).message(message).data(data).build();
    }
    public static <T> ResponseResult<T> success(Integer code,String message, T data) {
        return ResponseResult.<T>builder().code(code).message(message).data(data).build();
    }

    public static <T> ResponseResult<T> fail(String message) {
        return ResponseResult.<T>builder().code(500).message(message).build();
    }

    public static <T> ResponseResult<T> fail(String message, T data) {
        return ResponseResult.<T>builder().code(500).message(message).data(data).build();
    }

}
