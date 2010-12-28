package com.tapestry5inaction.tlog.services.impl;

import com.tapestry5inaction.tlog.entities.Article;
import com.tapestry5inaction.tlog.entities.Blog;
import com.tapestry5inaction.tlog.entities.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tapestry5.hibernate.HibernateSessionManager;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import java.util.Date;

public class DemoDataSource {

    @Inject
    private HibernateSessionManager sessionManager;

    public void create() {
        createUser();

        final Blog blog = createBlog();

        createArticles(blog);

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

    private void createArticles(final Blog blog) {
        final Session session = this.sessionManager.getSession();
        session.save(newArticle(
                blog,
                "Category Hierarchy",
                new Date(),
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Fusce euismod commodo ante. Suspendisse potenti. Nunc pellentesque quam vel pede. Ut a lorem non urna molestie euismod. Fusce consequat tortor eu urna. Pellentesque aliquam, pede eget tincidunt feugiat, nunc massa hendrerit magna, non ultricies neque lectus nec dui. In hac habitasse platea dictumst. Sed feugiat quam eget lectus. Fusce at pede. Morbi sagittis tristique tortor. Sed erat justo, blandit ac, dignissim in, pretium ut, urna."));
        session.save(newArticle(
                blog,
                "Hello world!",
                new Date(),
                "Welcome to WordPress. This is your first post. Edit or delete it, then start blogging!."));

    }

    private Article newArticle(final Blog blog, final String title,
                               final Date publishDate, final String content) {
        final Article article = new Article();
        article.setBlog(blog);
        article.setTitle(title);
        article.setPublishDate(publishDate);
        article.setContent(content);
        return article;
    }

}
