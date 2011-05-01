package com.tapestry5inaction.pages.chapter06.blog;


import com.tapestry5inaction.entities.User;
import com.tapestry5inaction.pages.chapter06.Index;
import com.tapestry5inaction.services.Authenticator;
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

    void onValidateFromLoginForm() {
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
        return Welcome.class;
    }
}