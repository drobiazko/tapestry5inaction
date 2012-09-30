package com.tapestry5book.pages.chapter06.blog.v3;

import com.tapestry5book.entities.Article;
import com.tapestry5book.entities.Comment;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;

public class Read {

    @PageActivationContext
    @Property
    private Article article;

    @Property
    private Comment comment;

    @Property
    private Comment currentComment;

    void onPrepare() {
        comment = new Comment();
    }

    @CommitAfter
    void onSuccess() {
        article.getComments().add(comment);
    }
}