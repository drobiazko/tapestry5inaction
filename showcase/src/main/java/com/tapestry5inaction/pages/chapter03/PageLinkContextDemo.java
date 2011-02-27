package com.tapestry5inaction.pages.chapter03;


import com.tapestry5inaction.entities.Article;
import com.tapestry5inaction.pages.Index;
import com.tapestry5inaction.services.BlogService;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.List;

public class PageLinkContextDemo {

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
