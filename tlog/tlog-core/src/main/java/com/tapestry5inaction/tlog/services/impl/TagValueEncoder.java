package com.tapestry5inaction.tlog.services.impl;


import com.tapestry5inaction.tlog.entities.Month;
import com.tapestry5inaction.tlog.entities.Tag;
import com.tapestry5inaction.tlog.utils.Utils;
import org.apache.tapestry5.ValueEncoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TagValueEncoder implements ValueEncoder<Tag> {

    public String toClient(Tag value) {
        return value.getName();
    }

    public Tag toValue(String clientValue) {
        if (Utils.isBlank(clientValue)) {
            return null;
        }

        Tag tag = new Tag();
        tag.setName(clientValue);

        return tag;
    }
}
