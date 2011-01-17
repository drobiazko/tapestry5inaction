package com.tapestry5inaction.tlog.services.impl;

import com.tapestry5inaction.tlog.entities.Article;
import com.tapestry5inaction.tlog.entities.Blog;
import com.tapestry5inaction.tlog.entities.User;
import com.tapestry5inaction.tlog.entities.Archive;
import com.tapestry5inaction.tlog.services.BlogService;
import org.apache.tapestry5.ioc.annotations.Inject;
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

    public User findUserByName(final String name) {
        return (User) session.createCriteria(User.class)
                .add(Restrictions.eq("name", name)).uniqueResult();
    }

    public List<Archive> findArchives() {
        List<Archive> archives = new ArrayList<Archive>();

        List result = session.createQuery("select count(id), year(publishDate), month(publishDate) " +
                "from Article group by year(publishDate), month(publishDate) " +
                "order by year(publishDate) desc, month(publishDate) desc").setMaxResults(10).list();

        for (Object next : result) {
            Object[] array = (Object[]) next;

            final Number count = (Number) array[0];

            Integer year = (Integer) array[1];
            Integer month = (Integer) array[2];
            final Date date = new GregorianCalendar(year, month - 1, 1).getTime();

            archives.add(new Archive(date, count));
        }

        return archives;
    }

    public List<Article> findArticles(String term) {
        System.err.println(term);
        return this.session.createCriteria(Article.class)
                .add(Restrictions.or(like("title", term), like("content", term)))
                .addOrder(desc("publishDate")).setMaxResults(20).list();
    }

    private Criterion like(String property, String value) {
        return Restrictions.like(property, "%" + value + "%");
    }

}
