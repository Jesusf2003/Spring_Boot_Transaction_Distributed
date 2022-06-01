package com.saga.orderms.service.impl;

import com.saga.orderms.enums.OrderStatus;
import com.saga.orderms.repository.OrderPurchaseRepository;
import com.saga.orderms.model.Order;
import com.saga.orderms.model.OrderPurchase;
import com.saga.orderms.processor.OrderPurchaseProcessor;
import com.saga.orderms.repository.ProductRepository;
import com.saga.orderms.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Autowired
    private final OrderPurchaseRepository orderPurchaseRepository;

    @Autowired
    private final OrderPurchaseProcessor orderPurchaseProcessor;

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final Scheduler jdbcScheduler;


    @Override
    public Mono<OrderPurchase> createOrder(Order order) {
        OrderPurchase orderPurchase = getOrderPurchase(order);
        OrderPurchase savedOrderPurchase = orderPurchaseRepository.save(orderPurchase);
        orderPurchaseProcessor.process(orderPurchase);
        return Mono.just(savedOrderPurchase);
    }

    @Override
    public Flux<OrderPurchase> getAll() {
        return Flux.defer(() -> Flux.fromIterable(orderPurchaseRepository.findAll()))
                .subscribeOn(jdbcScheduler);
    }

    @Override
    public Flux<List<OrderPurchase>> reactiveGetAll() {
        Flux<Long> interval = Flux.interval(Duration.ofMillis(2000));
        Flux<List<OrderPurchase>> orderPurchaseFlux = Flux.fromStream(
                Stream.generate(orderPurchaseRepository::findAll));
        return Flux.zip(interval, orderPurchaseFlux)
                .map(Tuple2::getT2);
    }

    @Override
    public Mono<OrderPurchase> getOrderById(Integer id) {
        return Mono.fromCallable(
                        () -> orderPurchaseRepository.findById(id)
                                .orElseThrow(() -> new ResponseStatusException(
                                        HttpStatus.NOT_FOUND, "Order id: " + id + " does not exist")))
                .subscribeOn(jdbcScheduler);
    }

    private OrderPurchase getOrderPurchase(final Order order) {
        Integer productId = order.getProductId();
        return new OrderPurchase()
                .setProductId(productId)
                .setUserId(order.getUserId())
                .setPrice(productRepository.findById(productId)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Product ID: " + productId + " does not exist"))
                        .getPrice())
                .setStatus(OrderStatus.CREATED);
    }
}