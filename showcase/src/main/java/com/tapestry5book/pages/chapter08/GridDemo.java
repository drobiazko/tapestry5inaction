package com.tapestry5book.pages.chapter08;

import com.tapestry5book.entities.User;
import com.tapestry5book.services.UserDao;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.List;

public class GridDemo {
    @Inject
    private UserDao userDao;

    @Property
    private List<User> users;

    @Property
    private User currentUser;

    void setupRender() {
        users = userDao.findAll();
    }

}