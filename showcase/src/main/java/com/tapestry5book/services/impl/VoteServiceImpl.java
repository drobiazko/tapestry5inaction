package com.tapestry5book.services.impl;

import com.tapestry5book.entities.Option;
import com.tapestry5book.entities.Vote;
import com.tapestry5book.services.VoteService;
import org.apache.tapestry5.hibernate.HibernateSessionManager;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

public class VoteServiceImpl implements VoteService {

    @Inject
    private HibernateSessionManager sessionManager;

    public Vote findById(Long id) {
        return (Vote) getSession().get(Vote.class, id);
    }

    public Option findOptionById(Long id) {
        return (Option) getSession().get(Option.class, id);
    }

    public void persist(Vote vote) {
        getSession().saveOrUpdate(vote);

        sessionManager.commit();
    }

    private Session getSession() {
        return sessionManager.getSession();
    }
}
