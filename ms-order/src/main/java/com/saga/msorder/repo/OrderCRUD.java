package com.saga.msorder.repo;

import com.saga.msorder.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCRUD extends CrudRepository<Order, Integer> {
}