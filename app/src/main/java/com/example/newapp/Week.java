package com.example.newapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Week extends Fragment{

    private RecyclerView calender;
    private CalRecycler adapter;

    public ArrayList<DateItem> mcalendarlist = new ArrayList<>();

    private GregorianCalendar cal;
    private GregorianCalendar cal2;

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.week,container,false);
        calender = view.findViewById(R.id.calender);

        cal = new GregorianCalendar();
        int m = cal.get(Calendar.MONTH);
        int y = cal.get(Calendar.YEAR);
        cal2 = new GregorianCalendar(y,m,1,0,0,0);

        setcalender(cal2,y,m);

        return view;
    }
    public void setcalender(GregorianCalendar cal2 ,int year, int month){
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int week = cal.get(Calendar.DAY_OF_WEEK);

        int startday = day - week + 1;

        if(day - week < 0){
            int firstweek = cal2.get(Calendar.DAY_OF_WEEK);
            cal2 = new GregorianCalendar(year,month-1,0,0,0,0);
            for(int i = 1; i < firstweek; i++){
                day = cal2.getActualMaximum(day) - firstweek;
                mcalendarlist.add(new DateItem(day, 0));
                day++;
            }
        }else if(day + 7 - week > cal.getActualMaximum(day)){
            for(int i = startday; i < cal.getActualMaximum(day); i++){
                mcalendarlist.add(new DateItem(i,1));
            }
            cal2 = new GregorianCalendar(year,month,cal.getActualMaximum(day),0,0,0);
            for(int i = 1; i < cal2.get(Calendar.DAY_OF_WEEK) - 8; i++){
                mcalendarlist.add(new DateItem(i,0));
            }

        }else{
            for(int i = startday; i < startday + 6; i++){
                mcalendarlist.add(new DateItem(i,1));
            }
        }

    }


}
