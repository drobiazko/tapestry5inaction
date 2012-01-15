package com.tapestry5inaction.pages.chapter11;

import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;

public class WorldTime {

    private Date newYorkTime;

    public String getBerlinTime() {
        return getDateByTimeZone("Europe/Berlin");
    }

    public String getMoscowTime() {
        return getDateByTimeZone("Europe/Moscow");
    }

    public String getNewYorkTime() {
        return getDateByTimeZone("America/New_Yourk");
    }

    public String getDateByTimeZone(String timeZone) {
        final DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.LONG);
        dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));

        return dateFormat.format(new Date());
    }
}