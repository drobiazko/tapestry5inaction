package com.tapestry5inaction.test;

import com.tapestry5inaction.pages.PageableLoopDemo;
import org.apache.tapestry5.dom.Document;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.func.Predicate;
import org.apache.tapestry5.test.PageTester;
import org.apache.tapestry5.test.TapestryTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PageableLoopTest extends TapestryTestCase {

    private PageTester tester;

    @BeforeMethod
    public void setUp() {
        tester = new PageTester("com.tapestry5inaction", "app", "src/main/resources/com/tapestry5inaction/pages", TestModule.class);
    }


    @Test
    public void navigate() {
        Document document = tester.renderPage(PageableLoopDemo.class.getSimpleName());

        assertTextPresent(document, "A", "B", "C", "D", "E", "F", "G", "H", "I", "K");

        //Navigate forward by clicking the link

        Element olderEntriesLink = findLinkByLabel(document, "Older Entries");
        assertNotNull(olderEntriesLink);

        Element newerEntriesLink = findLinkByLabel(document, "Newer Entries");
        assertNull(newerEntriesLink);

        document = tester.clickLink(olderEntriesLink);

        assertTextPresent(document, "L", "M", "N", "O", "P", "Q", "R", "S", "T", "V");


        //Next page

        olderEntriesLink = findLinkByLabel(document, "Older Entries");
        assertNotNull(olderEntriesLink);

        newerEntriesLink = findLinkByLabel(document, "Newer Entries");
        assertNotNull(newerEntriesLink);

        document = tester.clickLink(olderEntriesLink);

        assertTextPresent(document, "X", "Y", "Z");

        //Now navigate backward

        olderEntriesLink = findLinkByLabel(document, "Older Entries");
        assertNull(olderEntriesLink);

        newerEntriesLink = findLinkByLabel(document, "Newer Entries");
        assertNotNull(newerEntriesLink);

        document = tester.clickLink(newerEntriesLink);

        assertTextPresent(document, "L", "M", "N", "O", "P", "Q", "R", "S", "T", "V");

        //Previous page

        olderEntriesLink = findLinkByLabel(document, "Older Entries");
        assertNotNull(olderEntriesLink);

        newerEntriesLink = findLinkByLabel(document, "Newer Entries");
        assertNotNull(newerEntriesLink);

        document = tester.clickLink(newerEntriesLink);

        assertTextPresent(document, "A", "B", "C", "D", "E", "F", "G", "H", "I", "K");

    }

    private Element findLinkByLabel(final Document document, final String label) {
        return document.getRootElement().getElement(new Predicate<Element>() {
            public boolean accept(Element element) {
                return "a".equals(element.getName()) && element.getChildMarkup().contains(label);
            }
        });
    }

    public void assertTextPresent(Document document, String... text) {
        final String markup = document.toString();

        for (String s : text) {
            assertTrue(markup.contains(s), String.format("%s is not present in the markup. %s", s, markup));
        }
    }
}
