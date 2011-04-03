package com.tapestry5inaction.tlog.services.impl;

import com.tapestry5inaction.tlog.core.entities.*;
import com.tapestry5inaction.tlog.services.BlogService;
import com.tapestry5inaction.tlog.services.PageableLoopDataSource;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import static org.hibernate.criterion.Order.desc;

public class BlogServiceImpl implements BlogService {

    @Inject
    private Session session;

    public Blog findBlog() {
        return (Blog) this.session.createCriteria(Blog.class).uniqueResult();
    }

    public PageableLoopDataSource findRecentArticles() {
        return new PageableLoopDataSourceImpl(session, Article.class);
    }

    public PageableLoopDataSource findArticles(final Month month) {
        return new PageableLoopDataSourceImpl(session, Article.class, new AdditionalConstraintsCallback() {
            public void apply(Criteria criteria) {
                criteria.add(Restrictions.between("publishDate", month.getStart(), month.getEnd()));
            }
        });
    }

    public PageableLoopDataSource findArticles(final Tag tag) {
        return new PageableLoopDataSourceImpl(session, Article.class, new AdditionalConstraintsCallback() {
            public void apply(Criteria criteria) {
                criteria.createCriteria("tags").add(
                        Restrictions.eq("name", tag.getName()));
            }
        });
    }

    public User findUserByName(final String name) {
        return (User) session.createCriteria(User.class)
                .add(Restrictions.eq("name", name)).uniqueResult();
    }

    public PageableLoopDataSource findArticles(final String term) {
        return new PageableLoopDataSourceImpl(session, Article.class, new AdditionalConstraintsCallback() {
            public void apply(Criteria criteria) {
                criteria.add(Restrictions.or(like("title", term), like("content", term)));
            }
        });
    }

    private Criterion like(String property, String value) {
        return Restrictions.ilike(property, "%" + value + "%");
    }

}
