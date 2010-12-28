package com.tapestry5inaction.tlog.pages;

import com.tapestry5inaction.tlog.annotations.PublicPage;
import com.tapestry5inaction.tlog.entities.Article;
import com.tapestry5inaction.tlog.services.BlogService;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.List;

@PublicPage
public class Index {

    @Inject
    private BlogService blogService;

    @Property
    private List<Article> articles;

    void pageAttached() {
        this.articles = this.blogService.findRecentArticles();
    }
}
