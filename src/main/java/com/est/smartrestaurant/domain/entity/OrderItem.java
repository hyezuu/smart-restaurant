package com.est.smartrestaurant.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class OrderItem extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Column(nullable = false)
    private Integer quantity;

    @Builder
    private OrderItem(Order order, Menu menu, Integer quantity) {
        this.order = order;
        this.menu = menu;
        this.quantity = quantity;
    }

    public void setOrder(Order order) {
        this.order = order;
        if(!order.getOrderItems().contains(this)) {
            order.getOrderItems().add(this);
        }
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
        if(!order.getOrderItems().contains(this)) {
            order.getOrderItems().add(this);
        }
    }

    public void changeQuantity(Integer quantity) {
        if(quantity == 0){
            order.getOrderItems().remove(this);
        }
        else this.quantity = quantity;
    }
}
