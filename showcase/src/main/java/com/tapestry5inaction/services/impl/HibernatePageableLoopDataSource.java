package com.tapestry5inaction.services.impl;

import com.tapestry5inaction.services.PageableLoopDataSource;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class HibernatePageableLoopDataSource implements PageableLoopDataSource {
    private final Session session;

    private final Class entityType;

    private List preparedResults;

    public HibernatePageableLoopDataSource(Session session, Class entityType) {
        this.session = session;
        this.entityType = entityType;
    }


    public int getAvailableRows() {
        Criteria criteria = session.createCriteria(entityType);

        published(criteria);

        criteria.setProjection(Projections.rowCount());

        Number result = (Number) criteria.uniqueResult();

        return result.intValue();
    }

    public void prepare(int startIndex, int endIndex) {
        Criteria criteria = session.createCriteria(entityType)
                .setFirstResult(startIndex).setMaxResults(endIndex - startIndex + 1)
                .addOrder(Order.desc("publishDate"));

        published(criteria);

        preparedResults = criteria.list();
    }

    public List<?> getRow() {
        return preparedResults;
    }

    private void published(Criteria criteria) {
        criteria.add(Restrictions.isNotNull("publishDate"));
    }
}