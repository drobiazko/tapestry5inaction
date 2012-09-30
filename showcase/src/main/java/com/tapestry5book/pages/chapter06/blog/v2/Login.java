package com.tapestry5book.pages.chapter06.blog.v2;

import com.tapestry5book.entities.User;
import com.tapestry5book.pages.chapter06.blog.Welcome;
import com.tapestry5book.services.Authenticator;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

public class Login {

    @Property
    private User user;

    @InjectComponent
    private Form loginForm;

    @Inject
    private Authenticator authenticator;

    @Inject
    private Messages messages;

    @SessionState(create = false)
    private User loggedIn;

    void onPrepare() {
        user = new User();
    }

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