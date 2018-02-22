package com.bridgelabz.repository;

import org.springframework.data.repository.CrudRepository;

import com.bridgelabz.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
