package com.tapestry5book.pagetests;

import org.apache.tapestry5.dom.Document;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.func.Predicate;
import org.apache.tapestry5.test.PageTester;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ActionLinkDemoTest {
    private PageTester pageTester;

    @BeforeClass
    public void setUp() {
        pageTester = new PageTester("com.tapestry5inaction", "app", "src/main/webapp");
    }

    @Test
    public void clickLink() {
        Document document = pageTester.renderPage("chapter02/ActionLinkDemo");

        assertTextPresent(document, "Current value: 0");

        final Element link = document.getRootElement().getElement(new Predicate<Element>() {
            public boolean accept(Element element) {
                return element.getName().equals("a") && element.getChildMarkup().equals("Increment");
            }
        });

        document = pageTester.clickLink(link);

        assertTextPresent(document, "Current value: 1");

        document = pageTester.clickLink(link);

        assertTextPresent(document, "Current value: 2");
    }

    private void assertTextPresent(Document document, String text) {
        assertTrue(document.toString().contains(text));
    }

    @AfterClass
    public void shutdown() {
        pageTester.shutdown();
    }
}
