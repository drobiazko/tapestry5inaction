package com.tapestry5book.pagetests;

import org.apache.tapestry5.dom.Document;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.func.Predicate;
import org.apache.tapestry5.test.PageTester;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.junit.Assert.assertNotNull;
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

        Element link = document.getRootElement().getElement(new Predicate<Element>() {
            public boolean accept(Element element) {
                String href = element.getAttribute("href");

                return href != null && href.endsWith("/chapter04");
            }
        });

        assertNotNull(link);

        document = pageTester.clickLink(link);

        assertTrue(document.toString().contains("This chapter covers:"));
    }
}
