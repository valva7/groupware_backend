package org.groupware.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // 회원 및 인증 관련
    WRONG_ACCOUNT(1001, "error.auth.wrong_account"),
    ALREADY_EXIST(1002, "error.auth.already_exist"),
    NO_EXIST_MEMBER(1003, "error.auth.no_exist_member"),
    JWT_EXCEPTION(1101, "error.auth.jwt_exception"),
    UNAUTHORIZED(1102, "error.auth.unauthorized"),
    NO_EXIST_ROLE(1103, "error.auth.no_exist_role"),

    // 부서 관련
    NOT_EXIST_DEPARTMENT(2001, "error.department.not_exist"),
    NOT_EXIST_DEPARTMENT_MEMBER(2001, "error.department.not_exist"),

    // 기타
    S3FILE_UPLOAD_FAILED(9000, "error.file.upload.failed")



    ;

    private final Integer code;
    private final String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}