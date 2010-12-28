package com.tapestry5inaction.tlog.test;


import org.apache.tapestry5.test.SeleniumTestCase;
import org.testng.annotations.Test;

public class IntegrationTests extends SeleniumTestCase {

    @Test
    public void home() {
        open("/");

        assertTextPresent("Tapestry 5 Blog", "Welcome to WordPress.", "Lorem ipsum dolor sit amet");
    }

    @Test
    public void leave_a_reply() {
        open("/");

        click("link=Hello world!");

        waitForPageToLoad();

        assertTextPresent("0 Responses to Hello world!");

        click("submit");

        waitForPageToLoad();

        assertTextPresent("0 Responses to Hello world!", "Please provide your name",
                "Please provide your email", "Please provide a comment");

        type("author", "Igor");
        type("email", "drobiazko@apache.org");
        type("website", "http://tapestry5.de");
        type("commentText", "Bla bla bla");

        click("submit");

        waitForPageToLoad();

        assertTextPresent("1 Responses to Hello world!", "Igor", "Bla bla bla");


    }
}
