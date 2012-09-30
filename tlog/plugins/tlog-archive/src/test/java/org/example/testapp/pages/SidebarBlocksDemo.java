package org.example.testapp.pages;


import com.tapestry5book.tlog.core.services.SidebarBlockSource;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.List;

public class SidebarBlocksDemo {

    @Inject
    private SidebarBlockSource sidebarBlockSource;

    public List<Block> getBlocks() {
        return sidebarBlockSource.getBlocks();
    }
}
