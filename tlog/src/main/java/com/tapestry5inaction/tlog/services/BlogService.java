package com.tapestry5inaction.tlog.services;

import com.tapestry5inaction.tlog.entities.Archive;
import com.tapestry5inaction.tlog.entities.Article;
import com.tapestry5inaction.tlog.entities.Blog;
import com.tapestry5inaction.tlog.entities.User;

import java.util.List;

public interface BlogService {

    User findUserByName(String name);

    Blog findBlog();

    List<Article> findRecentArticles();

    List<Archive> findArchives();
}
