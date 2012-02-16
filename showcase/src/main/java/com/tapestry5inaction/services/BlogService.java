package com.tapestry5inaction.services;

import com.tapestry5inaction.entities.Article;
import com.tapestry5inaction.entities.Blog;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import java.util.List;

public interface BlogService {

    Blog findBlog();

    List<Article> findRecentArticles();

    Article findArticleById(Long id);

    @CommitAfter
    void persistArticle(Article article);
}
