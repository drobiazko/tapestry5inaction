package com.tapestry5book.pages.chapter10;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AutocompleteDemo {
    @Inject
    private Locale locale;

    @Inject
    private AlertManager alertManager;

    @Property
    private String country;

    List<String> onProvideCompletionsFromCountry(String input) {
        List<String> result = new ArrayList<String>();

        Locale[] locales = Locale.getAvailableLocales();

        for (Locale next : locales) {
            String country = next.getDisplayCountry(locale);

            if (country.toLowerCase().contains(input.toLowerCase())) {
                result.add(country);
            }
        }

        return result;
    }

    void onSuccess() {
        alertManager.info("Selected country: " + country);
    }
}