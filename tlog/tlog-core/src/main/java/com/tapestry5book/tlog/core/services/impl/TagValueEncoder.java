package com.tapestry5book.tlog.core.services.impl;


import com.tapestry5book.tlog.core.entities.Tag;
import com.tapestry5book.tlog.core.utils.Utils;
import org.apache.tapestry5.ValueEncoder;

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
