package com.saga.orderms.consumer;

import com.saga.orderms.model.Event;

public interface EventConsumer<T extends Event>{

    void consumeEvent(T event);
}