package org.groupware.global.exception;

import lombok.Getter;

@Getter
public class DepartmentException extends RuntimeException{

    private final ErrorCode errorCode;
    private final String message;

    public DepartmentException(String message) {
        this.errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        this.message = message;
    }

}
