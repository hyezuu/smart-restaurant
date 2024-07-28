package com.est.smartrestaurant.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "orders")
@NoArgsConstructor
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderItem> orderItems;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", length = 50) // 적절한 길이 설정
    private OrderStatus orderStatus = OrderStatus.ORDER_RECEIVED;

    private double totalPrice;

    @Builder
    private Order(Customer customer, Store store, List<OrderItem> orderItems) {
        this.customer = customer;
        this.orderItems = orderItems;
        this.store = store;
    }

    public void updateTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void update(Order newOrder){
        this.orderItems = newOrder.getOrderItems();
        this.orderStatus = newOrder.getOrderStatus();
    }
}
