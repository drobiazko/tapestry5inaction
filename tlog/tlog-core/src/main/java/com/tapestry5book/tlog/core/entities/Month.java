package com.tapestry5book.tlog.core.entities;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Month {
    private Date start;
    private Date end;

    public Month(int year, int month){
        this(new GregorianCalendar(year, month - 1, 1).getTime());
    }

    public Month(Date start) {
        this.start = start;

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(this.start);
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, lastDay);
        this.end = calendar.getTime();
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }
}
