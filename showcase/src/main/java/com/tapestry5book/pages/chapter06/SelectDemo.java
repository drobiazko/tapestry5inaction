package com.tapestry5book.pages.chapter06;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;

public class SelectDemo {

    @Persist
    @Property
    private String paymentType;

    @Inject
    private Logger logger;

    void onSuccess() {
        logger.info("Selected payment type {} ", paymentType);
    }
}