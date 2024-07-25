package com.est.smartrestaurant.repository;

import com.est.smartrestaurant.domain.entity.OrderItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Query("""
        SELECT oi.menu.id as menuItemId,\
        SUM(oi.quantity) as totalQuantity \
        FROM OrderItem oi \
        GROUP BY oi.menu.id ORDER BY oi.quantity DESC LIMIT 3""")
    List<PopularMenuItemProjection> findPopularMenuItems();
}
