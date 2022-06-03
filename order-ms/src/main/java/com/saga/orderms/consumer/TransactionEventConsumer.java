package com.saga.orderms.consumer;

import com.saga.orderms.enums.OrderStatus;
import com.saga.orderms.model.OrderPurchase;
import com.saga.orderms.model.TransactionEvent;
import com.saga.orderms.repository.OrderPurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import static com.saga.orderms.enums.TransactionStatus.SUCCESSFUL;

@Component
@AllArgsConstructor
public class TransactionEventConsumer implements EventConsumer<TransactionEvent> {

    @Autowired
    private final OrderPurchaseRepository orderPurchaseRepository;

    @Autowired
    private final Scheduler jdbcScheduler;

    public void consumeEvent(TransactionEvent event) {
        Mono.fromRunnable(
                        () -> orderPurchaseRepository.findById(event.getOrderId())
                                .ifPresent(order -> {
                                    setStatus(event, order);
                                    orderPurchaseRepository.save(order);
                                }))
                .subscribeOn(jdbcScheduler)
                .subscribe();
    }

    private void setStatus(TransactionEvent transactionEvent, OrderPurchase order) {
        order.setStatus(SUCCESSFUL.equals(transactionEvent.getStatus())
                ? OrderStatus.COMPLETED
                : OrderStatus.FAILED);
    }
}