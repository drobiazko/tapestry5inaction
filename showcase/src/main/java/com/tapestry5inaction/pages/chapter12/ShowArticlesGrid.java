package com.tapestry5inaction.pages.chapter12;

import com.tapestry5inaction.entities.Article;
import com.tapestry5inaction.services.BlogService;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.hibernate.HibernateGridDataSource;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class ShowArticlesGrid {

    @Inject
    private Session session;

    public GridDataSource getArticles() {
        return new HibernateGridDataSource(session, Article.class){
            @Override
            protected void applyAdditionalConstraints(Criteria criteria) {
                Date date = new GregorianCalendar(2008, 5, 6).getTime();

                criteria.add(Restrictions.gt("publishDate", date));
            }
        };
    }
}