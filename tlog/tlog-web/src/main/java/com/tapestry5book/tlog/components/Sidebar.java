package com.tapestry5book.tlog.components;

import com.tapestry5book.tlog.core.services.SidebarBlockSource;
import com.tapestry5book.tlog.pages.Search;
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
