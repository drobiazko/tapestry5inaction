package com.tapestry5book.services;

import com.tapestry5book.entities.User;

public interface PasswordPolicyService {

    boolean isPasswordAboutToExpire(User user);
}
