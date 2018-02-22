package com.bridgelabz.service;


import java.util.List;

import com.bridgelabz.model.User;

public interface GenericService {
    User findByUsername(String username);

    List<User> findAllUsers();

}
