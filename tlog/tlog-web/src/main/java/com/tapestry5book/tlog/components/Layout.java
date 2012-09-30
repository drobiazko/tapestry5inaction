package com.tapestry5book.tlog.components;

import com.tapestry5book.tlog.core.entities.Blog;
import com.tapestry5book.tlog.core.services.SkinManager;
import com.tapestry5book.tlog.core.services.SkinResources;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.Resource;
import org.apache.tapestry5.ioc.annotations.Inject;

public class Layout {
    @SessionState
    @Property
    private Blog blog;

    @Inject
    private SkinManager skinManager;


    public Resource getSkin() {
        SkinResources resources = skinManager.getSkinResources(blog.getSkin());

        return resources.getTemplate();
    }
}
