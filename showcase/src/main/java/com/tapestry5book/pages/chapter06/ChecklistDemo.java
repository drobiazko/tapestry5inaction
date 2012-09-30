package com.tapestry5book.pages.chapter06;

import com.tapestry5book.entities.Language;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ValueEncoderSource;
import org.apache.tapestry5.util.EnumSelectModel;

import java.util.List;

public class ChecklistDemo {
    @Inject
    private Messages messages;

    @Inject
    private AlertManager alertManager;

    @Inject
    private ValueEncoderSource valueEncoderSource;

    @Persist
    @Property
    private List<Language> languages;

    public SelectModel getModel() {
        return new EnumSelectModel(Language.class, messages);
    }

    public ValueEncoder getEncoder() {
        return valueEncoderSource.getValueEncoder(Language.class);
    }

    void onSuccess(){
        alertManager.info(messages.get("thank-you"));
    }
}