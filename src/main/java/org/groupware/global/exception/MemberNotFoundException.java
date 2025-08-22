package org.groupware.global.exception;

import lombok.Getter;

@Getter
public class MemberNotFoundException extends RuntimeException{

    private final ErrorCode errorCode;
    private final String message;

    public MemberNotFoundException(String message) {
        this.errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        this.message = message;
    }

}
