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

    public ArrayList<DateItem> mcalenderlist = new ArrayList<>();

    private GregorianCalendar cal;
    private GregorianCalendar cal2;

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.week,container,false);
        calender = view.findViewById(R.id.calender);


        return view;
    }


}
