package com.tapestry5inaction.pages.chapter08;

import com.tapestry5inaction.entities.Article;
import com.tapestry5inaction.entities.Track;
import com.tapestry5inaction.entities.User;
import com.tapestry5inaction.services.BlogService;
import com.tapestry5inaction.services.MusicLibrary;
import com.tapestry5inaction.services.UserDao;
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