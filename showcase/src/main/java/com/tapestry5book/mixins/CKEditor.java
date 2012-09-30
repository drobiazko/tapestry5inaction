package com.tapestry5book.mixins;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectContainer;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;


@Import(library = "context:ckeditor/ckeditor.js")
public class CKEditor {

    @Parameter(value = "kama", defaultPrefix = BindingConstants.LITERAL)
    private String skin;

    @InjectContainer
    private ClientElement container;

    @Inject
    private JavaScriptSupport javaScriptSupport;

    void afterRender() {
        javaScriptSupport.addScript("CKEDITOR.replace( '%s', { skin: '%s' } );", container.getClientId(), skin);
    }
}