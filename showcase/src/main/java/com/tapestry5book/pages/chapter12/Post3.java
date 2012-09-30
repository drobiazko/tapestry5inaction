package com.tapestry5book.pages.chapter12;

import com.tapestry5book.entities.Article;
import com.tapestry5book.entities.Blog;
import com.tapestry5book.services.BlogService;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

public class Post3 {
    @Inject
    private BlogService blogService;

    @Inject
    private AlertManager alertManager;

    @Property
    private Article article;

    @SessionState
    @Property
    private Blog blog;

    void onPrepare() {
        article = new Article();
    }

    void onSuccess() {
        article.setBlog(blog);

        blogService.persistArticle(article);

        alertManager.info("Article saved successfully.");
    }
}