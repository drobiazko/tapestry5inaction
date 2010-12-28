package com.tapestry5inaction.tlog.services.impl;


import com.tapestry5inaction.tlog.entities.User;
import com.tapestry5inaction.tlog.services.Authenticator;
import com.tapestry5inaction.tlog.services.BlogService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class AuthenticatorImpl implements Authenticator {
    @Inject
    private BlogService blogService;

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
}
