package com.tapestry5inaction.pagetests;

import org.apache.tapestry5.dom.Document;
import org.apache.tapestry5.test.PageTester;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.junit.Assert.assertTrue;


public class SimplePageTest {

    private PageTester pageTester;

    @BeforeClass
    public void setUp() {
        pageTester = new PageTester("com.tapestry5inaction", "app");
    }


    @AfterClass
    public void shutdown() {
        pageTester.shutdown();
    }

    @Test
    public void renderIndexPage() {
        Document document = pageTester.renderPage("index");

        assertTrue(document.toString().contains("Tapestry 5 Book"));
    }
}
