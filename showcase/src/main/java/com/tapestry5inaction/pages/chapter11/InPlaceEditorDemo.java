package com.tapestry5inaction.pages.chapter11;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

public class InPlaceEditorDemo {

    @Property(read = false)
    @Persist
    private String edit;

    public String getEdit() {
        return edit == null ? "Please click here" : edit;
    }
}