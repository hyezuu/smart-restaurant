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
@Entity(name = "menus")
@NoArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private String description;

    @Builder
    private Menu(String name, String category, Double price, String description) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
    }

    public void updateName(String newName) {
        this.name = newName;
    }

    public void updateCategory(String newCategory) {
        this.category = newCategory;
    }

    public void updatePrice(Double newPrice) {
        this.price = newPrice;
    }

    public void updateDescription(String newDescription) {
        this.description = newDescription;
    }
}
