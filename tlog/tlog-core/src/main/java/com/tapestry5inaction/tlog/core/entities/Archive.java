package com.tapestry5inaction.tlog.core.entities;


public class Archive {

    private Number count;

    private Month month;

    public Archive(Month month, Number count) {
        this.month = month;
        this.count = count;
    }

    public Number getCount() {
        return count;
    }

    public void setCount(Number count) {
        this.count = count;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }
}
