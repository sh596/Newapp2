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

public class Today extends Fragment  {
    private View view;
    public ArrayList<Item> list;
    Todolist_recycler adapter;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.today,container,false);

        final RDatabase db = RDatabase.getAppDatabase(getContext());
        db.itemDao().alldelete();

        list = new ArrayList<>();
        recyclerView = view.findViewById(R.id.excution_recycler1);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        adapter = new Todolist_recycler(list);

        for (int i = 0; i < 4; i++){
            for (int s = 0; s < db.itemDao().thing(i).size(); s++){
                adapter.additem(db.itemDao().thing(i).get(s));
            }
        }
        recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(new Todolist_recycler.OnClickListener() {
            @Override
            public void OnClick(View view, int position, String title,int start, int stiopwatch, int pri, int check, int dayweek, int year, int month, int day) {
                Intent intent = new Intent(view.getContext(),Execution_Activity.class);
                intent.putExtra("title",title);
                intent.putExtra("start",start);
                intent.putExtra("pri",pri);
                intent.putExtra("check",check);
                intent.putExtra("dayweek",dayweek);
                intent.putExtra("year",year);
                intent.putExtra("month",month);
                intent.putExtra("day",day);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        final RDatabase db = RDatabase.getAppDatabase(getContext());


        list = new ArrayList<>();
        recyclerView = view.findViewById(R.id.excution_recycler1);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        adapter = new Todolist_recycler(list);

        for (int i = 0; i < 4; i++){
            for (int s = 0; s < db.itemDao().thing(i).size(); s++){
                adapter.additem(db.itemDao().thing(i).get(s));
            }
        }
        recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(new Todolist_recycler.OnClickListener() {
            @Override
            public void OnClick(View view, int position, String title, int start,int stopwatch,int pri, int check, int dayweek, int year, int month, int day) {
                Intent intent = new Intent(view.getContext(),Execution_Activity.class);
                intent.putExtra("title",title);
                intent.putExtra("start",start);
                intent.putExtra("pri",pri);
                intent.putExtra("check",check);
                intent.putExtra("dayweek",dayweek);
                intent.putExtra("year",year);
                intent.putExtra("month",month);
                intent.putExtra("day",day);
                startActivity(intent);
            }

        });
    }

}