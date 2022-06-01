package com.saga.orderms.repository;

import com.saga.orderms.model.OrderPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPurchaseRepository extends JpaRepository<OrderPurchase, Integer>{
}