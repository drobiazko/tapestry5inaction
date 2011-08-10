package com.tapestry5inaction.services;

import com.tapestry5inaction.entities.User;

public interface PasswordPolicyService {

    boolean isPasswordAboutToExpire(User user);
}
