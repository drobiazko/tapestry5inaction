package com.tapestry5inaction.pages.chapter05;


import com.tapestry5inaction.entities.User;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

public class MessagesDemo {

    @Inject
    private Messages messages;

    @SessionState(create = false)
    private User user;

    public String getWelcomeMessage() {
        if (user == null) {
            return messages.get("welcome-guest");
        }
        return messages.format("welcome-user", user.getName());
    }

}