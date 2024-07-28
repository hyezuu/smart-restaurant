package com.est.smartrestaurant.service;

import com.est.smartrestaurant.common.exception.BusinessLogicException;
import com.est.smartrestaurant.common.exception.ExceptionCode;
import com.est.smartrestaurant.domain.entity.Order;
import com.est.smartrestaurant.repository.OrderRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public Order save(Order order) {

        orderRepository.save(order);
        order.updateTotalPrice(calculateTotal(order));
        return order;
    }

    @Transactional
    public Order update(Long id, Order newOrder) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new BusinessLogicException(
            ExceptionCode.ORDER_NOT_FOUND));
        orderRepository.save(order);
        order.updateTotalPrice(calculateTotal(order));
        return order;
    }

    @Transactional(readOnly = true)
    public double searchOrders(LocalDate start, LocalDate end, long storeId) {
        List<Order> orderList = orderRepository.findByCreatedAtBetweenAndStore_Id(start, end, storeId);
        return orderList.stream().mapToDouble(this::calculateTotal).sum();
    }

    public double calculateTotal(Order order) {
        return order.getOrderItems().stream()
            .mapToDouble(orderItem -> orderItem.getMenu().getPrice() * orderItem.getQuantity())
            .sum();
    }

}
