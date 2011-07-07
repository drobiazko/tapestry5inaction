package com.tapestry5inaction.services;

import com.tapestry5inaction.entities.User;

import java.util.List;

public interface UserDao {

    User findById(Long id);

    List<User> findAll();
}
