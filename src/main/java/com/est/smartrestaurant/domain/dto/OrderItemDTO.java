package com.est.smartrestaurant.domain.dto;

import com.est.smartrestaurant.domain.entity.Menu;
import com.est.smartrestaurant.domain.entity.OrderItem;

public class OrderItemDTO {
    record Request(Long menuId, int quantity) {
        OrderItem toEntity(){
            return OrderItem.builder()
                .menu(new Menu(menuId))
                .quantity(quantity)
                .build();
        }
    }
    record Response(Long orderId, Long menuId, int quantity) {
        static Response from(OrderItem orderItem){
            return new Response(orderItem.getId()
                , orderItem.getMenu().getId()
                , orderItem.getQuantity());
        }
    }
}
