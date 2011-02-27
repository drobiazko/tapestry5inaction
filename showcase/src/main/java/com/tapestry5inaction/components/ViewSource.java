package com.tapestry5inaction.components;

import org.apache.commons.io.IOUtils;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.Resource;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.model.ComponentModel;
import org.apache.tapestry5.runtime.Component;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.apache.tapestry5.services.templates.ComponentTemplateLocator;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Locale;

@Import(library = {"context:syntaxhighlighter/shCore.js",
        "context:syntaxhighlighter/shBrushJava.js",
        "context:syntaxhighlighter/shBrushXml.js"}, stylesheet = "context:syntaxhighlighter/shCoreDefault.css")
public class ViewSource {

    @Inject
    private ComponentResources resources;

    @Inject
    private ComponentTemplateLocator componentTemplateLocator;

    @Inject
    private JavaScriptSupport javaScriptSupport;

    @Inject
    private Locale locale;

    @Property
    private String pageClassPath;

    @Property
    private Resource template;

    void setupRender() {

        Component page = resources.getPage();

        String pageClass = page.getClass().getName();

        pageClassPath = String.format("/%s.java", pageClass.replace(".", "/"));

        ComponentModel model = page.getComponentResources().getComponentModel();

        template = componentTemplateLocator.locateTemplate(model, locale);
    }

    public void beginRender(MarkupWriter writer) {

        javaScriptSupport.addScript("SyntaxHighlighter.all();");
    }

    public String getJavaCode() {
        InputStream inputStream = getClass().getResourceAsStream(pageClassPath);

        return toString(inputStream);
    }

    public String getTemplateCode() throws IOException {
        if (template == null) {
            return null;
        }
        return toString(template.openStream());
    }

    private String toString(InputStream inputStream) {
        StringWriter target = new StringWriter();
        try {
            IOUtils.copy(inputStream, target);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }

        return target.toString();
    }
}