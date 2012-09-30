package com.tapestry5book.pages.chapter08;

import com.tapestry5book.entities.User;
import org.apache.tapestry5.annotations.Property;

public class BeanEditFormDemo {

    @Property
    private User user;

    void onPrepare(){
        user = new User();
    }
}