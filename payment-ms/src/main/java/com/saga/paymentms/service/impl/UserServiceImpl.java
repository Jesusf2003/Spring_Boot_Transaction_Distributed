package com.saga.paymentms.service.impl;

import com.saga.paymentms.model.User;
import com.saga.paymentms.repository.UserRepository;
import com.saga.paymentms.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public Flux<User> getAll() {
        return Flux.fromIterable(userRepository.findAll());
    }
}