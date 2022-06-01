package com.saga.paymentms.service;

import com.saga.paymentms.model.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TransactionService {

    Flux<Transaction> getAll();

    Flux<List<Transaction>> reactiveGetAll();

    Mono<Transaction> getOrderById(Integer id);
}