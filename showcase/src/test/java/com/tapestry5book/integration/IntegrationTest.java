package com.tapestry5book.integration;

import org.apache.tapestry5.test.SeleniumTestCase;
import org.testng.annotations.Test;

public class IntegrationTest extends SeleniumTestCase {

    private static final String LINK = "//a[text()=\"Increment\"]";
    private static final String SUBMIT = "//input[@type='submit']";

    @Test
    public void clickLink() {
        open("chapter02/ActionLinkDemo");

        waitForPageToLoad();

        assertTextPresent("Current value: 0");

        click(LINK);

        waitForPageToLoad();

        assertTextPresent("Current value: 1");

        click(LINK);

        waitForPageToLoad();

        assertTextPresent("Current value: 2");

    }

    @Test
    public void login() {
        open("/chapter06/blog/login");

        waitForPageToLoad();

        type("userName", "admin");

        click(SUBMIT);

        waitForPageToLoad();

        assertTextPresent("Password is required");

        type("password", "wrong");

        click(SUBMIT);

        waitForPageToLoad();

        assertTextPresent("Invalid user name or password");

        type("password", "admin");

        click(SUBMIT);

        waitForPageToLoad();

        assertTextPresent("Welcome, admin!");
    }
}
