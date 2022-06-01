package com.saga.orderms.controller;

import com.saga.orderms.model.Product;
import com.saga.orderms.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/products")
@AllArgsConstructor
@Tag(name = "Products", description = "Manage products")
public class ProductController {

    @Autowired
    private final ProductService productService;

    @GetMapping
    @Operation(summary = "Obtain all products")
    public Flux<Product> getAllOrders() {
        return productService.getAll();
    }
}