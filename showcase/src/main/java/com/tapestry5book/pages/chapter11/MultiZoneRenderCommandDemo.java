package com.tapestry5book.pages.chapter11;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.runtime.RenderCommand;
import org.apache.tapestry5.runtime.RenderQueue;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

import java.util.Date;

public class MultiZoneRenderCommandDemo {

    @Property
    @Persist
    private int number;

    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;

    void onIncrement() {
        this.number++;

        RenderCommand command = new RenderCommand() {
            public void render(MarkupWriter writer, RenderQueue queue) {
                writer.element("p");
                writer.writef("Current value: %d", number);
                writer.end();
            }
        };

        ajaxResponseRenderer
                .addRender("numberZone", command)
                .addRender("statusZone", "Updated on " + new Date());
    }
}