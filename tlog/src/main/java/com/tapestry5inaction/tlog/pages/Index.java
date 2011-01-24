package com.tapestry5inaction.tlog.pages;

import com.tapestry5inaction.tlog.RequestParameters;
import com.tapestry5inaction.tlog.annotations.PublicPage;
import com.tapestry5inaction.tlog.entities.Article;
import com.tapestry5inaction.tlog.entities.Month;
import com.tapestry5inaction.tlog.services.BlogService;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.RequestParameter;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.Date;
import java.util.List;

@PublicPage
public class Index {

    @Inject
    private BlogService blogService;

    @Property
    private List<Article> articles;

    @ActivationRequestParameter(RequestParameters.MONTH)
    private Month month;

    void onActivate() {
        if (month != null) {
            this.articles = this.blogService.findArticles(month);
        } else {
            this.articles = this.blogService.findRecentArticles();
        }
    }
}
