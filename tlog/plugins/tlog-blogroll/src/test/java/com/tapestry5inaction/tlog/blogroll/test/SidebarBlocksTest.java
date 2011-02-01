package com.tapestry5inaction.tlog.blogroll.test;


import com.tapestry5inaction.tlog.blogroll.entities.ExternalBlog;
import com.tapestry5inaction.tlog.blogroll.services.BlogrollModule;
import com.tapestry5inaction.tlog.entities.Tag;
import org.apache.tapestry5.dom.Document;
import org.apache.tapestry5.hibernate.HibernateSessionManager;
import org.apache.tapestry5.test.PageTester;
import org.apache.tapestry5.test.TapestryTestCase;
import org.example.testapp.pages.SidebarBlocksDemo;
import org.hibernate.Session;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SidebarBlocksTest extends TapestryTestCase {

    private PageTester tester;

    @BeforeMethod
    public void setUp() {
        tester = new PageTester("org.example.testapp", "app", "src/test/webapp", BlogrollModule.class);
    }


    @Test
    public void test() {
        HibernateSessionManager sessionManager = tester.getService(HibernateSessionManager.class);

        Session session = sessionManager.getSession();

        ExternalBlog blog = new ExternalBlog();
        blog.setName("http://tapestry5.de");
        blog.setUri("http://tapestry5.de");

        session.save(blog);

        sessionManager.commit();

        Document document = tester.renderPage(SidebarBlocksDemo.class.getSimpleName());

        String markup = document.toString();

        assertTrue(markup.contains("Blogroll"));

        assertTrue(markup.contains("http://tapestry5.de"));
    }
}
