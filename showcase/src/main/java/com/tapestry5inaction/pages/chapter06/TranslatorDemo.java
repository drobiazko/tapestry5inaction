package com.tapestry5inaction.pages.chapter06;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

import java.util.Currency;

public class TranslatorDemo {

    @Property
    @Persist
    private Currency currency;
}