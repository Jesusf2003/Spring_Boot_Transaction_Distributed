package com.saga.orderms.service;

import com.saga.orderms.model.Product;
import reactor.core.publisher.Flux;

public interface ProductService {

    Flux<Product> getAll();
}