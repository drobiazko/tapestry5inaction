package com.tapestry5book.tlog.archive.services;


import com.tapestry5book.tlog.archive.pages.SidebarBlocks;
import com.tapestry5book.tlog.archive.services.impl.ArchiveServiceImpl;
import com.tapestry5book.tlog.core.services.CoreModule;
import com.tapestry5book.tlog.core.services.SidebarBlockContribution;
import com.tapestry5book.tlog.core.services.SidebarBlockSource;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.SubModule;
import org.apache.tapestry5.services.ComponentClassResolver;
import org.apache.tapestry5.services.LibraryMapping;

@SubModule(CoreModule.class)
public class ArchiveModule {

    public static void bind(ServiceBinder binder) {
        binder.bind(ArchiveService.class, ArchiveServiceImpl.class);
    }

    @Contribute(ComponentClassResolver.class)
    public static void provideLibraryMapping(Configuration<LibraryMapping> configuration){
        configuration.add(new LibraryMapping("tla", "com.tapestry5inaction.tlog.archive"));
    }

    @Contribute(SidebarBlockSource.class)
    public static void provideSideBlocks(
            final OrderedConfiguration<SidebarBlockContribution> configuration) {

        configuration.add("Archives", new SidebarBlockContribution(
                SidebarBlocks.class, "archives"), "after:Pages");

    }
}
