package com.tapestry5inaction.services.impl;

import com.tapestry5inaction.entities.User;
import com.tapestry5inaction.services.Authenticator;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ApplicationStateManager;

public class AuthenticatorImpl implements Authenticator {

    @Inject
    private ApplicationStateManager applicationStateManager;

    public User authenticate(String userName, String password) {
        if ("admin".equals(userName) && "admin".equals(password)) {
            return new User(userName, password);

        }

        return null;
    }

    public boolean isLoggedIn() {
        return applicationStateManager.exists(User.class);
    }
}