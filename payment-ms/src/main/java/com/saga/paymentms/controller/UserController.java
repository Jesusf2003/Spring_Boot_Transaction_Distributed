package com.saga.paymentms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.saga.paymentms.model.User;
import com.saga.paymentms.service.UserService;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Tag(name = "Users", description = "Manage users")
public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping
    @Operation(summary = "Obtain all users", description = "Obtain all users registered")
    public Flux<User> getAllUsers() {
        return userService.getAll();
    }
}