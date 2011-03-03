package com.tapestry5inaction.tlog.services.impl;

import com.tapestry5inaction.tlog.core.entities.Article;
import com.tapestry5inaction.tlog.core.entities.Blog;
import com.tapestry5inaction.tlog.core.entities.Tag;
import com.tapestry5inaction.tlog.core.entities.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tapestry5.test.TapestryTestCase;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DemoDataParserTest extends TapestryTestCase {

    @Test
    public void testParser() {
        DemoDataParser parser = new DemoDataParser(LoggerFactory.getLogger(DemoDataParser.class));


        DemoDataParser.DemoData demoData = parser.parse(DemoDataParser.class.getResource("demodata.xml"));

        Blog blog = demoData.getBlog();

        assertEquals(blog.getName(), "Tapestry 5 Blog");
        assertEquals(blog.getDescription(), "Thoughts on coding, technology and occasional stuff");


        List<Article> articles = demoData.getArticles();

        assertEquals(articles.size(), 2);

        assertArticle(articles.get(0), "Hello world!", newDate(2008, 5, 6));
        assertArticle(articles.get(1), "Tag Hierarchy", newDate(2008, 6, 20));

        List<User> users = demoData.getUsers();

        assertEquals(users.size(), 1);

        assertUser(users.get(0), "admin", "admin");

        List<Tag> tags = demoData.getTags();
        assertEquals(tags.size(), 2);
        assertTag(tags.get(0), "Uncategorized");
        assertTag(tags.get(1), "Blog");

    }


    private void assertArticle(Article article, String title, Date publishDate) {
        assertEquals(article.getTitle(), title);
        assertEquals(article.getPublishDate(), publishDate);
    }

    private void assertUser(User user, String name, String password) {
        assertEquals(user.getName(), name);
        assertEquals(user.getPassword(), DigestUtils.md5Hex(password));
    }

    private void assertTag(Tag tag, String name) {
        assertEquals(tag.getName(), name);
    }


    private Date newDate(int year, int month, int day) {
        return new GregorianCalendar(year, month, day).getTime();
    }
}
