package com.tapestry5book.tlog.core.services.impl;


import com.tapestry5book.tlog.core.utils.Utils;
import com.tapestry5book.tlog.core.entities.Month;
import org.apache.tapestry5.ValueEncoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MonthValueEncoder implements ValueEncoder<Month> {

    public String toClient(Month value) {
        Date start = value.getStart();
        return newFormat().format(start);
    }

    public Month toValue(String clientValue) {
        if(Utils.isBlank(clientValue)){
            return null;
        }

        try {
            return new Month(newFormat().parse(clientValue));
        } catch (ParseException e) {
            return null;
        }
    }

    private SimpleDateFormat newFormat(){
        return new SimpleDateFormat("yyyy-MM");
    }
}
