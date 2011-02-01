package com.tapestry5inaction.tlog.blogroll.services.impl;


import com.tapestry5inaction.tlog.blogroll.entities.ExternalBlog;
import com.tapestry5inaction.tlog.blogroll.services.BlogrollService;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import java.util.List;

public class BlogrollServiceImpl implements BlogrollService {

    @Inject
    private Session session;

    public List<ExternalBlog> findBlogs() {
        return session.createCriteria(ExternalBlog.class).list();
    }
}
