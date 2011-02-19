package com.tapestry5inaction.tlog.services;

import com.tapestry5inaction.tlog.core.entities.*;

import java.util.List;

public interface BlogService {

    User findUserByName(String name);

    Blog findBlog();

    PageableLoopDataSource findRecentArticles();

    PageableLoopDataSource findArticles(Month month);

    PageableLoopDataSource findArticles(Tag tag);

    List<Article> findArticles(String term);
}
