package com.est.smartrestaurant.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "stores")
@NoArgsConstructor
public class Store extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String phoneNumber;
    @Column(nullable = false)
    private String address;

    @Builder
    private Store(String name, String phoneNumber, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Store update(Store newStore) {
        if(newStore.getName() != null) {
            this.name = newStore.getName();
        }
        if(newStore.getPhoneNumber() != null) {
            this.phoneNumber = newStore.getPhoneNumber();
        }
        if(newStore.getAddress() != null) {
            this.address = newStore.getAddress();
        }
        return this;
    }
}
