package com.est.smartrestaurant.repository;

import com.est.smartrestaurant.domain.entity.Menu;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Boolean existsByName(String name);
    Page<Menu> findAllByCategory(String category, Pageable pageable);
}
