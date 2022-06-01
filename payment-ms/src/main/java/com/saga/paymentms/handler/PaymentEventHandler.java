package com.saga.paymentms.handler;

import com.saga.paymentms.model.Transaction;
import com.saga.paymentms.model.events.PaymentEvent;
import com.saga.paymentms.model.events.TransactionEvent;
import com.saga.paymentms.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import javax.transaction.Transactional;

import static com.saga.paymentms.enums.PaymentStatus.APPROVED;
import static com.saga.paymentms.enums.TransactionStatus.SUCCESSFUL;
import static com.saga.paymentms.enums.TransactionStatus.UNSUCCESSFUL;

@Component
public class PaymentEventHandler implements EventHandler<PaymentEvent, TransactionEvent> {

    private final TransactionRepository transactionRepository;
    private final Scheduler jdbcScheduler;

    @Autowired
    public PaymentEventHandler(
            TransactionRepository transactionRepository,
            Scheduler jdbcScheduler) {
        this.transactionRepository = transactionRepository;
        this.jdbcScheduler = jdbcScheduler;
    }

    @Transactional
    public TransactionEvent handleEvent(PaymentEvent event) {
        Mono.fromRunnable(() -> transactionRepository.save(
                        new Transaction()
                                .setOrderId(event.getOrderId())
                                .setPrice(event.getPrice())))
                .subscribeOn(jdbcScheduler)
                .subscribe();

        return new TransactionEvent()
                .orderId(event.getOrderId())
                .status(APPROVED.equals(event.getStatus())
                        ? SUCCESSFUL
                        : UNSUCCESSFUL);

    }
}