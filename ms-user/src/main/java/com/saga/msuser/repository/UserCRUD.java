package com.saga.msuser.repository;

import com.saga.msuser.bean.User;
import org.springframework.data.repository.CrudRepository;

public interface UserCRUD extends CrudRepository<User, Integer> {
}