package org.groupware.common.response;


import org.groupware.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public record Response<T>(Integer code, String message, T data) {

    public static <T> Response<T> ok(T value){
        return new Response<>(HttpStatus.OK.value(), "ok", value);
    }

    public static <T> Response<T> error(ErrorCode error){
        return new Response<>(error.getCode(), error.getMessage(), null);
    }

    public static <T> Response<T> error(int code, String message){
        return new Response<>(code, message, null);
    }

}
