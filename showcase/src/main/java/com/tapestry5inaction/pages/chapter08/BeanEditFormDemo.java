package com.tapestry5inaction.pages.chapter08;

import com.tapestry5inaction.entities.User;
import org.apache.tapestry5.annotations.Property;

public class BeanEditFormDemo {

    @Property
    private User user;

    void onPrepare(){
        user = new User();
    }
}