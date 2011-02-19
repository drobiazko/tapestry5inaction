package com.tapestry5inaction.tlog.pages;

import com.howardlewisship.tapx.kaptcha.components.KaptchaImage;
import com.tapestry5inaction.tlog.core.annotations.PublicPage;
import com.tapestry5inaction.tlog.core.entities.Article;
import com.tapestry5inaction.tlog.core.entities.Comment;
import com.tapestry5inaction.tlog.services.GravatarService;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

@PublicPage
public class Read {

    @PageActivationContext
    @Property
    private Article article;

    @Property
    @Persist
    private Comment comment;

    @Property
    private Comment currentComment;

    @Inject
    private GravatarService gravatarService;

    @Inject
    private Messages messages;

    @Component
    private KaptchaImage kaptchaImage;


    void setupRender() {
        if (comment == null) {
            comment = new Comment();
        }
    }

    @CommitAfter
    @DiscardAfter
    void onSuccess() {

        article.getComments().add(comment);
    }

    public String getAvatar() {
        return gravatarService.getAvatar(currentComment.getEmail());

    }

    public String getResponsesCountMessage() {
        return messages.format("responses-count", article.getComments().size(), article.getTitle());
    }


    public String getCaptchaText(){
        return kaptchaImage.getCaptchaText();
    }

}
