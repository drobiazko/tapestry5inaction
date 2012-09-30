package com.tapestry5book.services.impl;

import org.apache.tapestry5.Field;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.Translator;
import org.apache.tapestry5.ValidationException;
import org.apache.tapestry5.services.FormSupport;

import java.util.Currency;

public class CurrencyTranslator implements Translator<Currency> {

    public String getMessageKey() {
        return "currency-parse-exception";
    }

    public String getName() {
        return "currency";
    }

    public Class<Currency> getType() {
        return Currency.class;
    }

    public Currency parseClient(Field field, String clientValue,
                                String message)
            throws ValidationException {
        try {
            return Currency.getInstance(clientValue);
        } catch (final IllegalArgumentException e) {
            throw new ValidationException(message);
        }
    }

    public String toClient(final Currency value) {
        return value.toString();
    }

    public void render(Field field, String message,
                       MarkupWriter writer, FormSupport formSupport) {
    }
}