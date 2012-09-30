package com.tapestry5book.pages.chapter02;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

public class EventLinkDemo {

    @Property
    @Persist
    private int number;

    void onIncrement() {
        this.number++;
    }
}