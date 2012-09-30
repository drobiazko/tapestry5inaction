package com.tapestry5book.pages.chapter02;

import java.util.List;

public class PropDemo {

    public String greetUser(String user) {
        return "Hello, " + user;
    }


    public String calculateSomething(List<Object> values) {
        return values.toString();
    }

}