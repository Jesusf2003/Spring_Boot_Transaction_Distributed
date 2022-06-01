package com.saga.paymentms.model;

import lombok.*;

import javax.persistence.*;

@ToString
@AllArgsConstructor
@Data
@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer orderId;
    private double price;

    public Transaction() {
    }

    public Transaction setOrderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    public Transaction setPrice(double price) {
        this.price = price;
        return this;
    }
}
