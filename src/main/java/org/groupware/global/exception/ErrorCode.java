package org.groupware.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // 회원 및 인증 관련
    WRONG_ACCOUNT(1001, "로그인 정보가 틀렸습니다."),
    ALREADY_EXIST(1002, "이미 존재하는 아이디입니다."),
    NO_EXIST_MEMBER(1003, "존재하지 않는 사용자"),
    JWT_EXCEPTION(1101, "JWT 관련 예외 발생"),
    UNAUTHORIZED(1102, "유효하지 않은 토큰입니다."),
    NO_EXIST_ROLE(1103, "존재하지 않는 권한"),

    // 기타
    S3FILE_UPLOAD_FAILED(9000, "파일 업로드 실패")



    ;

    private final Integer code;
    private final String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}