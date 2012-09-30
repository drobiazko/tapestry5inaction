package com.tapestry5book.tlog.tags.services;


import com.tapestry5book.tlog.core.services.CoreModule;
import com.tapestry5book.tlog.core.services.SidebarBlockContribution;
import com.tapestry5book.tlog.core.services.SidebarBlockSource;
import com.tapestry5book.tlog.tags.pages.SidebarBlocks;
import com.tapestry5book.tlog.tags.services.impl.TagServiceImpl;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.SubModule;
import org.apache.tapestry5.services.ComponentClassResolver;
import org.apache.tapestry5.services.LibraryMapping;

@SubModule(CoreModule.class)
public class TagsModule {

    public static void bind(ServiceBinder binder) {
        binder.bind(TagService.class, TagServiceImpl.class);
    }

    @Contribute(ComponentClassResolver.class)
    public static void provideLibraryMapping(Configuration<LibraryMapping> configuration) {
        configuration.add(new LibraryMapping("tlt", "com.tapestry5inaction.tlog.tags"));
    }

    @Contribute(SidebarBlockSource.class)
    public static void provideSideBlocks(
            final OrderedConfiguration<SidebarBlockContribution> configuration) {

        configuration.add("Tags", new SidebarBlockContribution(
                SidebarBlocks.class, "tags"), "after:Archives");

    }
}
