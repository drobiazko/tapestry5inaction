package com.tapestry5book.pagetests;

import org.apache.tapestry5.dom.Document;
import org.apache.tapestry5.test.PageTester;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testapp.pages.LoopTestPage;

import static org.testng.Assert.assertTrue;

public class LoopTest {

    private PageTester pageTester;

    @BeforeClass
    public void setUp() {
        pageTester = new PageTester("testapp", "app", "src/test/webapp");
    }


    @AfterClass
    public void shutdown() {
        pageTester.shutdown();
    }

    @Test
    public void test_loop() {
        Document document = pageTester.renderPage(LoopTestPage.class.getSimpleName());

        String markup = document.toString();

        assertTrue(markup.contains("1"));
        assertTrue(markup.contains("2"));
        assertTrue(markup.contains("3"));
        assertTrue(markup.contains("4"));
        assertTrue(markup.contains("5"));
    }
}
