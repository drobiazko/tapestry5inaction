package com.tapestry5inaction.pages.chapter11;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;

public class JSONDemo {

    @Property
    @Persist
    private int number;

    @Inject
    private Block numberBlock;

    @Inject
    private Request request;

    Object onIncrement() {
        this.number++;

        return request.isXHR() ? numberBlock : this;
    }
}