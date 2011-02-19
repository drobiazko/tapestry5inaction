package com.tapestry5inaction.tlog.tags.pages;

import com.tapestry5inaction.tlog.core.entities.Tag;
import com.tapestry5inaction.tlog.core.services.StartPageLinkSource;
import com.tapestry5inaction.tlog.tags.services.TagService;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.List;

public class SidebarBlocks {

    @Inject
    private TagService tagService;

    @Inject
    private StartPageLinkSource startPageLinkSource;

    @Property
    private Tag currentTag;

    public List<Tag> getTags() {
        return tagService.findTags();
    }

    public Link getLink() {
        return startPageLinkSource.getLink(currentTag);
    }

}
