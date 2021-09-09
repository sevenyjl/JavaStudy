package com.seven.nacos.common;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

@Data
@Accessors(chain = true)
public class ApiResult<T> {
    private int code;
    private String msg;
    private T data;


    private ApiResult(HttpStatus httpStatus, String msg, T data) {
        this.code = httpStatus.value();
        this.msg = msg;
        this.data = data;
    }

    public static ApiResult success() {
        return new ApiResult(HttpStatus.OK, "操作成功", new HashMap<>());
    }

    public static ApiResult success(String msg) {
        return new ApiResult(HttpStatus.OK, msg, new HashMap<>());
    }


    public static <F> ApiResult<F> success(String msg, F data) {
        return new ApiResult<F>(HttpStatus.OK, msg, data);
    }

    public static <F> ApiResult<F> success(F data) {
        return new ApiResult<F>(HttpStatus.OK, "操作成功", data);
    }

    public static ApiResult error() {
        return new ApiResult(HttpStatus.INTERNAL_SERVER_ERROR, "操作失败", new HashMap<>());
    }

    public static ApiResult error(HttpStatus httpStatus) {
        return new ApiResult(httpStatus, "操作失败", new HashMap<>());
    }

    public static ApiResult error(String msg) {
        return new ApiResult(HttpStatus.INTERNAL_SERVER_ERROR, msg, new HashMap<>());
    }

    public static ApiResult error(HttpStatus httpStatus, String msg) {
        return new ApiResult(httpStatus, msg, new HashMap<>());
    }

    public static <F> ApiResult<F> error(String msg, F data) {
        return new ApiResult<F>(HttpStatus.INTERNAL_SERVER_ERROR, msg, data);
    }

    public static <F> ApiResult<F> createApiResult(HttpStatus httpStatus, String msg, F data) {
        return new ApiResult<F>(httpStatus, msg, data);
    }
}