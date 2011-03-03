package com.tapestry5inaction.pages.chapter03.blog.v6;

import com.tapestry5inaction.entities.Article;
import com.tapestry5inaction.services.BlogService;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import java.util.List;

public class Index {

    @Inject
    private BlogService blogService;

    @Inject
    private PageRenderLinkSource pageRenderLinkSource;

    @Property
    private List<Article> articles;

    @Property
    private Article currentArticle;

    void onActivate() {
        articles = blogService.findRecentArticles();
    }

    public Link getLink() {
        Link link = pageRenderLinkSource.createPageRenderLinkWithContext(Read.class);

        link.addParameterValue("articleId", currentArticle);

        return link;
    }
}
