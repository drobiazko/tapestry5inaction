package com.tapestry5inaction.tlog.services;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.ioc.annotations.UsesOrderedConfiguration;

import java.util.List;

@UsesOrderedConfiguration(SidebarBlockContribution.class)
public interface SidebarBlockSource {
    List<Block> getBlocks();
}
