package com.tapestry5book.pages.chapter02;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

public class ActionLinkDemo {

    @Property
    @Persist
    private int number;

    void onAction() {
        this.number++;
    }
}