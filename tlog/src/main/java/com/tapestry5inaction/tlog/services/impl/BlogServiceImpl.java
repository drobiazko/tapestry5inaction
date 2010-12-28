package com.tapestry5inaction.tlog.services.impl;

import com.tapestry5inaction.tlog.entities.Article;
import com.tapestry5inaction.tlog.entities.Blog;
import com.tapestry5inaction.tlog.entities.User;
import com.tapestry5inaction.tlog.services.BlogService;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

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

    public User findUserByName(final String name) {
        return (User) session.createCriteria(User.class)
                .add(Restrictions.eq("name", name)).uniqueResult();
    }

}
