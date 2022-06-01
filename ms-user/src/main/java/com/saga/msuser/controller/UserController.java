package com.saga.msuser.controller;

import com.saga.msuser.bean.User;
import com.saga.msuser.repository.UserCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserCRUD userCRUD;

    @GetMapping("/all")
    public List<User> getAllUser() {
        List<User> users = (List<User>) userCRUD.findAll();
        return users;
    }

    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {
        user = userCRUD.save(user);
        return user;
    }
}