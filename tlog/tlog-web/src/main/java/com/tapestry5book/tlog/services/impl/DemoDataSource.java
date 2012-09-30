package com.tapestry5book.tlog.services.impl;

import com.tapestry5book.tlog.blogroll.entities.ExternalBlog;
import com.tapestry5book.tlog.core.entities.Article;
import com.tapestry5book.tlog.core.entities.Blog;
import com.tapestry5book.tlog.core.entities.Tag;
import com.tapestry5book.tlog.skins.SkinConstants;
import org.apache.tapestry5.hibernate.HibernateSessionManager;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DemoDataSource {

    @Inject
    private HibernateSessionManager sessionManager;

    @Inject
    private DemoDataParser demoDataParser;

    public void create() {
        DemoDataParser.DemoData demoData = demoDataParser.parse(getClass().getResource("demodata.xml"));


        persistAll(demoData.getUsers());

        final Blog blog = demoData.getBlog();
        persistBlog(blog);

        List<Tag> tags = demoData.getTags();
        persistAll(tags);

        List<Article> articles = demoData.getArticles();

        addTag(articles, tags, 0, 0);
        addTag(articles, tags, 1, 1);

        persistAll(articles);

        sessionManager.getSession().save(newExternalBlog("http://tapestry5.de", "http://tapestry5.de"));

        this.sessionManager.commit();
    }

    private <T> void persistAll(List<T> list) {
        for (T next : list) {
            sessionManager.getSession().save(next);
        }

        this.sessionManager.commit();
    }

    private void addTag(List<Article> articles, List<Tag> tags, int articleIndex, int tagIndex) {
        if (articleIndex < articles.size() && tagIndex < tags.size()) {
            Article article = articles.get(articleIndex);

            article.getTags().add(tags.get(tagIndex));
        }
    }

    private void persistBlog(Blog blog) {
        blog.setSkin(SkinConstants.DEFAULT_SKIN);
        this.sessionManager.getSession().save(blog);
        this.sessionManager.commit();
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
