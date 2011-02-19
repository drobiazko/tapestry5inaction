package com.tapestry5inaction.tlog.components.admin;

import com.tapestry5inaction.tlog.core.entities.Blog;
import com.tapestry5inaction.tlog.core.entities.User;
import com.tapestry5inaction.tlog.services.PluginPageManager;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.List;

@Import(stylesheet = {"context:admin/static/style.css"})
public class Layout {
    @SessionState
    @Property
    private Blog blog;

    @Parameter(required = true, defaultPrefix = BindingConstants.MESSAGE)
    @Property
    private String title;

    @SessionState(create = false)
    @Property(write = false)
    private User user;

    @Property(write = false)
    private boolean userExists;

    @Inject
    private PluginPageManager pluginPageManager;

    @Inject
    private Messages messages;

    @Property
    private String currentPage;

    public List<String> getPluginPageNames(){
        return pluginPageManager.getPluginPageNames();
    }

    public String getCurrentLabel(){
        return messages.get(currentPage);
    }
}
