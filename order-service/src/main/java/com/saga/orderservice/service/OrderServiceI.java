package com.saga.orderservice.service;

import com.saga.orderservice.model.Order;

import java.util.List;

public interface OrderServiceI {

    Order save(Order order);
    List<Order> findAll();
}