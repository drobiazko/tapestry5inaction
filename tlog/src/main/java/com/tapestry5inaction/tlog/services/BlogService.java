package com.tapestry5inaction.tlog.services;

import com.tapestry5inaction.tlog.entities.*;

import java.util.List;

public interface BlogService {

    User findUserByName(String name);

    Blog findBlog();

    List<Article> findRecentArticles();

    List<Article> findArticles(Month month);

    List<Archive> findArchives();

    List<Article> findArticles(String term);
}
