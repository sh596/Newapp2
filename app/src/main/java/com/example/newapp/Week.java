package com.example.newapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Week extends Fragment{

    private RecyclerView calendar;
    private CalRecycler adapter;

    public ArrayList<DateItem> mcalendarlist = new ArrayList<>();

    private GregorianCalendar cal;
    private GregorianCalendar cal2;

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.week,container,false);
        calendar = view.findViewById(R.id.recyclerweek1);
        GridLayoutManager manager = new GridLayoutManager(getContext(),7);

        cal = new GregorianCalendar();
        int m = cal.get(Calendar.MONTH);
        int y = cal.get(Calendar.YEAR);
        cal2 = new GregorianCalendar(y,m,1,0,0,0);

        mcalendarlist.clear();
        setcalender(cal2,y,m);

        adapter = new CalRecycler(mcalendarlist);

        adapter.setCalenderList(mcalendarlist);

        calendar.setLayoutManager(manager);
        calendar.setAdapter(adapter);


        return view;
    }

    public void setcalender(GregorianCalendar cal2 ,int year, int month){
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int week = cal.get(Calendar.DAY_OF_WEEK);

        int lastday = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int startday = day - week + 1;

        if(day - week < 0){
            int firstweek = cal2.get(Calendar.DAY_OF_WEEK);
            cal2 = new GregorianCalendar(year,month-1,1,0,0,0);
            int weekday = cal2.getActualMaximum(Calendar.DAY_OF_MONTH) - firstweek + 2;
            for(int i = 1; i < firstweek; i++){
                mcalendarlist.add(new DateItem(weekday, 0));
                weekday++;
            }
            for(int i = day; i < day + 8 - week; i++){
                mcalendarlist.add(new DateItem(i,1));
            }
        }else if(day + 7 - week > lastday){
            for(int i = startday; i < lastday; i++){
                mcalendarlist.add(new DateItem(i,1));
            }
            cal2 = new GregorianCalendar(year,month,lastday,0,0,0);
            for(int i = 1; i < 8 - cal2.get(Calendar.DAY_OF_WEEK); i++){
                mcalendarlist.add(new DateItem(i,0));
            }

        }else{
            for(int i = startday; i < startday + 6; i++){
                mcalendarlist.add(new DateItem(i,1));
            }
        }
    }


}
