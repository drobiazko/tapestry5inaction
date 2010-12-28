package com.tapestry5inaction.tlog.components;

import com.tapestry5inaction.tlog.services.SidebarBlockSource;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.List;

public class Sidebar {
    @Inject
    private SidebarBlockSource blockSource;

    @Property
    private List<Block> blocks;

    @Property
    private String searchPattern;

    void setupRender() {
        this.blocks = this.blockSource.getBlocks();
    }
}
