package com.tapestry5inaction.services.impl;

import com.tapestry5inaction.AppSymbolConstants;
import com.tapestry5inaction.entities.User;
import com.tapestry5inaction.services.PasswordPolicyService;
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
