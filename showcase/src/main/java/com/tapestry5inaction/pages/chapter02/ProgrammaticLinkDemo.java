package com.tapestry5inaction.pages.chapter02;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

public class ProgrammaticLinkDemo {

    @Persist
    @Property
    private int number;

    @Inject
    private ComponentResources componentResources;

    public Link getLink() {
        return this.componentResources.createEventLink("increment");
    }

    void onIncrement() {
        this.number++;
    }
}