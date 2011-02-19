package com.tapestry5inaction.tlog.archive.test;


import com.tapestry5inaction.tlog.archive.services.ArchiveModule;
import com.tapestry5inaction.tlog.core.entities.Article;
import com.tapestry5inaction.tlog.core.entities.Blog;
import org.apache.tapestry5.dom.Document;
import org.apache.tapestry5.hibernate.HibernateSessionManager;
import org.apache.tapestry5.test.PageTester;
import org.apache.tapestry5.test.TapestryTestCase;
import org.example.testapp.pages.SidebarBlocksDemo;
import org.hibernate.Session;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.GregorianCalendar;

public class SidebarBlocksTest extends TapestryTestCase {

    private PageTester tester;

    @BeforeMethod
    public void setUp() {
        tester = new PageTester("org.example.testapp", "app", "src/test/webapp", ArchiveModule.class);
    }


    @Test
    public void test() {
        HibernateSessionManager sessionManager = tester.getService(HibernateSessionManager.class);

        Session session = sessionManager.getSession();

        final Blog blog = new Blog();
        blog.setName("Tapestry 5 Blog");
        blog.setDescription("Thoughts on coding, technology and occasional stuff");

        sessionManager.getSession().save(blog);

        Article article = new Article();
        article.setBlog(blog);
        article.setTitle("Hello world!");
        article.setContent("Hello world!");
        article.setPublishDate(new GregorianCalendar(2011, 0, 28).getTime());

        session.save(article);

        sessionManager.commit();

        Document document = tester.renderPage(SidebarBlocksDemo.class.getSimpleName());

        String markup = document.toString();

        assertTrue(markup.contains("January 2011 (1)"));

        System.err.print(document);
    }
}
