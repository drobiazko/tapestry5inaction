package com.tapestry5book.services.impl;

import org.apache.tapestry5.NullFieldStrategy;

import java.util.Currency;

public class CurrencyNullFieldStrategy implements NullFieldStrategy {

    private static final String DEFAULT_CURRENCY = "USD";

    public Object replaceToClient() {
        return Currency.getInstance(DEFAULT_CURRENCY);
    }

    public String replaceFromClient() {
        return DEFAULT_CURRENCY;
    }
}
