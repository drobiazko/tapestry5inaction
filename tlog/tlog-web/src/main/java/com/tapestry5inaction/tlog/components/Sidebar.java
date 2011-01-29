package com.tapestry5inaction.tlog.components;

import com.tapestry5inaction.tlog.pages.Search;
import com.tapestry5inaction.tlog.services.SidebarBlockSource;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import java.util.List;

public class Sidebar {
    @Inject
    private SidebarBlockSource blockSource;

    @Inject
    private PageRenderLinkSource pageRenderLinkSource;

    @Property
    private List<Block> blocks;

    @Property
    private String searchTerm;

    void setupRender() {
        this.blocks = this.blockSource.getBlocks();
    }

    Object onSuccessFromSearchForm(){
        return pageRenderLinkSource.createPageRenderLinkWithContext(Search.class, searchTerm);
    }
}
