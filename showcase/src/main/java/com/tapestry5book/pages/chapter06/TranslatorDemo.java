package com.tapestry5book.pages.chapter06;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

import java.util.Currency;

public class TranslatorDemo {

    @Property
    @Persist
    private Currency currency;
}