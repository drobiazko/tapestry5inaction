package com.tapestry5book.services;

import com.tapestry5book.entities.User;

public interface Authenticator {
    User authenticate(String userName, String password);

    boolean isLoggedIn();
}