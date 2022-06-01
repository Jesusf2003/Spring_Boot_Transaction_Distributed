package com.saga.msorder.controller;

import com.saga.msorder.bean.Order;
import com.saga.msorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/all")
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @PostMapping("/create")
    public Order saveOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }
}