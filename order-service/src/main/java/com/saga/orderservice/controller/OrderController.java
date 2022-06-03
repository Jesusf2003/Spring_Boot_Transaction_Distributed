package com.saga.orderservice.controller;

import com.saga.orderservice.model.Order;
import com.saga.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/save")
    public Order save(Order order) {
        return orderService.save(order);
    }

    @GetMapping("/all")
    public List<Order> findAll() {
        return orderService.findAll();
    }
}