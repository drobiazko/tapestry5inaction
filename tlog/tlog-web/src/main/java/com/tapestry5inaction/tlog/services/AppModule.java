package com.tapestry5inaction.tlog.services;

import com.tapestry5inaction.tlog.entities.Blog;
import com.tapestry5inaction.tlog.entities.Month;
import com.tapestry5inaction.tlog.pages.SidebarBlocks;
import com.tapestry5inaction.tlog.services.impl.*;
import com.tapestry5inaction.tlog.services.impl.MonthValueEncoder;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Autobuild;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.Startup;
import org.apache.tapestry5.ioc.annotations.SubModule;
import org.apache.tapestry5.services.*;

@SubModule(CoreModule.class)
public class AppModule {

    public static void bind(final ServiceBinder binder) {
        binder.bind(Authenticator.class, AuthenticatorImpl.class);
        binder.bind(GravatarService.class, GravatarServiceImpl.class);
        binder.bind(BlogService.class, BlogServiceImpl.class);
    }

    @Contribute(ApplicationStateManager.class)
    public static void provideApplicationStateContributions(
            final MappedConfiguration<Class, ApplicationStateContribution> configuration,
            final BlogService blogService) {

        final ApplicationStateCreator<Blog> creator = new ApplicationStateCreator<Blog>() {
            public Blog create() {
                return blogService.findBlog();
            }

        };
        configuration.add(Blog.class, new ApplicationStateContribution(
                PersistenceConstants.SESSION, creator));
    }

    @Contribute(SidebarBlockSource.class)
    public static void provideSideBlocks(
            final OrderedConfiguration<SidebarBlockContribution> configuration) {

        configuration.add("Pages", new SidebarBlockContribution(
                SidebarBlocks.class, "pages"), "before:*");

        configuration.add("Meta", new SidebarBlockContribution(
                SidebarBlocks.class, "meta"), "after:*");
    }

    @Contribute(ComponentRequestHandler.class)
    public static void provideComponentRequestFilters(
            OrderedConfiguration configuration) {
        configuration.addInstance("PageAccess", PageAccessFilter.class);
    }


    @Startup
    public static void initDemoData(@Autobuild
                                    final DemoDataSource source) {
        source.create();
    }
}
