package com.tapestry5inaction.tlog.test;


import org.apache.tapestry5.test.SeleniumTestCase;
import org.testng.annotations.Test;

public class IntegrationTests extends SeleniumTestCase {

    private static final String SUBMIT = "//input[@type='submit']";

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

    @Test
    public void login_logout() {
        open("/");

        click("link=Log in");

        waitForPageToLoad();

        assertTextPresent("User", "Password");

        click(SUBMIT);

        waitForPageToLoad();

        assertTextPresent("User name is required", "Password is required");

        type("userName", "foo");
        type("password", "bar");

        click(SUBMIT);

        waitForPageToLoad();

        assertTextPresent("Invalid user name or password");

        type("userName", "admin");
        type("password", "admin");

        click(SUBMIT);

        waitForPageToLoad();

        assertTextPresent("Welcome admin");

        click("link=Log out");

        waitForPageToLoad();

        assertTextPresent("Tapestry 5 Blog", "Welcome to WordPress.", "Lorem ipsum dolor sit amet");

    }


    @Test
    public void access_denied() {
        open("/admin/post");

        waitForPageToLoad();

        assertTextPresent("Tapestry 5 Blog - Administration", "User", "Password");

    }
}
