package com.tapestry5inaction.tlog.test;


import org.apache.tapestry5.test.SeleniumTestCase;
import org.testng.annotations.Test;

public class IntegrationTests extends SeleniumTestCase {

    private static final String SUBMIT = "//input[@type='submit']";

    @Test
    public void home() {
        open("/");

        assertTextPresent("Tapestry 5 Blog", "Welcome to Tapestry Blog.", "Lorem ipsum dolor sit amet");
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

        login();

        logout();

        assertTextPresent("Tapestry 5 Blog", "Welcome to Tapestry Blog.", "Lorem ipsum dolor sit amet");

    }


    @Test
    public void access_denied() {
        open("/admin/post");

        waitForPageToLoad();

        assertTextPresent("Tapestry 5 Blog - Administration", "User", "Password");

    }

    @Test
    public void archives() {
        open("/");

        click("//a[@href='/?m=2008-07']");

        waitForPageToLoad();

        assertTextPresent("Lorem ipsum dolor sit amet");

        click("//a[@href='/?m=2008-06']");

        waitForPageToLoad();

        assertTextPresent("Hello world!");
    }

    @Test
    public void tags() {
        open("/");

        click("//a[@href='/?t=Blog']");

        waitForPageToLoad();

        assertTextPresent("Lorem ipsum dolor sit amet");

        click("//a[@href='/?t=Uncategorized']");

        waitForPageToLoad();

        assertTextPresent("Hello world!");
    }

    @Test
    public void sidebar_tags() {
        open("/");

        click("//li[@class='cat-item']/a[@href='/?t=Blog']");

        waitForPageToLoad();

        assertTextPresent("Lorem ipsum dolor sit amet");

        click("//li[@class='cat-item']/a[@href='/?t=Uncategorized']");

        waitForPageToLoad();

        assertTextPresent("Hello world!");
    }

    @Test
    public void post() {
        open("/admin/");

        login();

        click("link=Post");

        waitForPageToLoad();

        click(SUBMIT);

        waitForPageToLoad();

        assertTextPresent("You must provide a value for Title.", "You must provide a value for Textarea.");

        type("title", "New Article");
        runScript("CKEDITOR.instances['textarea'].setData('New Content');");

        click(SUBMIT);

        waitForPageToLoad();

        assertTextPresent("The article has been saved");

        open("/");

        assertTextPresent("New Article", "New Content", "Lorem ipsum dolor sit amet");

        open("/admin/listarticles");


        click("link=New Article");

        waitForPageToLoad();

        type("title", "UPDATE: New Article");

        click(SUBMIT);

        waitForPageToLoad();

        assertTextPresent("The article has been saved");

        logout();

        assertTextPresent("UPDATE: New Article", "New Content", "Lorem ipsum dolor sit amet");

    }

    private void login() {

        type("userName", "admin");
        type("password", "admin");

        click(SUBMIT);

        waitForPageToLoad();

        assertTextPresent("Welcome admin");


    }

    private void logout() {
        click("link=Log out");

        waitForPageToLoad();
    }
}
