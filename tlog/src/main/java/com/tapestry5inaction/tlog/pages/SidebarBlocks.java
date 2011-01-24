package com.tapestry5inaction.tlog.pages;

import com.tapestry5inaction.tlog.RequestParameters;
import com.tapestry5inaction.tlog.entities.Archive;
import com.tapestry5inaction.tlog.entities.Month;
import com.tapestry5inaction.tlog.entities.Tag;
import com.tapestry5inaction.tlog.services.BlogService;
import com.tapestry5inaction.tlog.services.impl.MonthValueEncoder;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.ValueEncoderFactory;

import java.util.List;

public class SidebarBlocks {

    @Inject
    private BlogService blogService;

    @Inject
    private PageRenderLinkSource pageRenderLinkSource;

    @Property
    private Archive currentArchive;

    @Property
    private Tag currentTag;

    public List<Archive> getArchives() {
        return blogService.findArchives();
    }

    public Link getLink() {
        MonthValueEncoder encoder = new MonthValueEncoder();

        Link link = pageRenderLinkSource.createPageRenderLink(Index.class);
        link.addParameter(RequestParameters.MONTH, encoder.toClient(currentArchive.getMonth()));

        return link;
    }

}
