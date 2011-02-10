package com.tapestry5inaction.tlog.services.impl;

import com.tapestry5inaction.tlog.blogroll.entities.ExternalBlog;
import com.tapestry5inaction.tlog.entities.Article;
import com.tapestry5inaction.tlog.entities.Blog;
import com.tapestry5inaction.tlog.entities.Tag;
import com.tapestry5inaction.tlog.entities.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tapestry5.hibernate.HibernateSessionManager;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;

public class DemoDataSource {

    @Inject
    private HibernateSessionManager sessionManager;

    public void create() {
        createUser();

        final Blog blog = createBlog();

        final Session session = this.sessionManager.getSession();

        Tag tag = newTag("Blog");
        Tag anotherTag = newTag("Uncategorized");
        session.save(tag);
        session.save(anotherTag);

        session.save(newArticle(
                blog,
                "Tag Hierarchy",
                newDate(2008, 6, 20),
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Fusce euismod commodo ante. Suspendisse potenti. Nunc pellentesque quam vel pede. Ut a lorem non urna molestie euismod. Fusce consequat tortor eu urna. Pellentesque aliquam, pede eget tincidunt feugiat, nunc massa hendrerit magna, non ultricies neque lectus nec dui. In hac habitasse platea dictumst. Sed feugiat quam eget lectus. Fusce at pede. Morbi sagittis tristique tortor. Sed erat justo, blandit ac, dignissim in, pretium ut, urna.",
                tag));

        session.save(newArticle(
                blog,
                "Hello world!",
                newDate(2008, 5, 6),
                "Welcome to Tapestry Blog. This is your first post. Edit or delete it, then start blogging!.",
                anotherTag));

        sessionManager.getSession().save(newExternalBlog("http://tapestry5.de", "http://tapestry5.de"));

        this.sessionManager.commit();
    }

    private User createUser() {
        final User user = new User();
        user.setName("admin");
        user.setPassword(DigestUtils.md5Hex("admin"));

        this.sessionManager.getSession().save(user);
        return user;
    }

    private Blog createBlog() {
        final Blog blog = new Blog();
        blog.setName("Tapestry 5 Blog");
        blog.setDescription("Thoughts on coding, technology and occasional stuff");

        this.sessionManager.getSession().save(blog);

        return blog;
    }

    private Article newArticle(final Blog blog, final String title,
                               final Date publishDate, final String content, Tag... tags) {
        final Article article = new Article();
        article.setBlog(blog);
        article.setTitle(title);
        article.setPublishDate(publishDate);
        article.setContent(content);

        if (tags != null) {
            article.getTags().addAll(Arrays.asList(tags));
        }
        return article;
    }

    private Tag newTag(String name) {
        Tag tag = new Tag();
        tag.setName(name);

        return tag;
    }

    private ExternalBlog newExternalBlog(String name, String uri) {
        ExternalBlog blog = new ExternalBlog();
        blog.setName(name);
        blog.setUri(uri);

        return blog;
    }

    private Date newDate(int year, int month, int day) {
        return new GregorianCalendar(year, month, day).getTime();
    }

}
