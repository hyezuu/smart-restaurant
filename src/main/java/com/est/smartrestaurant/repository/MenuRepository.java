package com.est.smartrestaurant.repository;

import com.est.smartrestaurant.domain.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Boolean existsByName(String name);
}
