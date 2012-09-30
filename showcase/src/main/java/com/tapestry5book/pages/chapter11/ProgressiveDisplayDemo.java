package com.tapestry5book.pages.chapter11;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.ioc.annotations.Inject;

public class ProgressiveDisplayDemo {

    @Inject
    private Block dukeBlock;

    Object onProgressiveDisplayFromDisplayDuke() {
        delay(3000);

        return dukeBlock;
    }

    private void delay(final int millis) {
        try {
            Thread.sleep(millis);
        } catch (final Exception ex) {
        }
    }
}