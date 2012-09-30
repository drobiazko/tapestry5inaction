package com.tapestry5book.tlog.components;

import com.tapestry5book.tlog.core.entities.Blog;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;

public class Header {
    @SessionState
    @Property
    private Blog blog;
}
