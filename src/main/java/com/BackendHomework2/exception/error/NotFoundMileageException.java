package com.BackendHomework2.exception.error;

import com.BackendHomework2.exception.ErrorCode;

public class NotFoundMileageException extends RuntimeException{
    public NotFoundMileageException(){
        super(ErrorCode.NOT_FOUND_MILEAGE.getMessage());
    }

    public NotFoundMileageException(Exception ex){
        super(ex);
    }
}
