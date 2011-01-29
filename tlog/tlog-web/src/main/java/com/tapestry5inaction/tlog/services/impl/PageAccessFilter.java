package com.tapestry5inaction.tlog.services.impl;

import com.tapestry5inaction.tlog.annotations.PublicPage;
import com.tapestry5inaction.tlog.pages.admin.Login;
import com.tapestry5inaction.tlog.services.Authenticator;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.runtime.Component;
import org.apache.tapestry5.services.*;

import java.io.IOException;

public class PageAccessFilter implements ComponentRequestFilter {

    private final PageRenderLinkSource renderLinkSource;

    private final ComponentSource componentSource;

    private final Response response;

    private final Authenticator authenticator;

    public PageAccessFilter(PageRenderLinkSource renderLinkSource,
                            ComponentSource componentSource, Response response,
                            Authenticator authenticator) {
        this.renderLinkSource = renderLinkSource;
        this.componentSource = componentSource;
        this.response = response;
        this.authenticator = authenticator;
    }

    public void handleComponentEvent(
            ComponentEventRequestParameters parameters,
            ComponentRequestHandler handler) throws IOException {

        if (dispatchedToLoginPage(parameters.getActivePageName())) {
            return;
        }

        handler.handleComponentEvent(parameters);

    }

    public void handlePageRender(PageRenderRequestParameters parameters,
                                 ComponentRequestHandler handler) throws IOException {

        if (dispatchedToLoginPage(parameters.getLogicalPageName())) {
            return;
        }

        handler.handlePageRender(parameters);
    }

    private boolean dispatchedToLoginPage(String pageName) throws IOException {

        if (pageName.equals("admin/Login")) {
            return false;
        }

        if (authenticator.isLoggedIn()) {
            return false;
        }

        Component page = componentSource.getPage(pageName);

        if (page.getClass().isAnnotationPresent(PublicPage.class)) {
            return false;
        }

        Link link = renderLinkSource.createPageRenderLink(Login.class);

        response.sendRedirect(link);

        return true;
    }
}
