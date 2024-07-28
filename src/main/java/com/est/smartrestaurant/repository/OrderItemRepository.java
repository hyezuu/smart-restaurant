package com.est.smartrestaurant.repository;

import com.est.smartrestaurant.domain.dto.PopularMenuItemDTO;
import com.est.smartrestaurant.domain.entity.Menu;
import com.est.smartrestaurant.domain.entity.OrderItem;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("SELECT oi.menu, SUM(oi.quantity) as total " +
        "FROM OrderItem oi " +
        "GROUP BY oi.menu " +
        "ORDER BY total DESC")
    Page<Menu> findPopularMenus(Pageable pageable);
}
