package com.tapestry5book.tlog.services.impl;


import com.tapestry5book.tlog.core.entities.User;
import com.tapestry5book.tlog.services.Authenticator;
import com.tapestry5book.tlog.services.BlogService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ApplicationStateManager;

public class AuthenticatorImpl implements Authenticator {
    @Inject
    private BlogService blogService;

    @Inject
    private ApplicationStateManager applicationStateManager;

    public User authenticate(String userName, String password) {
        User user = blogService.findUserByName(userName);

        if (user != null) {
            String digest = DigestUtils.md5Hex(password);

            if (user.getPassword().equals(digest)) {
                return user;
            }
        }

        return null;
    }

    public boolean isLoggedIn() {
        return applicationStateManager.exists(User.class);
    }
}
