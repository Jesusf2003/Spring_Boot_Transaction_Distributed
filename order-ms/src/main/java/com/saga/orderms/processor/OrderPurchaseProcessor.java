package com.saga.orderms.processor;

import com.saga.orderms.model.OrderPurchaseEvent;
import com.saga.orderms.model.OrderPurchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

@Component
public class OrderPurchaseProcessor {

    private final Sinks.Many<OrderPurchaseEvent> sink;

    @Autowired
    public OrderPurchaseProcessor(Sinks.Many<OrderPurchaseEvent> sink) {
        this.sink = sink;
    }

    public void process(OrderPurchase orderPurchase) {
        OrderPurchaseEvent ope = new OrderPurchaseEvent()
                .setUserId(orderPurchase.getUserId())
                .setOrderId(orderPurchase.getId())
                .setPrice(orderPurchase.getPrice());
        sink.emitNext(ope, Sinks.EmitFailureHandler.FAIL_FAST);
    }
}