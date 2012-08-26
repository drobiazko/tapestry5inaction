package com.tapestry5inaction.pages.chapter06;

import com.tapestry5inaction.entities.Address;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

public class FormFragmentDemo {

    @Property
    private Address billingAddress;

    @Property
    private Address shippingAddress;

    @Property
    @Persist
    private boolean shipToAnotherAddress;

    void onPrepare() {
        billingAddress = new Address();
        shippingAddress = new Address();
    }

    void onSuccess() {
        System.err.println(billingAddress.getStreet());
        System.err.println(shippingAddress.getStreet());
    }

}