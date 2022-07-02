package com.BackendHomework2.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    NOT_FOUND_PATH(HttpStatus.NOT_FOUND, "PATH_001", "NOT FOUND PATH"), // 없는 경로로 요청보낸 경우
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED,"PATH_002","METHOD NOT ALLOWED"), // HTTP 요청 메서드를 잘못 보낸경우

    NOT_FOUND_MILEAGE_TYPE(HttpStatus.NOT_FOUND, "MILEAGE_001", "해당 마일리지 타입이 존재하지 않습니다"),
    NOT_FOUND_REVIEW_ACTION_TYPE(HttpStatus.NOT_FOUND, "MILEAGE_002", "리뷰 액션타입이 존재하지 않습니다"),
    NOT_FOUND_MILEAGE(HttpStatus.NOT_FOUND, "MILEAGE_003", "해당 리뷰에 마일리지를 찾지 못했습니다."),

    NOT_FOUND_REVIEW(HttpStatus.NOT_FOUND,"REVIEW_001", "해당 리뷰를 찾지 못했습니다."),
    DUPLICATE_REVIEW_EXCEPTION(HttpStatus.FORBIDDEN, "REVIEW_002", "이미 리뷰가 있는 장소입니다."),

    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "USER_001", "해당 사용자를 찾을 수 없습니다.");
    private final String code;
    private final String message;
    private final HttpStatus status;

    ErrorCode(final HttpStatus status, final String code, final String message){
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
