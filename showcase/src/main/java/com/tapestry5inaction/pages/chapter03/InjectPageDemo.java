package com.tapestry5inaction.pages.chapter03;


import com.tapestry5inaction.entities.Article;
import com.tapestry5inaction.services.BlogService;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import java.util.List;

public class InjectPageDemo {

    @Inject
    private BlogService blogService;

    @InjectPage
    private Read read;

    @Property
    private List<Article> articles;

    @Property
    private Article currentArticle;

    void onActivate() {
        articles = blogService.findRecentArticles();
    }

    public Object onReadArticle(Article article) {
        read.setArticle(article);
        return read;
    }
}
