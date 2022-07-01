package com.BackendHomework2.exception.error;

import com.BackendHomework2.exception.ErrorCode;

public class NotFoundMileageTypeException extends RuntimeException{
    public NotFoundMileageTypeException(){
        super(ErrorCode.NOT_FOUND_MILEAGE_TYPE.getMessage());
    }

    public NotFoundMileageTypeException(Exception ex){
        super(ex);
    }
}

