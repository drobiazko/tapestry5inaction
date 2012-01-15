package com.tapestry5inaction.pages.chapter11;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

import java.util.Date;

public class MultiZoneCallbackDemo {

    @Property
    @Persist
    private int number;

    @Inject
    private Block numberBlock;

    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;

    void onIncrement() {
        this.number++;

        ajaxResponseRenderer
                .addRender("numberZone", numberBlock)
                .addRender("statusZone", "Updated on " + new Date());
    }
}