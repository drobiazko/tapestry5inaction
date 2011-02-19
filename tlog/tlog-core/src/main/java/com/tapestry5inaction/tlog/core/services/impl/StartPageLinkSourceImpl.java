package com.tapestry5inaction.tlog.core.services.impl;


import com.tapestry5inaction.tlog.core.RequestParameters;
import com.tapestry5inaction.tlog.core.entities.Month;
import com.tapestry5inaction.tlog.core.entities.Tag;
import com.tapestry5inaction.tlog.core.services.StartPageLinkSource;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.ValueEncoderSource;

import java.util.ArrayList;
import java.util.List;

public class StartPageLinkSourceImpl implements StartPageLinkSource {
    @Inject
    private PageRenderLinkSource pageRenderLinkSource;

    @Inject
    private ValueEncoderSource valueEncoderSource;

    public Link getLink(Month month) {
        return getLink(month, RequestParameters.MONTH);
    }

    public Link getLink(Tag tag) {
        return getLink(tag, RequestParameters.TAG);
    }

    public Link getLink(Object value, String parameterName) {
        ValueEncoder encoder = valueEncoderSource.getValueEncoder(value.getClass());

        Link link = pageRenderLinkSource.createPageRenderLinkWithContext("index");

        List<String> parameters = new ArrayList<String>(link.getParameterNames());

        for(String next: parameters){
            link.removeParameter(next);
        }

        link.addParameter(parameterName, encoder.toClient(value));

        return link;
    }
}
