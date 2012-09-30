package com.tapestry5book.tlog.blogroll.services;


import com.tapestry5book.tlog.blogroll.entities.ExternalBlog;

import java.util.List;

public interface BlogrollService {

    List<ExternalBlog> findBlogs();
}
