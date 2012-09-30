package com.tapestry5book.pages.chapter02;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

public class OnEventAnnotationDemo {

    @Property
    @Persist
    private int number;

    @OnEvent(component = "byOne")
    void increment() {

        this.number++;
    }

    @OnEvent(value = EventConstants.ACTION, component = "byTwo")
    void incrementByTwo() {

        this.number += 2;
    }

}