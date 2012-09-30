package com.tapestry5book.tlog.pages.admin;

import com.tapestry5book.tlog.core.entities.Article;
import com.tapestry5book.tlog.core.entities.Blog;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import java.util.Date;

public class Post {

    @Property
    @PageActivationContext(passivate = false)
    private Article article;

    @SessionState
    @Property
    private Blog blog;

    @Inject
    private Session session;

    @Inject
    private Messages messages;

    @Persist(PersistenceConstants.FLASH)
    @Property
    private String message;

    void onPrepare() {
        if (article == null) {
            article = new Article();
        }
    }

    void onPublish(){
        article.setPublishDate(new Date());
    }

    @CommitAfter
    void onSuccess() {
        this.article.setBlog(this.blog);

        this.session.saveOrUpdate(this.article);

        message = messages.get("success");
    }

    Object[] onPassivate() {
        if (article == null || article.getId() == null) {
            return new Object[]{};
        }

        return new Object[]{article};
    }
}
