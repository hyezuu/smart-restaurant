package com.est.smartrestaurant.controller;

import com.est.smartrestaurant.domain.dto.OrderDTO;
import com.est.smartrestaurant.domain.dto.OrderDTO.Response;
import com.est.smartrestaurant.domain.entity.Order;
import com.est.smartrestaurant.service.OrderService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/orders")
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody OrderDTO.Request post) {
        return orderService.save(post.toEntity());
    }

    @PutMapping("/{id}")
    void updateOrder(@PathVariable("id") long id, @RequestBody OrderDTO.Request request) {
        orderService.update(id, request.toEntity());
    }

    @GetMapping("/search/total")
    public double searchOrders(
        @RequestParam(required = false) Long storeId,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime startDate,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime endDate) {
        return orderService.searchOrders(startDate, endDate, storeId);
    }
}
