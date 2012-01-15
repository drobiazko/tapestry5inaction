package com.tapestry5inaction.pages.chapter11;

import com.tapestry5inaction.entities.User;
import com.tapestry5inaction.pages.chapter06.blog.Welcome;
import com.tapestry5inaction.services.Authenticator;
import org.apache.tapestry5.annotations.DiscardAfter;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;

public class Login {

    @Property
    private User user;

    @InjectComponent
    private Form loginForm;

    @Inject
    private Authenticator authenticator;

    @InjectComponent
    private Zone formZone;

    @Inject
    private Messages messages;

    @Inject
    private Request request;

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

    @DiscardAfter
    Object onSuccess() {
        return Welcome.class;
    }

    Object onFailure() {
        return request.isXHR() ? formZone.getBody() : null;
    }
}