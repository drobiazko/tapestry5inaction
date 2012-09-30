package com.tapestry5book.pagetests;

import org.apache.tapestry5.dom.Document;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.test.PageTester;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LoginTest {

    private PageTester pageTester;

    @BeforeClass
    public void setUp() {
        pageTester = new PageTester("com.tapestry5book", "app");
    }


    @AfterClass
    public void shutdown() {
        pageTester.shutdown();
    }

    @Test
    public void login() {
        Document document = pageTester.renderPage("chapter06/blog/login");

        Element loginForm = document.getElementById("loginForm");

        assertNotNull(loginForm);

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("userName", "admin");
        document = pageTester.submitForm(loginForm, parameters);

        assertTrue(document.toString().contains("Password is required"));

        parameters.put("password", "wrong");
        document = pageTester.submitForm(loginForm, parameters);

        assertTrue(document.toString().contains("Invalid user name or password"));

        parameters.put("password", "admin");
        document = pageTester.submitForm(loginForm, parameters);

        assertTrue(document.toString().contains("Welcome, admin!"));
    }
}
