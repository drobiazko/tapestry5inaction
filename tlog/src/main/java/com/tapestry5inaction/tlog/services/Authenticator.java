package com.tapestry5inaction.tlog.services;


import com.tapestry5inaction.tlog.entities.User;

public interface Authenticator {
    User authenticate(String userName, String password);
}
