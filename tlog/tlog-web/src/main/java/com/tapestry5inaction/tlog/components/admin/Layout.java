package com.tapestry5inaction.tlog.components.admin;

import com.tapestry5inaction.tlog.entities.Blog;
import com.tapestry5inaction.tlog.entities.User;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;

@Import(stylesheet = {"context:admin/static/style.css"})
public class Layout {
    @SessionState
    @Property
    private Blog blog;

    @Parameter(required = true, defaultPrefix = BindingConstants.MESSAGE)
    @Property
    private String title;

    @Property
    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private Block sidebar;

    @SessionState(create = false)
    @Property(write = false)
    private User user;

    @Property(write = false)
    private boolean userExists;
}
