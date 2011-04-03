package com.tapestry5inaction.tlog.pages;


import com.tapestry5inaction.tlog.core.annotations.PublicPage;
import com.tapestry5inaction.tlog.core.entities.Article;
import com.tapestry5inaction.tlog.services.BlogService;
import com.tapestry5inaction.tlog.services.PageableLoopDataSource;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.List;

@PublicPage
public class Search {

    @PageActivationContext
    private String term;

    @Inject
    private BlogService blogService;

    @Property
    private PageableLoopDataSource articles;

    @Property
    private Article currentArticle;


    void beginRender() {
        this.articles = this.blogService.findArticles(term);
    }
}
