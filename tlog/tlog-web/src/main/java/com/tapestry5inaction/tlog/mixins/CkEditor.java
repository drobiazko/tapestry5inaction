package com.tapestry5inaction.tlog.mixins;


import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectContainer;
import org.apache.tapestry5.annotations.MixinAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

@Import(library = "context:static/ckeditor/ckeditor.js")
@MixinAfter
public class CkEditor {

    @InjectContainer
    private ClientElement container;

    @Inject
    private JavaScriptSupport javaScriptSupport;

    void afterRender(){
        javaScriptSupport.addScript("CKEDITOR.replace( '%s' );", container.getClientId());
    }
}
