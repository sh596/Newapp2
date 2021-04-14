package com.example.newapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Week extends Fragment{

    private RecyclerView calendar;
    private RecyclerView calendartodo;
    private CalRecycler adapter1;
    private CalTodo_Recycler adapter2;

    private ArrayList<DateItem> mcalendarlist = new ArrayList<>();
    private ArrayList<Item> list = new ArrayList<>();

    private GregorianCalendar cal;
    private GregorianCalendar cal2;

    int m;
    int y;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.week,container,false);

        final RDatabase db = RDatabase.getAppDatabase(getContext());

        calendar = view.findViewById(R.id.recyclerweek1);
        calendartodo = view.findViewById(R.id.recyclerweek2);
        GridLayoutManager manager1 = new GridLayoutManager(getContext(),7);
        LinearLayoutManager manager2 = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

        cal = new GregorianCalendar();
        m = cal.get(Calendar.MONTH);
        y = cal.get(Calendar.YEAR);
        cal2 = new GregorianCalendar(y,m,1,0,0,0);

        mcalendarlist.clear();
        setcalender(cal2,y,m);

        adapter1 = new CalRecycler(mcalendarlist);
        adapter2 = new CalTodo_Recycler(list);

        adapter1.setCalenderList(mcalendarlist);

        calendar.setLayoutManager(manager1);
        calendartodo.setLayoutManager(manager2);

        adapter1.setOnClickListener(new CalRecycler.Onsettodo() {
            @Override
            public void OnSet(View view, int pos, int viewtype) {
                if(viewtype == 1){
                    settodolist(db,pos,calendartodo,y,m,pos-cal2.get(Calendar.DAY_OF_WEEK)+2);
                }else{
                    settodolist(db,pos,calendartodo,y,m,cal2.getActualMaximum(Calendar.DAY_OF_MONTH)-cal2.get(Calendar.DAY_OF_WEEK)+1+pos);
                }
            }
        });
        calendar.setAdapter(adapter1);


        return view;
    }

    public void setcalender(GregorianCalendar cal2 ,int year, int month){
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int week = cal.get(Calendar.DAY_OF_WEEK);

        int firstday = cal2.get(Calendar.DAY_OF_MONTH);

        int lastday = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int startday = day - week + 1;

        if(day - week < 0){
            int firstweek = cal2.get(Calendar.DAY_OF_WEEK);
            cal2 = new GregorianCalendar(year,month-1,1,0,0,0);
            int weekday = cal2.getActualMaximum(Calendar.DAY_OF_MONTH) - firstweek + 2;
            for(int i = 1; i < firstweek; i++){
                mcalendarlist.add(new DateItem(weekday, 2));
                weekday++;
            }
            for(int i = firstday; i < day + 8 - week +1; i++){
                mcalendarlist.add(new DateItem(i,1));
            }
        }else if(day + 7 - week > lastday){
            for(int i = startday; i < lastday; i++){
                mcalendarlist.add(new DateItem(i,1));
            }
            cal2 = new GregorianCalendar(year,month,lastday,0,0,0);
            for(int i = 1; i < 8 - cal2.get(Calendar.DAY_OF_WEEK); i++){
                mcalendarlist.add(new DateItem(i,2));
            }

        }else{
            for(int i = startday; i < startday + 7; i++){
                mcalendarlist.add(new DateItem(i,1));
            }
        }
    }
    public void settodolist(RDatabase db, int pos, RecyclerView caltodo, int y, int m, int d){
        adapter2.removeall();

        for(int i = 0; i < 4; i++){//클릭한 날짜의 할 일을 우선순위순으로 나열
            for (int s = 0; s < db.itemDao().getday(y,m,d,i).size(); s++){
                adapter2.additem(db.itemDao().getday(y,m,d,i).get(s));
            }
        }
        for (int i = 0; i < 4; i++){//클릭한 날짜의 매주 반복할 일을 우선순위순으로 나열
            for (int j= 0; j < db.itemDao().getdayweek(pos%7+1,i).size(); j++){
                adapter2.additem(db.itemDao().getdayweek(pos%7+1,i).get(j));
            }
        }
        caltodo.setAdapter(adapter2);
    }


}
