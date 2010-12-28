package com.tapestry5inaction.tlog.pages.admin;

import com.tapestry5inaction.tlog.entities.User;
import com.tapestry5inaction.tlog.services.Authenticator;
import org.apache.tapestry5.ValidationException;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

public class Login {

    @Property
    @Persist
    @Validate("required")
    private String userName;

    @Property
    @Persist
    @Validate("required")
    private String password;

    @InjectComponent
    private Form loginForm;

    @Inject
    private Authenticator authenticator;

    @Inject
    private Messages messages;

    @SessionState(create = false)
    private User user;

    void onValidateFromLoginForm() throws ValidationException {

        if (loginForm.isValid()) {

            user = authenticator.authenticate(userName, password);

            if (user == null) {
                loginForm.recordError(messages.get("invalid-username-or-password"));
            }
        }
    }

    @DiscardAfter
    Object onSuccess() {
        return Index.class;
    }
}
