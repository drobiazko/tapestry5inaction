package com.tapestry5book.tlog.services.impl;

import com.tapestry5book.tlog.services.GravatarService;
import org.apache.commons.codec.digest.DigestUtils;

public class GravatarServiceImpl implements GravatarService {

    private static final String BASE_URL = "http://www.gravatar.com/avatar/";

    public String getAvatar(String email) {
        if (email == null) {
            return null;

        }

        return String.format("%s%s", BASE_URL, DigestUtils.md5Hex(email));
    }
}
