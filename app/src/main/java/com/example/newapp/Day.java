package com.example.newapp;

import java.util.Calendar;

public class Day extends ViewModel {
    String day;

    public Day() {
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setCalendar(Calendar calendar){
        day = DateUtil.getDate(calendar.getTimeInMillis(), DateUtil.DayFormat);
    }
}
