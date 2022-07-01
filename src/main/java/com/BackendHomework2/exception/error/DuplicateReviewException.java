package com.BackendHomework2.exception.error;

import com.BackendHomework2.exception.ErrorCode;

public class DuplicateReviewException extends RuntimeException{
    public DuplicateReviewException(){
        super(ErrorCode.DUPLICATE_REVIEW_EXCEPTION.getMessage());
    }

    public DuplicateReviewException(Exception ex){
        super(ex);
    }
}
