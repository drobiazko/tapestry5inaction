package com.tapestry5book.pages.chapter06;


import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.DateField;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.Date;

public class DateFieldDemo {

    @Persist
    @Property
    private Date birthday;

    @InjectComponent
    private Form form;

    @InjectComponent
    private DateField birthdayField;

    @Inject
    private Messages messages;

    @Property
    @Persist(PersistenceConstants.FLASH)
    private String successMessage;

    void onValidateFromBirthdayField(Date value) {
        if (value != null && value.after(new Date())) {
            form.recordError(birthdayField, messages.get("birthday-is-in-future"));
        }
    }


    void onSuccess() {
        //Process birthday here

        successMessage = messages.format("success-message", birthday);
    }

}