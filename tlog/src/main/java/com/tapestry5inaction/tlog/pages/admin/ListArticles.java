package com.tapestry5inaction.tlog.pages.admin;

import com.tapestry5inaction.tlog.entities.Article;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.hibernate.HibernateGridDataSource;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

public class ListArticles {

    @Inject
    private Session session;

    @Property
    private Article currentArticle;

    public GridDataSource getArticles() {
        return new HibernateGridDataSource(this.session, Article.class);
    }
}
