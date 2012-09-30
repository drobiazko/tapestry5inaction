package com.tapestry5book.tlog.tags.pages;

import com.tapestry5book.tlog.core.entities.Tag;
import com.tapestry5book.tlog.core.services.StartPageLinkSource;
import com.tapestry5book.tlog.tags.services.TagService;
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
