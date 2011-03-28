package com.tapestry5inaction.pages.chapter05;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.services.ThreadLocale;
import org.apache.tapestry5.services.PersistentLocale;
import org.apache.tapestry5.services.Request;

import java.util.Locale;

public class LocaleDemo {

    @Inject
    private Request request;


    @Inject
    private ThreadLocale threadLocale;


    @Inject
    @Property
    private Locale locale;


    @Inject
    private PersistentLocale persistentLocale;

    public Locale getRequestLocale() {
        return request.getLocale();
    }

    public Locale getThreadLocale() {
        return threadLocale.getLocale();
    }

    public Locale getPersistentLocale() {
        return persistentLocale.get();
    }
}