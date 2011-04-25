package com.tapestry5inaction.pages.chapter06;


import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

import java.util.Date;

public class DateFieldDemo {

    @Persist
    @Property
    private Date birthday;

    @Property
    private String message;

    void onSuccess() {
        //Process birthday here
   }

}