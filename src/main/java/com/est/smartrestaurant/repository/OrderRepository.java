package com.est.smartrestaurant.repository;

import com.est.smartrestaurant.domain.entity.Order;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCreatedAtBetweenAndStore_Id(LocalDate createdAtStart,
        LocalDate createdAtEnd, Long id);
}
