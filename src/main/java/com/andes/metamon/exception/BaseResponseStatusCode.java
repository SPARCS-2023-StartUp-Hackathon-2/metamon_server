package com.andes.metamon.exception;

import lombok.Getter;

@Getter
public enum BaseResponseStatusCode {
    // 200 OK
    SUCCESS(200, "OK"),

    // 400
    USER_NOT_FOUND(400, "유저 정보를 찾을 수 없습니다."),
    USER_PASSWORD_NOT_MATCH(400, "패스워드가 맞지 않습니다."),
    USER_EMAIL_DUPLICATE(400, "중복된 이메일입니다."),
    TOKEN_INVALID_FORM(401, "잘못된 토큰 형식입니다."),
    TOKEN_HEADER_EMPTY(401, "헤더에 토큰이 포함되어 있지 않습니다.");


    // 500

    private final int status;
    private final String message;

    private BaseResponseStatusCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
