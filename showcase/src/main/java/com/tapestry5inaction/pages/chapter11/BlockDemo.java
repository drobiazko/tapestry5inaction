package com.tapestry5inaction.pages.chapter11;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;

public class BlockDemo {

    @Property
    @Persist
    private int number;

    @InjectComponent
    private Zone numberZone;

    @Inject
    private Request request;

    Object onIncrement() {
        this.number++;

        return request.isXHR() ? numberZone.getBody() : this;
    }
}