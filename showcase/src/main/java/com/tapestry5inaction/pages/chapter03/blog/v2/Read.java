package com.tapestry5inaction.pages.chapter03.blog.v2;

import com.tapestry5inaction.entities.Article;
import org.apache.tapestry5.annotations.Property;

public class Read {

    @Property
    private Article article;

    void onActivate(Article article) {
        this.article = article;
    }

    Article onPassivate() {
        return article;
    }
}