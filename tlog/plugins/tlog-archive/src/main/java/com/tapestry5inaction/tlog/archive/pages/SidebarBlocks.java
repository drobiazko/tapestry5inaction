package com.tapestry5inaction.tlog.archive.pages;


import com.tapestry5inaction.tlog.RequestParameters;
import com.tapestry5inaction.tlog.archive.services.ArchiveService;
import com.tapestry5inaction.tlog.entities.Archive;
import com.tapestry5inaction.tlog.services.StartPageLinkSource;
import com.tapestry5inaction.tlog.services.impl.MonthValueEncoder;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

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
