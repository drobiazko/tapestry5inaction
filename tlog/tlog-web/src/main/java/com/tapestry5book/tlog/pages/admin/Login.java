package com.tapestry5book.tlog.pages.admin;

import com.tapestry5book.tlog.core.entities.User;
import com.tapestry5book.tlog.services.Authenticator;
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

    @Component(parameters = "clientValidation=false")
    private Form loginForm;

    @Inject
    private Authenticator authenticator;

    @Inject
    private Messages messages;

    @SessionState(create = false)
    private User user;

    void onValidateFromLoginForm() throws ValidationException {

        if (loginForm.isValid()) {

            User authenticated = authenticator.authenticate(userName, password);

            if (authenticated == null) {
                loginForm.recordError(messages.get("invalid-username-or-password"));
            } else {
                user = authenticated;
            }
        }
    }

    @DiscardAfter
    Object onSuccess() {
        return Index.class;
    }
}
