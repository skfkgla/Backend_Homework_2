package com.BackendHomework2.exception.error;

import com.BackendHomework2.exception.ErrorCode;

public class NotFoundUserException extends RuntimeException{
    public NotFoundUserException(){
        super(ErrorCode.NOT_FOUND_USER.getMessage());
    }

    public NotFoundUserException(Exception ex){
        super(ex);
    }
}
