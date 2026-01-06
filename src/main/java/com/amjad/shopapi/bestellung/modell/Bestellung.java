package com.amjad.shopapi.bestellung.modell;

import jakarta.persistence.*;
import java.util.List;


@Entity
@Table(name = "orders")
public class Bestellung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private double totalPrice;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Bestellposition> orderItems;

    // Getter und Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Bestellposition> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<Bestellposition> orderItems) {
        this.orderItems = orderItems;
    }
}

