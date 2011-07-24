package com.tapestry5inaction.pages.chapter08;

import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PropertyOutputContext;

import java.net.URL;
import java.text.DateFormat;
import java.util.Locale;

public class AppPropertyDisplayBlocks {

    @Environmental
    private PropertyOutputContext context;

    @Inject
    private Locale locale;

    public URL getURL() {
        final URL url = (URL) context.getPropertyValue();

        if (url == null) {
            return null;
        }

        return url;
    }

    public Object getDate() {
        return context.getPropertyValue();
    }

    public DateFormat getDateFormat() {
        return DateFormat.getDateInstance(DateFormat.SHORT, locale);
    }
}
