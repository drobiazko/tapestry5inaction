package com.tapestry5inaction.tlog.services.impl;


import com.tapestry5inaction.tlog.annotations.PluginPage;
import com.tapestry5inaction.tlog.services.PluginPageManager;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ComponentClassResolver;

import java.util.ArrayList;
import java.util.List;

public class PluginPageManagerImpl implements PluginPageManager {

    @Inject
    private ComponentClassResolver componentClassResolver;

    public boolean isPluginPage(String pageName) {
        String pageClassName = componentClassResolver.resolvePageNameToClassName(pageName);

        Class pageClass = loadClass(pageClassName);

        return pageClass.isAnnotationPresent(PluginPage.class);
    }

    public List<String> getPluginPageNames() {
        List<String> result = new ArrayList<String>();

        List<String> pageNames = componentClassResolver.getPageNames();

        for (final String pageName : pageNames) {
            if (isPluginPage(pageName)) {
                result.add(pageName);
            }
        }

        return result;
    }

    private Class loadClass(final String className) {
        try {
            return Thread.currentThread()
                    .getContextClassLoader().loadClass(className);
        } catch (final ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}


