package com.tapestry5book.tlog.services.impl;

import com.tapestry5book.tlog.services.PluginPageManager;
import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ContextPathEncoder;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.PageRenderRequestParameters;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.linktransform.PageRenderLinkTransformer;

public class PluginPageLinkTransformer implements PageRenderLinkTransformer {

    private static final String PLUGIN_PAGE_PATH_PREFIX = "/admin/plugins/";

    @Inject
    private PageRenderLinkSource pageRenderLinkSource;

    @Inject
    private Request request;

    @Inject
    private PluginPageManager pluginPageManager;

    @Inject
    private ContextPathEncoder contextPathEncoder;


    public PageRenderRequestParameters decodePageRenderRequest(
            Request request) {
        String path = request.getPath();

        if (path.startsWith(PLUGIN_PAGE_PATH_PREFIX)) {

            String pageName = path.substring(PLUGIN_PAGE_PATH_PREFIX.length());

            EventContext context = contextPathEncoder.decodePath(path);

            return new PageRenderRequestParameters(pageName, context, false);
        }

        return null;
    }

    public Link transformPageRenderLink(
            Link defaultLink,
            PageRenderRequestParameters parameters) {

        String pageName = parameters.getLogicalPageName();

        if (pluginPageManager.isPluginPage(pageName)) {

            String basePath = String.format("%s%s%s", request.getContextPath(), PLUGIN_PAGE_PATH_PREFIX, pageName);

            return defaultLink.copyWithBasePath(basePath);
        }

        return defaultLink;
    }
}