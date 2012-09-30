package com.tapestry5book.tlog.pages;


import com.tapestry5book.tlog.core.annotations.PublicPage;
import com.tapestry5book.tlog.core.entities.Article;
import com.tapestry5book.tlog.services.BlogService;
import com.tapestry5book.tlog.services.PageableLoopDataSource;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

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
