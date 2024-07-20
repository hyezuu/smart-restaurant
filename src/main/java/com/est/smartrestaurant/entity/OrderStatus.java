package com.est.smartrestaurant.entity;

import lombok.Getter;

@Getter
public enum OrderStatus {
    ORDER_REQUEST(1, "주문 접수"),
    ORDER_COMPLETE(2, "주문 완료"),
    ORDER_CANCEL(3, "주문 취소");

    private int stepNumber;
    private String stepDescription;

    OrderStatus(int stepNumber, String stepDescription) {
        this.stepNumber = stepNumber;
        this.stepDescription = stepDescription;
    }
}