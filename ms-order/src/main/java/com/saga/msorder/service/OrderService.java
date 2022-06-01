package com.saga.msorder.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saga.msorder.bean.Order;
import com.saga.msorder.repository.OrderCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderCRUD orderCRUD;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Value("${order.topic.name}")
    private String topicName;

    ObjectMapper om = new ObjectMapper();

    public Order saveOrder(Order order) {
        order.setStatus("CREATED");
        order = orderCRUD.save(order);
        try {
            String orderStr = om.writeValueAsString(order);
            kafkaTemplate.send(topicName, orderStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return order;
    }

    public List<Order> getOrders() {
        List<Order> orders = (List<Order>) orderCRUD.findAll();
        return orders;
    }
}