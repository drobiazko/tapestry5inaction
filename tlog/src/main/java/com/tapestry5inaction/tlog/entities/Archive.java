package com.tapestry5inaction.tlog.entities;


import java.util.Date;

public class Archive {

    private Number count;

    private Date date;

    public Archive(Date date, Number count) {
        this.date = date;
        this.count = count;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Number getCount() {
        return count;
    }

    public void setCount(Number count) {
        this.count = count;
    }
}
