package com.est.smartrestaurant.common.exception;

public class BusinessLogicException extends RuntimeException {
    public BusinessLogicException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
    }
}
