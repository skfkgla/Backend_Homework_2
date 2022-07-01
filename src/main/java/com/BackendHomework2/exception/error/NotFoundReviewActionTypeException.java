package com.BackendHomework2.exception.error;

import com.BackendHomework2.exception.ErrorCode;

public class NotFoundReviewActionTypeException extends RuntimeException{
    public NotFoundReviewActionTypeException(){
        super(ErrorCode.NOT_FOUND_REVIEW_ACTION_TYPE.getMessage());
    }

    public NotFoundReviewActionTypeException(Exception ex){
        super(ex);
    }
}

