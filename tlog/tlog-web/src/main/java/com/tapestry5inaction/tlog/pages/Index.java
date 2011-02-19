package com.tapestry5inaction.tlog.pages;

import com.tapestry5inaction.tlog.core.RequestParameters;
import com.tapestry5inaction.tlog.core.annotations.PublicPage;
import com.tapestry5inaction.tlog.core.entities.Month;
import com.tapestry5inaction.tlog.core.entities.Tag;
import com.tapestry5inaction.tlog.services.BlogService;
import com.tapestry5inaction.tlog.services.PageableLoopDataSource;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

@PublicPage
public class Index {

    @Inject
    private BlogService blogService;

    @ActivationRequestParameter(RequestParameters.MONTH)
    private Month month;

    @ActivationRequestParameter(RequestParameters.TAG)
    private Tag tag;

    @PageActivationContext
    @Property
    private int currentPage;

    @Property
    private PageableLoopDataSource source;

    void onActivate() {
        if (month != null) {
            this.source = this.blogService.findArticles(month);
        } else if (tag != null) {
            this.source = this.blogService.findArticles(tag);
        } else {
            this.source = this.blogService.findRecentArticles();
        }
    }

    void onGoToPage(int nextPage) {
        currentPage = nextPage;
    }
}
