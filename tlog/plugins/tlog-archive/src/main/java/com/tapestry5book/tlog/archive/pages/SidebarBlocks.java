package com.tapestry5book.tlog.archive.pages;


import com.tapestry5book.tlog.archive.services.ArchiveService;
import com.tapestry5book.tlog.core.entities.Archive;
import com.tapestry5book.tlog.core.services.StartPageLinkSource;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.List;

public class SidebarBlocks {

    @Inject
    private ArchiveService archiveService;

    @Inject
    private StartPageLinkSource startPageLinkSource;

    @Property
    private Archive currentArchive;

    public List<Archive> getArchives() {
        return archiveService.findArchives();
    }

    public Link getLink() {
        return startPageLinkSource.getLink(currentArchive.getMonth());
    }
}
