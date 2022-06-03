package com.example.paymentservice.service;

import com.example.paymentservice.model.Payment;

import java.util.List;

public interface PaymentServiceI {

    Payment save(Payment payment);
    List<Payment> findAll();
}