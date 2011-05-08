package com.tapestry5inaction.pages.chapter06;

import com.tapestry5inaction.entities.Address;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

public class FormFragmentDemo {

    @Property
    private Address billingAdress;

    @Property
    private Address shippingAdress;

    @Property
    @Persist
    private boolean shipToAnotherAddress;

    void onPrepare() {
        billingAdress = new Address();
        shippingAdress = new Address();
    }

    void onSuccess() {
        System.err.println(billingAdress.getStreet());
        System.err.println(shippingAdress.getStreet());
    }

}