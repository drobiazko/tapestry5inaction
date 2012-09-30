package com.tapestry5book.tlog.services;


import com.tapestry5book.tlog.core.entities.User;

public interface Authenticator {
    User authenticate(String userName, String password);

    boolean isLoggedIn();
}
