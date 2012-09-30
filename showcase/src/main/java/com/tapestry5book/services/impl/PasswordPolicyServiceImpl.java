package com.tapestry5book.services.impl;

import com.tapestry5book.AppSymbolConstants;
import com.tapestry5book.entities.User;
import com.tapestry5book.services.PasswordPolicyService;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.ioc.util.TimeInterval;

import java.util.Date;

public class PasswordPolicyServiceImpl implements PasswordPolicyService {

    @Inject
    @Symbol(AppSymbolConstants.PASSWORD_EXPIRY_PERIOD)
    private TimeInterval passwordExpiryPeriod;


    public boolean isPasswordAboutToExpire(User user) {
        final Date passwordChanged = user.getPasswordChanged();

        final long time = new Date().getTime();

        return time < passwordChanged.getTime() + passwordExpiryPeriod.milliseconds();
    }
}
