package com.tapestry5inaction.pages.chapter06;

import com.tapestry5inaction.entities.Language;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.util.EnumSelectModel;
import org.apache.tapestry5.util.EnumValueEncoder;

import java.util.List;

public class PaletteDemo {

    @Inject
    private Messages messages;

    @Persist
    @Property
    private List<Language> languages;

    public SelectModel getModel() {
        return new EnumSelectModel(Language.class, messages);
    }

    public ValueEncoder getEncoder() {
        return new EnumValueEncoder(Language.class);
    }

}