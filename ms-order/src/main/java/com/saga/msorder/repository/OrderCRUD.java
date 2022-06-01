package com.saga.msorder.repository;

import com.saga.msorder.bean.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderCRUD extends CrudRepository<Order, Integer> {
}