package com.tapestry5inaction.tlog.services.impl;

import com.tapestry5inaction.tlog.entities.*;
import com.tapestry5inaction.tlog.services.BlogService;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.hibernate.criterion.Order.desc;

public class BlogServiceImpl implements BlogService {

    @Inject
    private Session session;

    public Blog findBlog() {
        return (Blog) this.session.createCriteria(Blog.class).uniqueResult();
    }

    public List<Article> findRecentArticles() {
        return this.session.createCriteria(Article.class).addOrder(
                desc("publishDate")).setMaxResults(20).list();
    }

    public List<Article> findArticles(Month month) {
        return this.session.createCriteria(Article.class).add(
                Restrictions.between("publishDate", month.getStart(), month.getEnd()))
                .addOrder(desc("publishDate")).setMaxResults(20).list();
    }

    public List<Article> findArticles(Tag tag) {
        Criteria criteria = this.session.createCriteria(Article.class);

        criteria.createCriteria("tags").add(
                Restrictions.eq("name", tag.getName()));

        return criteria.addOrder(desc("publishDate")).setMaxResults(20).list();
    }

    public User findUserByName(final String name) {
        return (User) session.createCriteria(User.class)
                .add(Restrictions.eq("name", name)).uniqueResult();
    }

    public List<Article> findArticles(String term) {
        return this.session.createCriteria(Article.class)
                .add(Restrictions.or(like("title", term), like("content", term)))
                .addOrder(desc("publishDate")).setMaxResults(20).list();
    }

    private Criterion like(String property, String value) {
        return Restrictions.like(property, "%" + value + "%");
    }

}
