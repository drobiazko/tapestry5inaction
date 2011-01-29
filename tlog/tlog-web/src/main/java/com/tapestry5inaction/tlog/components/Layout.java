package com.tapestry5inaction.tlog.components;

import com.tapestry5inaction.tlog.entities.Blog;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;

public class Layout {
    @SessionState
    @Property
    private Blog blog;
}
