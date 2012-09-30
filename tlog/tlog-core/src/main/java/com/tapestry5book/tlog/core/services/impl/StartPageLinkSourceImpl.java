package com.tapestry5book.tlog.core.services.impl;


import com.tapestry5book.tlog.core.RequestParameters;
import com.tapestry5book.tlog.core.entities.Month;
import com.tapestry5book.tlog.core.entities.Tag;
import com.tapestry5book.tlog.core.services.StartPageLinkSource;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import java.util.ArrayList;
import java.util.List;

public class StartPageLinkSourceImpl implements StartPageLinkSource {
    @Inject
    private PageRenderLinkSource pageRenderLinkSource;

    public Link getLink(Month month) {
        return getLink(month, RequestParameters.MONTH);
    }

    public Link getLink(Tag tag) {
        return getLink(tag, RequestParameters.TAG);
    }

    public Link getLink(Object value, String parameterName) {
        Link link = pageRenderLinkSource.createPageRenderLinkWithContext("index");

        List<String> parameters = new ArrayList<String>(link.getParameterNames());

        for (String next : parameters) {
            link.removeParameter(next);
        }

        link.addParameterValue(parameterName, value);

        return link;
    }
}
