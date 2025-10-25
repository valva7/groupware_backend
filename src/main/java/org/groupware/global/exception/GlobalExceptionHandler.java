package org.groupware.global.exception;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.groupware.common.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public Response<Void> handleIllegalArgumentException(IllegalArgumentException exception) {
        log.error(exception.getMessage(), exception);
        return Response.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<Void> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error(exception.getMessage(), exception);
        return Response.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage());
    }

    @ExceptionHandler(Exception.class)
    public Response<Void> handleException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return Response.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "");
    }

    @ExceptionHandler(RuntimeException.class)
    public Response<Void> runtimeException(RuntimeException exception) {
        log.error(exception.getMessage(), exception);
        return Response.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }


    // ======================================= 커스텀 ============================================
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(InvalidJwtException.class)
    public Response<Void> invalidJwtException(InvalidJwtException exception) {
        log.error(exception.getMessage(), exception);
        return Response.error(exception.getCode(), exception.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MemberException.class)
    public Response<Void> memberNotFoundException(MemberException exception) {
        log.error(exception.getMessage(), exception);
        return Response.error(exception.getCode(), exception.getMessage());
    }


}
