package com.tapestry5inaction.services;

import com.tapestry5inaction.entities.Article;
import com.tapestry5inaction.entities.Blog;

import java.util.List;

public interface BlogService {

    Blog findBlog();

    List<Article> findRecentArticles();

    Article findArticleById(Long id);

    void persistArticle(Article article);
}
