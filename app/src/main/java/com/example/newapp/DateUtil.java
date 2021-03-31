package com.example.newapp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    public final static String DayFormat = "d";

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
