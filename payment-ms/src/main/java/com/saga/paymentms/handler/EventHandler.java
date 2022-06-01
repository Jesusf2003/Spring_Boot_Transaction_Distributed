package com.saga.paymentms.handler;

import com.saga.paymentms.model.events.Event;

public interface EventHandler<T extends Event, R extends Event> {

    R handleEvent(T event);
}