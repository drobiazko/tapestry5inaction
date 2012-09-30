package com.tapestry5book.pages.chapter03.blog.v2;

import com.tapestry5book.entities.Article;
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