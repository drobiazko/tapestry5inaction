package com.tapestry5book.pages.chapter03.blog.v4;


import com.tapestry5book.entities.Article;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;

public class Read {

    @PageActivationContext
    @Property(write = false)
    private Article article;

    public void setArticle(Article article) {
        this.article = article;
    }
}
