package com.tapestry5book.tlog.services;

import com.tapestry5book.tlog.core.entities.*;

public interface BlogService {

    User findUserByName(String name);

    Blog findBlog();

    PageableLoopDataSource findRecentArticles();

    PageableLoopDataSource findArticles(Month month);

    PageableLoopDataSource findArticles(Tag tag);

    PageableLoopDataSource findArticles(String term);
}
