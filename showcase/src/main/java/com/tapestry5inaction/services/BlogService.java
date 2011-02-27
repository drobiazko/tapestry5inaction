package com.tapestry5inaction.services;

import com.tapestry5inaction.entities.Article;

import java.util.List;

public interface BlogService {

    List<Article> findRecentArticles();
}
