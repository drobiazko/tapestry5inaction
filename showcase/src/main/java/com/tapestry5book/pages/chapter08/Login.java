package com.tapestry5book.pages.chapter08;

import com.tapestry5book.entities.User;
import com.tapestry5book.services.Authenticator;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

public class Login {

    @InjectComponent
    private BeanEditForm loginForm;

    @Inject
    private Authenticator authenticator;

    @Inject
    private Messages messages;

    @Property
    private User user;

    @SessionState(create = false)
    private User loggedIn;

    void onValidateFromLoginForm() {
        if (loginForm.isValid()) {
            User authenticated = authenticator.authenticate(user.getName(), user.getPassword());

            if (authenticated == null) {
                loginForm.recordError(messages.get("invalid-username-or-password"));
            } else {
                loggedIn = authenticated;
            }
        }
    }

    Object onSuccess() {
        return Welcome.class;
    }
}