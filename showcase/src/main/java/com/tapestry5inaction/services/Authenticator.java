package com.tapestry5inaction.services;

import com.tapestry5inaction.entities.User;

public interface Authenticator {
    User authenticate(String userName, String password);

    boolean isLoggedIn();
}