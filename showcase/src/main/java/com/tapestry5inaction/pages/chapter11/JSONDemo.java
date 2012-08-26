package com.tapestry5inaction.pages.chapter11;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.Request;

public class JSONDemo {

    @Property
    @Persist
    private int number;

    @Inject
    private Request request;

    Object onIncrement() {
        this.number++;

        JSONObject json = new JSONObject();

        json.put(
                "content",
                String.format("<p>Current value: %d</p>", number));

        return request.isXHR() ? json : this;
    }
}