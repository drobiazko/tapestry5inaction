package com.tapestry5inaction.pages.chapter08;

import com.tapestry5inaction.entities.User;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;

public class BeanDisplayDemo {

    @PageActivationContext
    @Property
    private User user;
}