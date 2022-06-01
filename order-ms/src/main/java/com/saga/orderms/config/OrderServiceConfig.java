package com.saga.orderms.config;

import com.saga.orderms.consumer.EventConsumer;
import com.saga.orderms.model.OrderPurchaseEvent;
import com.saga.orderms.model.TransactionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Configuration
public class OrderServiceConfig {

    private final EventConsumer<TransactionEvent> transactionEventConsumer;

    @Autowired
    public OrderServiceConfig(EventConsumer<TransactionEvent> transactionEventConsumer) {
        this.transactionEventConsumer = transactionEventConsumer;
    }

    @Bean
    public Sinks.Many<OrderPurchaseEvent> sink() {
        return Sinks.many()
                .multicast()
                .directBestEffort();
    }

    @Bean
    public Supplier<Flux<OrderPurchaseEvent>> orderPurchaseEventPublisher(
            Sinks.Many<OrderPurchaseEvent> publisher) {
        return publisher::asFlux;
    }

    @Bean
    public Consumer<TransactionEvent> transactionEventProcessor() {
        return transactionEventConsumer::consumeEvent;
    }
}
