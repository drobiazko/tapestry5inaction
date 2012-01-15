package com.tapestry5inaction.pages.chapter11;

import org.apache.tapestry5.Block;
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
    private Block numberBlock;

    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;

    void onIncrement2() {
        this.number++;

        ajaxResponseRenderer
                .addRender("numberZone", numberBlock)
                .addRender("statusZone", "Updated on " + new Date());
    }

    void onIncrement() {
        this.number++;

        ajaxResponseRenderer
                .addRender("numberZone", new RenderCommand() {
                    public void render(MarkupWriter writer, RenderQueue queue) {
                        writer.element("p");
                        writer.writef("Current value: %d", number);
                        writer.end();
                    }
                })
                .addRender("statusZone", "Updated on " + new Date());
    }
}