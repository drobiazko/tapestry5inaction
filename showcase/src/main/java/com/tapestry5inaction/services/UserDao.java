package com.tapestry5inaction.services;

import com.tapestry5inaction.entities.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();
}
