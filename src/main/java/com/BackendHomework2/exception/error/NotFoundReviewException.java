package com.BackendHomework2.exception.error;

import com.BackendHomework2.exception.ErrorCode;

public class NotFoundReviewException extends RuntimeException{
    public NotFoundReviewException(){
        super(ErrorCode.NOT_FOUND_REVIEW.getMessage());
    }

    public NotFoundReviewException(Exception ex){
        super(ex);
    }
}
