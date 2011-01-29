package com.tapestry5inaction.tlog.services.impl;

import com.tapestry5inaction.tlog.services.SidebarBlockContribution;
import com.tapestry5inaction.tlog.services.SidebarBlockSource;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.internal.services.RequestPageCache;
import org.apache.tapestry5.internal.structure.Page;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.InjectResource;
import org.apache.tapestry5.services.ComponentClassResolver;

import java.util.ArrayList;
import java.util.List;

public class SidebarBlockSourceImpl implements SidebarBlockSource {

    @Inject
    private RequestPageCache pageCache;

    @Inject
    private ComponentClassResolver componentClassResolver;

    @InjectResource
    private List<SidebarBlockContribution> configuration;

    public List<Block> getBlocks() {
        final List<Block> result = new ArrayList<Block>();

        for (final SidebarBlockContribution contribution : this.configuration) {
            String pageClass = contribution.getPage().getName();

            String pageName = componentClassResolver.resolvePageClassNameToPageName(pageClass);


            final Page page = this.pageCache.get(pageName);

            final Block block = page.getRootElement().getBlock(
                    contribution.getBlockId());

            result.add(block);
        }
        return result;
    }

}
