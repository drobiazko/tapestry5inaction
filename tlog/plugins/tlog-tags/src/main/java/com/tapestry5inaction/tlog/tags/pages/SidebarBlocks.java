package com.tapestry5inaction.tlog.tags.pages;

import com.tapestry5inaction.tlog.entities.Tag;
import com.tapestry5inaction.tlog.tags.services.TagService;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.List;

public class SidebarBlocks {

    @Inject
    private TagService tagService;

    @Property
    private Tag currentTag;

    public List<Tag> getTags(){
        return tagService.findTags();
    }

}
