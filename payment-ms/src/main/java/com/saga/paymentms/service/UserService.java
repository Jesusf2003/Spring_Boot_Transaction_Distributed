package com.saga.paymentms.service;

import com.saga.paymentms.model.User;
import reactor.core.publisher.Flux;

public interface UserService {

    Flux<User> getAll();
}