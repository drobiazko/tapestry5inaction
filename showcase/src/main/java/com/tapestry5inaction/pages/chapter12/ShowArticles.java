package com.tapestry5inaction.pages.chapter12;

import com.tapestry5inaction.entities.Article;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import java.util.List;

public class ShowArticles {

    @Inject
    private Session session;

    @Property
    private List<Article> articles;

    @Property
    private Article currentArticle;

    void onActivate() {
        articles = session
                .createCriteria(Article.class).list();
    }
}