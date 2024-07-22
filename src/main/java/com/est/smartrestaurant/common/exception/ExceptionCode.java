package com.est.smartrestaurant.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
    CUSTOMER_NOT_FOUND_EXCEPTION(404, "Customer not found"),
    CUSTOMER_ALREADY_EXISTS(409,"Customer already exists");

    public final int status;
    public final String message;
}
