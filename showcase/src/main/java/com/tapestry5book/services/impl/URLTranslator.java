package com.tapestry5book.services.impl;

import org.apache.tapestry5.Field;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.Translator;
import org.apache.tapestry5.ValidationException;
import org.apache.tapestry5.services.FormSupport;

import java.net.MalformedURLException;
import java.net.URL;

public class URLTranslator implements Translator<URL> {
    public String getName() {
        return "url";
    }

    public String toClient(URL value) {
        return value.toString();
    }

    public Class<URL> getType() {
        return URL.class;
    }

    public String getMessageKey() {
        return "url-parse-exception";
    }

    public URL parseClient(Field field, String clientValue, String message) throws ValidationException {
        try {
            return new URL(clientValue);
        } catch (MalformedURLException e) {
            throw new ValidationException(message);
        }
    }

    public void render(Field field, String message, MarkupWriter writer, FormSupport formSupport) {
    }
}
