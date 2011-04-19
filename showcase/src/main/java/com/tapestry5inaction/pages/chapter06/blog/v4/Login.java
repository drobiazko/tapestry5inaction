package com.tapestry5inaction.pages.chapter06.blog.v4;

import com.tapestry5inaction.entities.User;
import com.tapestry5inaction.pages.chapter06.Index;
import com.tapestry5inaction.services.Authenticator;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Cookies;

public class Login {

    @Property
    private User user;

    @Property
    private boolean rememberMe;

    @InjectComponent
    private Form loginForm;

    @Inject
    private Authenticator authenticator;

    @Inject
    private Cookies cookies;

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

                if (rememberMe) {
                    cookies.writeCookieValue("tlog-user", user.getName());
                }
            }
        }

    }

    Object onSuccess() {
        System.err.println("rememberMe: " + rememberMe);
        return Index.class;
    }

}