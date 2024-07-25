package com.est.smartrestaurant.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
    CUSTOMER_NOT_FOUND(404, "Customer not found"),
    CUSTOMER_ALREADY_EXISTS(409,"Customer already exists"),
    MENU_NOT_FOUND(404, "Menu not found"),
    MENU_ALREADY_EXISTS(409, "Menu already exists"),
    STORE_NOT_FOUND(404, "Store not found"),
    STORE_ALREADY_EXISTS(409, "Store already exists"),
    ORDER_NOT_FOUND(404, "Order not found");

    public final int status;
    public final String message;
}
