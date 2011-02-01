package com.tapestry5inaction.tlog.blogroll.pages;


import com.tapestry5inaction.tlog.blogroll.entities.ExternalBlog;
import com.tapestry5inaction.tlog.blogroll.services.BlogrollService;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.List;

public class SidebarBlocks {

    @Inject
    private BlogrollService blogrollService;

    @Property(write = false)
    private List<ExternalBlog> blogs;

    @Property
    private ExternalBlog currentBlog;

    void pageAttached(){
        this.blogs = blogrollService.findBlogs();
    }
}
