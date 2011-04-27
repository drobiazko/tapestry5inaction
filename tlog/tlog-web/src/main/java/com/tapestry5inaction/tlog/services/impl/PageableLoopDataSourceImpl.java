package com.tapestry5inaction.tlog.services.impl;


import com.tapestry5inaction.tlog.services.PageableLoopDataSource;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class PageableLoopDataSourceImpl implements PageableLoopDataSource {
    private final Session session;

    private final Class entityType;

    private final AdditionalConstraintsCallback callback;

    private List preparedResults;

    public PageableLoopDataSourceImpl(Session session, Class entityType) {
        this(session, entityType, new AdditionalConstraintsCallback() {
            public void apply(Criteria criteria) {
            }
        });
    }

    public PageableLoopDataSourceImpl(Session session, Class entityType, AdditionalConstraintsCallback callback) {
        this.session = session;
        this.entityType = entityType;
        this.callback = callback;
    }


    public int getAvailableRows() {
        Criteria criteria = session.createCriteria(entityType);

        published(criteria);

        callback.apply(criteria);

        criteria.setProjection(Projections.rowCount());

        Number result = (Number) criteria.uniqueResult();

        return result.intValue();
    }

    public void prepare(int startIndex, int endIndex) {
        Criteria criteria = session.createCriteria(entityType)
                .setFirstResult(startIndex).setMaxResults(endIndex - startIndex + 1)
                .addOrder(Order.desc("publishDate"));

        published(criteria);

        callback.apply(criteria);

        preparedResults = criteria.list();
    }

    public List<?> getRow() {
        return preparedResults;
    }

    private void published(Criteria criteria) {
        criteria.add(Restrictions.isNotNull("publishDate"));
    }
}
