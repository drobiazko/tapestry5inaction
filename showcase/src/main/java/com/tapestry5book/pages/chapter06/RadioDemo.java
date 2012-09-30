package com.tapestry5book.pages.chapter06;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

public class RadioDemo {

    @Persist
    @Property
    private String gender;

    void onSuccess(){
        //Do something here
    }
}