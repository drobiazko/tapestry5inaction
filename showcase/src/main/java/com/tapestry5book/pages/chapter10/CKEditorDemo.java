package com.tapestry5book.pages.chapter10;

import com.tapestry5book.entities.Article;
import org.apache.tapestry5.annotations.Property;

public class CKEditorDemo {

    @Property
    private Article article;

    void onPrepare() {
        if (article == null) {
            article = new Article();
        }
    }

    void onSuccess() {
        System.err.println(article.getContent());
    }
}