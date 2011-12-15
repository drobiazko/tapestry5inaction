package com.tapestry5inaction.pages.chapter09.blog;

import com.tapestry5inaction.entities.Article;
import com.tapestry5inaction.services.BlogService;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.List;

public class Index {

    @PageActivationContext
    @Property
    private int currentPage;

    @Inject
    private BlogService blogService;

    @Property
    private List<Article> articles;

    @Property
    private Article currentArticle;

    void onActivate() {
        articles = blogService.findRecentArticles();
    }
}