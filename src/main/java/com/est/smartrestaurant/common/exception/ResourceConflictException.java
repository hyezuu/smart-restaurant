package com.est.smartrestaurant.common.exception;

import lombok.Getter;

public class ResourceConflictException extends RuntimeException {
    public ResourceConflictException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
    }
}
