package com.tapestry5book.components;

import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

public class Greeter {

    @Parameter(required = true)
    private String name;

    @Inject
    private Messages messages;

    public String getGreeting() {
        return messages.format("greeting", name);
    }
}