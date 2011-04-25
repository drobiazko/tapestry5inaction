package com.tapestry5inaction.pages.chapter06;

import com.tapestry5inaction.entities.Gender;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

public class RadioEnumDemo {

    @Persist
    @Property
    private Gender gender;

    @Property
    private Gender currentGender;

    @Persist(PersistenceConstants.FLASH)
    @Property
    private Gender selectedGender;

    public Gender[] getValues() {
        return Gender.values();
    }

    public String getLabel() {
        return currentGender.name().toLowerCase();
    }


    void onSuccess() {
        selectedGender = gender;
    }
}