package org.groupware.global.exception;

import lombok.Getter;

@Getter
public class S3FileProcessException extends RuntimeException {

    private final int code;
    private final String message;

    public S3FileProcessException(ErrorCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }
}
