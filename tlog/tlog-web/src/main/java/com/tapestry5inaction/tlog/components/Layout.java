package com.tapestry5inaction.tlog.components;

import com.tapestry5inaction.tlog.core.entities.Blog;
import com.tapestry5inaction.tlog.core.services.Skin;
import com.tapestry5inaction.tlog.core.services.SkinManager;
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


    public Resource getSkin(){
        return skinManager.getSkinTemplate(new Skin("default", "1.0.0"));
    }
}
