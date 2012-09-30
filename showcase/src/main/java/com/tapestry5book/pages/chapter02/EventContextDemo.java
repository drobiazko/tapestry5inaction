package com.tapestry5book.pages.chapter02;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

public class EventContextDemo {

    @Property
    @Persist
    private int number;

    void onAction(Integer context) {
        number += context;
    }

}