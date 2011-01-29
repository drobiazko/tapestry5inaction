package com.tapestry5inaction.tlog.archive.pages;


import com.tapestry5inaction.tlog.RequestParameters;
import com.tapestry5inaction.tlog.archive.services.ArchiveService;
import com.tapestry5inaction.tlog.entities.Archive;
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
    private PageRenderLinkSource pageRenderLinkSource;

    @Property
    private Archive currentArchive;

    public List<Archive> getArchives() {
        return archiveService.findArchives();
    }

    public Link getLink() {
        MonthValueEncoder encoder = new MonthValueEncoder();

        Link link = pageRenderLinkSource.createPageRenderLink("index");
        link.addParameter(RequestParameters.MONTH, encoder.toClient(currentArchive.getMonth()));

        return link;
    }
}
