package com.example.newapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Month extends Fragment {

    private TextView month;
    private TextView year;
    private ImageButton lastmonth;
    private ImageButton nextmonth;

    public int mCenterPosition;
    private CalRecycler adapter;
    private CalTodo_Recycler adapter2;
    public ArrayList<Object> mcalenderlist = new ArrayList<>();

    private GregorianCalendar cal;
    private GregorianCalendar cal2;
    private int y;
    private int m;
    private int d;


    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.month,container,false);

        final RDatabase db = RDatabase.getAppDatabase(getContext());

        final RecyclerView calender = view.findViewById(R.id.calender);
        final RecyclerView caltodo = view.findViewById(R.id.caltodo);
        month = view.findViewById(R.id.month);
        year = view.findViewById(R.id.year);
        lastmonth = view.findViewById(R.id.lastmonth);
        nextmonth = view.findViewById(R.id.nextmonth);

        cal = new GregorianCalendar();
        y = cal.get(Calendar.YEAR);
        m = cal.get(Calendar.MONTH);
        cal2 = new GregorianCalendar(y,m,1,0,0,0);

        d = cal2.get(Calendar.DAY_OF_WEEK)-2;



        setcalenderlist();

        onMonthset();

        year.setText(String.valueOf(y));


        final ArrayList<Item> list = new ArrayList<>();

        final GridLayoutManager manager = new GridLayoutManager(getContext(),7);
        LinearLayoutManager manager1 = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

        adapter = new CalRecycler(mcalenderlist);
        adapter2 = new CalTodo_Recycler(list);

        adapter.setCalenderList(mcalenderlist);
        caltodo.setLayoutManager(manager1);

        calender.setLayoutManager(manager);
        calender.setAdapter(adapter);


        adapter.setOnClickListener(new CalRecycler.Onsettodo() {
            @Override
            public void OnSet(View view, int pos) {
                settodolist(db,pos,caltodo);
            }
        });

        lastmonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (m > 0){
                    m-=1;
                }else{
                    m+=12;
                    y-=1;
                    year.setText(String.valueOf(y));
                }

                cal2 = new GregorianCalendar(y,m,1,0,0,0);
                d = cal2.get(Calendar.DAY_OF_WEEK)-2;
                adapter.removeall();
                setcalenderlist();
                adapter.setCalenderList(mcalenderlist);
                calender.setAdapter(adapter);
                onMonthset();
            }
        });
        nextmonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (m < 11){
                    m+=1;
                }else{
                    m-=11;
                    y+=1;
                    year.setText(String.valueOf(y));
                }

                cal2 = new GregorianCalendar(y,m,1,0,0,0);
                d = cal2.get(Calendar.DAY_OF_WEEK)-2;
                adapter.removeall();
                setcalenderlist();
                adapter.setCalenderList(mcalenderlist);
                calender.setAdapter(adapter);
                onMonthset();
            }
        });

        return view;
    }
    public void settodolist(RDatabase db, int pos, RecyclerView caltodo){
        adapter2.removeall();

        for(int i = 0; i < db.itemDao().getday(y,m,pos-d).size(); i++){
            adapter2.additem(db.itemDao().getday(y,m,pos-d).get(i));
        }
        if(!(y+m+pos-d == y+m+cal.get(Calendar.DATE))){

            for (int j= 0; j < db.itemDao().getdayweek(pos%7).size(); j++){
                adapter2.additem(db.itemDao().getdayweek(pos%7).get(j));
            }
        }
        caltodo.setAdapter(adapter2);
    }

    public void setcalenderlist(){
        ArrayList<Object> calenderList = new ArrayList<>();

        calenderList.add(cal2.getTimeInMillis());

        int dayOfWeek = cal2.get(Calendar.DAY_OF_WEEK) - 2;
        int max = cal2.getActualMaximum(Calendar.DAY_OF_MONTH);

        for(int j = 0; j < dayOfWeek; j++){
            calenderList.add(Keys.EMPTY);
        }
        for (int j = 1; j <= max; j++){
            calenderList.add(new GregorianCalendar(cal2.get(Calendar.YEAR),cal2.get(Calendar.MONTH),j));
        }


        mcalenderlist = calenderList;
    }

    @Override
    public void onStart() {
        super.onStart();

        RDatabase db = RDatabase.getAppDatabase(getContext());

        adapter2.removeall();
        for(int i = 0; i < db.itemDao().getday(y,m,cal.get(Calendar.DATE)).size(); i++){
            adapter2.additem(db.itemDao().getday(y,m,cal.get(Calendar.DATE)).get(i));
        }
        for (int j= 0; j < db.itemDao().getdayweek(d+cal.get(Calendar.DATE)%7).size(); j++){
            adapter2.additem(db.itemDao().getdayweek(d+cal.get(Calendar.DATE)%7).get(j));
        }
    }
    public void onMonthset(){
        switch(m){
            case 0: month.setText("January");
                break;
            case 1: month.setText("February");
                break;
            case 2: month.setText("March");
                break;
            case 3:month.setText("April");
                break;
            case 4:month.setText("May");
                break;
            case 5:month.setText("June");
                break;
            case 6:month.setText("July") ;
                break;
            case 7:month.setText("August");
                break;
            case 8:month.setText("September");
                break;
            case 9:month.setText("October");
                break;
            case 10: month.setText("November");
                break;
            case 11:month.setText("December");
                break;
            default:
                month.setText("");
        }
    }
}
