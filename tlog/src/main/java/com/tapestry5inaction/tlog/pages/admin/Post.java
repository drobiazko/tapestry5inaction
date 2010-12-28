package com.tapestry5inaction.tlog.pages.admin;

import com.tapestry5inaction.tlog.entities.Article;
import com.tapestry5inaction.tlog.entities.Blog;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

public class Post {

    @Property
    @PageActivationContext
    private Article article;

    @SessionState
    @Property
    private Blog blog;

    @Inject
    private Session session;

    @CommitAfter
    void onSuccess() {
        this.article.setBlog(this.blog);

        this.session.saveOrUpdate(this.article);
    }
}
