package com.tapestry5inaction.tlog.pages;

import com.tapestry5inaction.tlog.entities.Archive;
import com.tapestry5inaction.tlog.services.BlogService;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.List;

public class SidebarBlocks {

    @Inject
    private BlogService blogService;

    @Property
    private Archive currentArchive;

    public List<Archive> getArchives(){
        return blogService.findArchives();
    }

}
