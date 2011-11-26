package com.tapestry5inaction.library.services;

import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.services.ComponentClassResolver;
import org.apache.tapestry5.services.LibraryMapping;

public class LibraryModule {

    @Contribute(ComponentClassResolver.class)
    public static void provideRootPackage(Configuration<LibraryMapping> configuration) {
        configuration.add(new LibraryMapping("lib", "com.tapestry5inaction.library"));
    }
}
