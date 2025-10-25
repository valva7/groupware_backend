package org.groupware.global.exception;

import lombok.Getter;

@Getter
public class DepartmentException extends RuntimeException{

    private final int code;
    private final String message;

    public DepartmentException(ErrorCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

}
