package com.tapestry5inaction.pages.chapter11;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.apache.tapestry5.services.ajax.JavaScriptCallback;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

import java.util.Date;

public class MultiZoneDemo {
    @Inject
    private Block dukeBlock;

    @Property
    private String message = "To be updated";

    @InjectComponent
    private Zone messageZone;

    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;

    @Inject
    @Path("initializers.js")
    private Asset library;

    public Date getNow() {
        return new Date();
    }

    void onActionFromUpdate() {

        ajaxResponseRenderer.addRender("dragons", "Unexplored territories");
        ajaxResponseRenderer.addRender("duke", dukeBlock);

        message = "Hello, Ajax!";
        ajaxResponseRenderer.addRender(messageZone);

        ajaxResponseRenderer.addCallback(new JavaScriptCallback() {
            public void run(JavaScriptSupport javascriptSupport) {

                javascriptSupport.importJavaScriptLibrary(library);

                javascriptSupport.addInitializerCall(
                        "replaceContent",
                        new JSONObject("id", "status", "newContent", "Updated on "+new Date()));
            }
        });
    }
}