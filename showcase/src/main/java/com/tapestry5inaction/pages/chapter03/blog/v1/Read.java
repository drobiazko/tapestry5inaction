package com.tapestry5inaction.pages.chapter03.blog.v1;

import com.tapestry5inaction.entities.Article;
import com.tapestry5inaction.services.BlogService;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

public class Read {
    @Inject
    private BlogService blogService;

    @Property
    private Article article;

    void onActivate(Long articleId) {
        this.article = blogService.findArticleById(articleId);
    }

    Long onPassivate() {
        return article.getId();
    }
}
