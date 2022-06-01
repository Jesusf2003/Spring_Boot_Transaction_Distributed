package com.saga.orderms.service.impl;

import com.saga.orderms.model.Product;
import com.saga.orderms.repository.ProductRepository;
import com.saga.orderms.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductRepository productRepository;

    @Override
    public Flux<Product> getAll() {
        return Flux.fromIterable(productRepository.findAll());
    }
}