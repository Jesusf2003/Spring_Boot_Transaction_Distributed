package com.saga.orderms.model;

import com.saga.orderms.enums.OrderStatus;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@ToString
public class OrderPurchase {

    public OrderPurchase() {
    }

    @Id
    @GeneratedValue
    private Integer id;
    private Integer userId;
    private Integer productId;
    private double price;
    private OrderStatus status;

    public OrderPurchase setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public OrderPurchase setProductId(Integer productId) {
        this.productId = productId;
        return this;
    }

    public OrderPurchase setPrice(double price) {
        this.price = price;
        return this;
    }

    public OrderPurchase setStatus(OrderStatus status) {
        this.status = status;
        return this;
    }
}