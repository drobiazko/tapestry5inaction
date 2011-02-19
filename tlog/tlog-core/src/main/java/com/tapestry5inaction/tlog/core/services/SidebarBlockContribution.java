package com.tapestry5inaction.tlog.core.services;

public final class SidebarBlockContribution {

    private final Class page;

    private final String blockId;

    public SidebarBlockContribution(final Class page, final String blockId) {
        this.page = page;
        this.blockId = blockId;
    }

    public String getBlockId() {
        return this.blockId;
    }

    public Class getPage() {
        return this.page;
    }

}