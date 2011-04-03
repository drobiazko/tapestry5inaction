package com.tapestry5inaction.pages.chapter05;


import org.apache.tapestry5.annotations.Import;

@Import(stylesheet = "context:css/flags.css", library = "context:js/tapestry5inaction.js")
public class LocalizedCssJavaScriptDemo {

    @Import(library = "context:js/messages.js")
    void afterRender() {
    }
}