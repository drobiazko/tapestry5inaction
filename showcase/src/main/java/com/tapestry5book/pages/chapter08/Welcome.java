package com.tapestry5book.pages.chapter08;

import com.tapestry5book.entities.User;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.If;

public class Welcome {
    @SessionState
    @Property
    private User user;

    @Property
    private boolean userExists;

    @Component(parameters = "test=userExists")
    private If iff;
}
