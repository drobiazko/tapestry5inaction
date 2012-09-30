package com.tapestry5book.components;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.ioc.annotations.Inject;

public class Duke {

    @Inject
    @Path("duke.jpg")
    private Asset duke;

    @BeginRender
    boolean renderImage(MarkupWriter writer) {
        writer.element("img", "src", duke);

        writer.end();

        return false;
    }
}
