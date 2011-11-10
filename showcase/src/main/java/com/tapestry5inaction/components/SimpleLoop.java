package com.tapestry5inaction.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.Iterator;

public class SimpleLoop<T> {

    @Parameter(required = true, principal = true, autoconnect = true)
    private Iterable<T> source;

    @Parameter(principal = true)
    private T value;

    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private String element;

    @Inject
    private ComponentResources componentResources;

    private Iterator<T> iterator;

    @SetupRender
    boolean setup() {
        iterator = source.iterator();

        return iterator.hasNext();
    }

    @BeginRender
    void begin(MarkupWriter writer) {
        value = iterator.next();

        if (element != null) {
            writer.element(element);
        }
    }

    @AfterRender
    boolean after(MarkupWriter writer) {
        if (element != null) {
            writer.end();
        }

        return !iterator.hasNext();
    }

    String defaultElement() {
        return componentResources.getElementName();
    }
}