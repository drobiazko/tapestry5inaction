package com.tapestry5book.services.impl;

import com.tapestry5book.entities.User;
import com.tapestry5book.services.UserDao;
import org.apache.tapestry5.ValueEncoder;

public class UserEncoder implements ValueEncoder<User> {

    private UserDao userDao;

    public UserEncoder(UserDao userDao) {
        this.userDao = userDao;
    }

    public String toClient(User value) {
        return String.valueOf(value.getId());
    }

    public User toValue(String clientValue) {
        Long id = Long.valueOf(clientValue);

        return userDao.findById(id);
    }
}
