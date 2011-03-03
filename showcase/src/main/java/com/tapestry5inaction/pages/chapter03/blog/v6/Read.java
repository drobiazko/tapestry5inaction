package com.tapestry5inaction.pages.chapter03.blog.v6;


import com.tapestry5inaction.entities.Article;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.RequestParameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

public class Read {

    @Property(write = false)
    private Article article;

    @Inject
    private PageRenderLinkSource pageRenderLinkSource;

    void onActivate(@RequestParameter("articleId") Article article) {
        this.article = article;
    }

    public Link getLink() {
        Link link = pageRenderLinkSource.createPageRenderLinkWithContext(Read.class);

        link.addParameterValue("articleId", article);

        return link;
    }

}
