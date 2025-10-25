package org.groupware.global.exception;

import lombok.Getter;

@Getter
public class MemberException extends RuntimeException{

    private final int code;
    private final String message;

    public MemberException(ErrorCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

}
