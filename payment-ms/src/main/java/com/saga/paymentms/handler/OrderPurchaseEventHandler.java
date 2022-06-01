package com.saga.paymentms.handler;

import com.saga.paymentms.enums.PaymentStatus;
import com.saga.paymentms.model.User;
import com.saga.paymentms.model.events.OrderPurchaseEvent;
import com.saga.paymentms.model.events.PaymentEvent;
import com.saga.paymentms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class OrderPurchaseEventHandler implements EventHandler<OrderPurchaseEvent, PaymentEvent> {

    private final UserRepository userRepository;

    @Autowired
    public OrderPurchaseEventHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public PaymentEvent handleEvent(OrderPurchaseEvent event) {
        double orderPrice = event.getPrice();
        Integer userId = event.getUserId();
        PaymentEvent paymentEvent = new PaymentEvent()
                .orderId(event.getOrderId())
                .price(event.getPrice())
                .status(PaymentStatus.DECLINED);
        userRepository
                .findById(userId)
                .ifPresent(user -> deductUserBalance(orderPrice, paymentEvent, user));
        return paymentEvent;
    }

    private void deductUserBalance(double orderPrice, PaymentEvent paymentEvent, User user) {
        double userBalance = user.getBalance();
        if (userBalance >= orderPrice) {
            user.setBalance(userBalance - orderPrice);
            userRepository.save(user);
            paymentEvent.status(PaymentStatus.APPROVED);
        }
    }
}