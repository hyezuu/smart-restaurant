package com.est.smartrestaurant.repository;

import com.est.smartrestaurant.domain.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
    boolean existsByPhoneNumber(String phoneNumber);
}
