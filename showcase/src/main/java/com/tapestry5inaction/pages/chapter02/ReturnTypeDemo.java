package com.tapestry5inaction.pages.chapter02;

import java.net.MalformedURLException;
import java.net.URL;

public class ReturnTypeDemo {

    Object onActionFromPageLink() {

        return Index.class;
    }

    Object onActionFromExternalLink() throws MalformedURLException {

        return new URL("http://www.google.com");
    }
}