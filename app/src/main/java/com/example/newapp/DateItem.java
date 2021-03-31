package com.example.newapp;

public class DateItem {
    private int date;
    private int viewtype;

    public DateItem(int date, int viewtype) {
        this.date = date;
        this.viewtype = viewtype;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getViewtype() {
        return viewtype;
    }

    public void setViewtype(int viewtype) {
        this.viewtype = viewtype;
    }
}
