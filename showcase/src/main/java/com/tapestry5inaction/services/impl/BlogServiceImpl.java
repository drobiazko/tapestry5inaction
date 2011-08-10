package com.tapestry5inaction.services.impl;

import com.tapestry5inaction.entities.Article;
import com.tapestry5inaction.entities.Blog;
import com.tapestry5inaction.services.BlogService;
import com.tapestry5inaction.services.PageableLoopDataSource;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import java.util.List;

public class BlogServiceImpl implements BlogService {

    @Inject
    private Session session;

    public Blog findBlog() {
        return (Blog) this.session.createCriteria(Blog.class).uniqueResult();
    }

    public List<Article> findRecentArticles() {
        return session.createCriteria(Article.class).list();
    }

    public PageableLoopDataSource findRecentArticles2() {
        return new HibernatePageableLoopDataSource(session, Article.class);
    }

    public Article findArticleById(Long id) {
        return (Article) session.get(Article.class, id);
    }

    public void persistArticle(Article article) {
        this.session.saveOrUpdate(article);
    }
}
