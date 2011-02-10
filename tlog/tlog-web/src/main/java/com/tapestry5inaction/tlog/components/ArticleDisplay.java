package com.tapestry5inaction.tlog.components;

import com.tapestry5inaction.tlog.entities.Article;
import com.tapestry5inaction.tlog.entities.Tag;
import com.tapestry5inaction.tlog.services.StartPageLinkSource;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

public class ArticleDisplay {

    @Parameter(required = true)
    @Property
    private Article article;

    @Property
    private Tag currentTag;


    @Inject
    private StartPageLinkSource startPageLinkSource;

    public Link getTagLink() {
        return startPageLinkSource.getLink(currentTag);
    }
}
