package com.tapestry5book.pages.chapter11;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.apache.tapestry5.services.ajax.JavaScriptCallback;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

import java.util.Date;

public class MultiZoneCallbackDemo {

    @Property
    @Persist
    private int number;

    @Inject
    private Block numberBlock;

    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;

    @Inject
    @Path("initializers.js")
    private Asset initializers;

    void onIncrement() {
        this.number++;

        ajaxResponseRenderer
                .addRender("numberZone", numberBlock);

        ajaxResponseRenderer.addCallback(new JavaScriptCallback() {
            public void run(JavaScriptSupport support) {

                support.importJavaScriptLibrary(initializers);

                JSONObject parameters = new JSONObject("id", "status",
                        "newContent", "Updated on " + new Date());

                support.addInitializerCall(
                        "replaceContent", parameters);
            }
        });

    }
}