package com.tapestry5book.services;

import com.tapestry5book.entities.User;

import java.util.List;

public interface UserDao {

    User findById(Long id);

    User findByName(String name);

    List<User> findAll();

    void persist(User user);
}
