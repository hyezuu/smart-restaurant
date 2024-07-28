package com.est.smartrestaurant.domain.dto;

import com.est.smartrestaurant.domain.entity.Customer;
import com.est.smartrestaurant.domain.entity.Order;
import com.est.smartrestaurant.domain.entity.Store;
import java.time.LocalDate;
import java.util.List;

public class OrderDTO {

    public record Request(
        Long storeId,
        Long customerId,
        List<OrderItemDTO.Request> orderItems
    ) {

        public Order toEntity() {
            return Order.builder()
                .store(new Store(storeId))
                .customer(new Customer(customerId))
                .orderItems(this.orderItems.stream()
                    .map(OrderItemDTO.Request::toEntity).toList())
                .build();
        }
    }

    public record Response(
        Long orderId,
        String storeName,
        String customerName,
        List<OrderItemDTO.Response> orderItems,
        double totalPrice,
        LocalDate orderDate
    ) {
        static Response from(Order order) {
            return new Response(
                order.getId(), order.getStore().getName(),
                order.getCustomer().getName(),
                order.getOrderItems().stream().map(OrderItemDTO.Response::from).toList(),
                order.getTotalPrice(),
                order.getCreatedAt()
            );
        }
    }

}

