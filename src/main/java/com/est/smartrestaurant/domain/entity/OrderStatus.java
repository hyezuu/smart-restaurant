package com.est.smartrestaurant.domain.entity;

import lombok.Getter;

@Getter
public enum OrderStatus {
    ORDER_RECEIVED(1, "주문 접수"),
    ORDER_COMPLETED(2, "주문 완료"),
    ORDER_CANCELED(3, "주문 취소");

    private final int stepNumber;
    private final String stepDescription;

    OrderStatus(int stepNumber, String stepDescription) {
        this.stepNumber = stepNumber;
        this.stepDescription = stepDescription;
    }
}
