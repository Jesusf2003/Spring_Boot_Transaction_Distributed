package com.saga.msorder.repo;

import com.saga.msorder.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCRUD extends CrudRepository<Users,Integer> {
}