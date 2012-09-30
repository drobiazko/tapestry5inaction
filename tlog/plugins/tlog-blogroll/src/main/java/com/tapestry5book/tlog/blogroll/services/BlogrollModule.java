package com.tapestry5book.tlog.blogroll.services;


import com.tapestry5book.tlog.blogroll.pages.SidebarBlocks;
import com.tapestry5book.tlog.blogroll.services.impl.BlogrollServiceImpl;
import com.tapestry5book.tlog.core.services.CoreModule;
import com.tapestry5book.tlog.core.services.SidebarBlockContribution;
import com.tapestry5book.tlog.core.services.SidebarBlockSource;
import org.apache.tapestry5.hibernate.HibernateEntityPackageManager;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.Resource;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.SubModule;
import org.apache.tapestry5.ioc.annotations.Value;
import org.apache.tapestry5.services.ComponentClassResolver;
import org.apache.tapestry5.services.LibraryMapping;
import org.apache.tapestry5.services.messages.ComponentMessagesSource;

@SubModule(CoreModule.class)
public class BlogrollModule {

    public static void bind(ServiceBinder binder) {
        binder.bind(BlogrollService.class, BlogrollServiceImpl.class);
    }

    @Contribute(ComponentClassResolver.class)
    public static void provideLibraryMapping(Configuration<LibraryMapping> configuration) {
        configuration.add(new LibraryMapping("blogroll", "com.tapestry5book.tlog.blogroll"));
    }

    @Contribute(HibernateEntityPackageManager.class)
    public static void provideHibernateEntityPackages(Configuration<String> configuration) {
        configuration.add("com.tapestry5book.tlog.blogroll.entities");
    }


    @Contribute(SidebarBlockSource.class)
    public static void provideSideBlocks(
            final OrderedConfiguration<SidebarBlockContribution> configuration) {

        configuration.add("Blogroll", new SidebarBlockContribution(
                SidebarBlocks.class, "blogroll"), "after:Tags");

    }

    @Contribute(ComponentMessagesSource.class)
    public static void provideAppCatalogMessages(
            @Value("/com/tapestry5book/tlog/blogroll/Messages.properties")
            Resource resource,
            OrderedConfiguration<Resource> configuration) {
        configuration.add("Blogroll", resource, "before:AppCatalog");
    }

}
