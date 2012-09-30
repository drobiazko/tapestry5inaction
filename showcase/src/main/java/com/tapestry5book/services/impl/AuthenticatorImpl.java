package com.tapestry5book.services.impl;

import com.tapestry5book.entities.User;
import com.tapestry5book.services.Authenticator;
import com.tapestry5book.services.UserDao;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tapestry5.services.ApplicationStateManager;

public class AuthenticatorImpl implements Authenticator {

    private UserDao userDao;

    private ApplicationStateManager applicationStateManager;

    public AuthenticatorImpl(UserDao userDao, ApplicationStateManager applicationStateManager) {
        this.userDao = userDao;
        this.applicationStateManager = applicationStateManager;
    }

    public User authenticate(String userName, String password) {
        User user = userDao.findByName(userName);

        if (user != null) {
            String digest = DigestUtils.md5Hex(password);

            if (digest.equals(user.getPassword())) {
                return user;
            }
        }

        return null;
    }

    public boolean isLoggedIn() {
        return applicationStateManager.exists(User.class);
    }
}