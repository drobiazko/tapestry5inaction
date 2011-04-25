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

public class SelectEnumDemo {
    @Persist
    @Property
    private PaymentType paymentType;

    @Inject
    private Messages messages;
    @Inject
    private Logger logger;

    public SelectModel getOptions() {
        return new EnumSelectModel(PaymentType.class, messages);
    }

    @DiscardAfter
    void onSuccess() {
        logger.info("Selected payment type {} ", paymentType);
    }
}