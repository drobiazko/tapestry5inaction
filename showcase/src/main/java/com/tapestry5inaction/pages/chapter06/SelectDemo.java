package com.tapestry5inaction.pages.chapter06;

import com.tapestry5inaction.entities.PaymentType;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.DiscardAfter;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.util.EnumSelectModel;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.List;

public class SelectDemo {

    @Persist
    @Property
    private String paymentType;

    @Inject
    private Logger logger;

    @DiscardAfter
    void onSuccess() {
        logger.info("Selected payment type {} ", paymentType);
    }
}