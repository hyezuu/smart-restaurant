package com.est.smartrestaurant.repository;

import com.est.smartrestaurant.domain.entity.Order;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCreatedAtBetweenAndStore_Id(LocalDateTime createdAtStart,
        LocalDateTime createdAtEnd, Long id);
}
