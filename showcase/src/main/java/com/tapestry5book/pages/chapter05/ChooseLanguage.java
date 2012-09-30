package com.tapestry5book.pages.chapter05;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PersistentLocale;

import java.util.Locale;

public class ChooseLanguage {

    @Inject
    private PersistentLocale persistentLocale;

    void onEn() {
        persistentLocale.set(Locale.ENGLISH);
    }

    void onRu() {
        persistentLocale.set(new Locale("ru"));
    }

    void onIl() {
        persistentLocale.set(new Locale("iw"));
    }

}