package com.example.newapp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    public final static String CalenderHead  = "yyyy-MM-dd HH:mm:ss";
    public final static String YearFormat = "yyyy";
    public final static String MonthFormat = "MM";
    public final static String DayFormat = "d";
    public final static String HourFormat = "HH";
    public final static String MinFormat = "mm";
    public final static String SecFormat = "ss";

    public static String getDate(Long date,String pattern){
        try{
            SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.ENGLISH);
            Date d = new Date(date);
            return formatter.format(d).toUpperCase();
        }catch (Exception e) {
            return "";
        }
    }
}
