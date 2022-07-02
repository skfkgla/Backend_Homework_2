package com.BackendHomework2.exception;


import com.BackendHomework2.exception.error.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    protected ResponseEntity<ErrorResponse> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        // 없는 경로로 요청 시
        ErrorCode errorCode = ErrorCode.NOT_FOUND_PATH;

        ErrorResponse response = ErrorResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .status(errorCode.getStatus().value())
                .build();

        return new ResponseEntity<>(response, errorCode.getStatus());
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        // GET POST 방식이 잘못된 경우
        ErrorCode errorCode = ErrorCode.METHOD_NOT_ALLOWED;

        ErrorResponse response = ErrorResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .status(errorCode.getStatus().value())
                .build();

        return new ResponseEntity<>(response, errorCode.getStatus());
    }
    @ExceptionHandler(NotFoundMileageTypeException.class)
    protected ResponseEntity<ErrorResponse> handleNotFoundMileageTypeException(NotFoundMileageTypeException e) {
        ErrorCode errorCode = ErrorCode.NOT_FOUND_MILEAGE_TYPE;

        ErrorResponse response = ErrorResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .status(errorCode.getStatus().value())
                .build();

        return new ResponseEntity<>(response, errorCode.getStatus());
    }
    @ExceptionHandler(DuplicateReviewException.class)
    protected ResponseEntity<ErrorResponse> handleDuplicateReviewException(DuplicateReviewException e) {
        ErrorCode errorCode = ErrorCode.DUPLICATE_REVIEW_EXCEPTION;

        ErrorResponse response = ErrorResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .status(errorCode.getStatus().value())
                .build();

        return new ResponseEntity<>(response, errorCode.getStatus());
    }
    @ExceptionHandler(NotFoundUserException.class)
    protected ResponseEntity<ErrorResponse> handleNotFoundUserException(NotFoundUserException ex) {
        ErrorCode errorCode = ErrorCode.NOT_FOUND_USER;

        ErrorResponse response = ErrorResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .status(errorCode.getStatus().value())
                .build();

        return new ResponseEntity<>(response, errorCode.getStatus());
    }
    @ExceptionHandler(NotFoundMileageException.class)
    protected ResponseEntity<ErrorResponse> handleNotFoundMileageException(NotFoundMileageException ex) {
        ErrorCode errorCode = ErrorCode.NOT_FOUND_MILEAGE;

        ErrorResponse response = ErrorResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .status(errorCode.getStatus().value())
                .build();

        return new ResponseEntity<>(response, errorCode.getStatus());
    }
}
