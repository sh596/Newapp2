package com.example.newapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Today extends Fragment {
    private View view;
    public ArrayList<Item> list1;
    public ArrayList<Item> list2;

    private GregorianCalendar calendar;
    Todolist_recycler adapter1;
    Todolist_recycler adapter2;

    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.today,container,false);

        final RDatabase db = RDatabase.getAppDatabase(getContext());

        list1 = new ArrayList<>();
        list2 = new ArrayList<>();

        recyclerView1 = view.findViewById(R.id.excution_recycler1);
        recyclerView2 = view.findViewById(R.id.excution_recycler2);

        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        LinearLayoutManager manager2 = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView1.setLayoutManager(manager);
        recyclerView2.setLayoutManager(manager2);

        adapter1 = new Todolist_recycler(list1);
        adapter2 = new Todolist_recycler(list2);

        calendar = new GregorianCalendar();

        for (int i = 0; i < 5; i++){
            for (int s = 0; s < db.itemDao().getdaycom(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE),i,0).size(); s++){
                adapter1.additem(db.itemDao().getdaycom(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE),i,0).get(s));
            }
        }
        for (int i = 0; i < 5; i++){
            for (int s = 0; s < db.itemDao().getdayweekcom(calendar.get(Calendar.DAY_OF_WEEK),i,0).size(); s++){
                adapter1.additem(db.itemDao().getdayweekcom(calendar.get(Calendar.DAY_OF_WEEK),i,0).get(s));
            }
        }


        for (int i = 0; i < 5; i++){
            for (int s = 0; s < db.itemDao().getdaycom(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE),i,1).size(); s++){
                adapter2.additem(db.itemDao().getdaycom(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE),i,1).get(s));
            }
        }
        for (int i = 0; i < 5; i++){
            for (int s = 0; s < db.itemDao().getdayweekcom(calendar.get(Calendar.DAY_OF_WEEK),i,1).size(); s++){
                adapter2.additem(db.itemDao().getdayweekcom(calendar.get(Calendar.DAY_OF_WEEK),i,1).get(s));
            }
        }

        recyclerView2.setAdapter(adapter2);
        recyclerView1.setAdapter(adapter1);
        adapter1.setOnClickListener(new Todolist_recycler.OnClickListener() {
            @Override
            public void OnClick(View view, int id, String title, int stopwatch, int pri, int check, int dayweek, int year, int month, int day) {
                intentExexution(id,title,stopwatch,pri,check,dayweek,year,month,day);
            }
        });
        adapter2.setOnClickListener(new Todolist_recycler.OnClickListener() {
            @Override
            public void OnClick(View view, int id, String title,int stopwatch, int pri, int check, int dayweek, int year, int month, int day) {
                intentExexution(id, title, stopwatch, pri, check, dayweek, year, month, day);
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        final RDatabase db = RDatabase.getAppDatabase(getContext());


        list1 = new ArrayList<>();
        list2 = new ArrayList<>();

        recyclerView1 = view.findViewById(R.id.excution_recycler1);
        recyclerView2 = view.findViewById(R.id.excution_recycler2);

        LinearLayoutManager manager1 = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        LinearLayoutManager manager2 = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView1.setLayoutManager(manager1);
        recyclerView2.setLayoutManager(manager2);

        adapter1 = new Todolist_recycler(list1);
        adapter2 = new Todolist_recycler(list2);

        for (int i = 0; i < 5; i++){
            for (int s = 0; s < db.itemDao().getdaycom(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE),i,0).size(); s++){
                adapter1.additem(db.itemDao().getdaycom(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE),i,0).get(s));
            }
        }

        for (int i = 0; i < 5; i++){
            for (int s = 0; s < db.itemDao().getdayweekcom(calendar.get(Calendar.DAY_OF_WEEK),i,0).size(); s++){
                adapter1.additem(db.itemDao().getdayweekcom(calendar.get(Calendar.DAY_OF_WEEK),i,0).get(s));
            }
        }


        for (int i = 0; i < 5; i++){
            for (int s = 0; s < db.itemDao().getdaycom(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE),i,1).size(); s++){
                adapter2.additem(db.itemDao().getdaycom(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE),i,1).get(s));
            }
        }
        for (int i = 0; i < 5; i++){
            for (int s = 0; s < db.itemDao().getdayweekcom(calendar.get(Calendar.DAY_OF_WEEK),i,1).size(); s++){
                adapter2.additem(db.itemDao().getdayweekcom(calendar.get(Calendar.DAY_OF_WEEK),i,1).get(s));
            }
        }

        recyclerView2.setAdapter(adapter2);
        recyclerView1.setAdapter(adapter1);
        adapter1.setOnClickListener(new Todolist_recycler.OnClickListener() {
            @Override
            public void OnClick(View view, int id, String title,int stopwatch,int pri, int check, int dayweek, int year, int month, int day) {
                intentExexution(id, title, stopwatch, pri, check, dayweek, year, month, day);
            }

        });

        adapter2.setOnClickListener(new Todolist_recycler.OnClickListener() {
            @Override
            public void OnClick(View view, int id, String title, int stopwatch, int pri, int check, int dayweek, int year, int month, int day) {
                intentExexution(id, title, stopwatch, pri, check, dayweek, year, month, day);
            }
        });

    }

    public void intentExexution( int id, String title,int stopwatch ,int pri, int complete, int dayweek, int year, int month, int day){
        Intent intent = new Intent(view.getContext(),Execution_Activity.class);
        intent.putExtra("pos", id);
        intent.putExtra("title",title);
        intent.putExtra("stop",stopwatch);
        intent.putExtra("pri",pri);
        intent.putExtra("check",complete);
        intent.putExtra("dayweek",dayweek);
        intent.putExtra("year",year);
        intent.putExtra("month",month);
        intent.putExtra("day",day);
        startActivity(intent);

    }

}