package com.tapestry5inaction.services.impl;

import com.tapestry5inaction.entities.Article;
import com.tapestry5inaction.services.BlogService;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import java.util.List;

public class BlogServiceImpl implements BlogService {

    @Inject
    private Session session;

    public List<Article> findRecentArticles() {
        return session.createCriteria(Article.class).list();
    }
}
