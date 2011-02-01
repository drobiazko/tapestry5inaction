package com.tapestry5inaction.tlog.blogroll.services;


import com.tapestry5inaction.tlog.blogroll.entities.ExternalBlog;

import java.util.List;

public interface BlogrollService {

    List<ExternalBlog> findBlogs();
}
