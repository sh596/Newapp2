package com.example.newapp;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

public class Add_Repeat extends Fragment {

    private View view;
    public int days;
    public int num;
    public int timernum;
    public String time;
    public String date;
    public int realram;
    static EditText edit;

    private TextView timer;
    private TextView dateText;
    private TextView alramText;

    private Button mon;
    private Button tue;
    private Button wen;
    private Button thu;
    private Button fri;
    private Button sat;
    private Button sun;

    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button alram;
/*
    private Button timeset;
    private Button notime;
    private Button relast;*/

    private Dialog dialog;
    private Dialog timedialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.add_repeat,container,false);

        days = 1;
        num = 0;

        edit = view.findViewById(R.id.reedit);
        /*
        timeset = view.findViewById(R.id.retimeset);
        relast = view.findViewById(R.id.relast);
        notime = view.findViewById(R.id.notime);

        timer = view.findViewById(R.id.redeadline);
        dateText= view.findViewById(R.id.refinish);
        */

        alramText = view.findViewById(R.id.alramtext);
        alram = view.findViewById(R.id.alram);
        alram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_NoActionBar, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        if(i > 12){
                            i -= 12;
                        }

                        if (i1 < 10){
                            alramText.setText(i+":0"+i1);
                        }else{
                            alramText.setText(i+":"+i1);}

                        realram = i*100 + i1;
                    }
                },cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),false);
                timePickerDialog.show();
            }
        });


        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.mon :
                        setdays();
                        mon.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.bluebutton));
                        days = 1;
                        break;
                    case R.id.tue :
                        setdays();
                        tue.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.bluebutton));
                        days = 2;
                        break;
                    case R.id.wen :
                        setdays();
                        wen.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.bluebutton));
                        days = 3;
                        break;
                    case R.id.thu :
                        setdays();
                        thu.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.bluebutton));
                        days = 4;
                        break;
                    case R.id.fri :
                        setdays();
                        fri.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.bluebutton));
                        days = 5;
                        break;
                    case R.id.sat :
                        setdays();
                        sat.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.bluebutton));
                        days = 6;
                        break;
                    case R.id.sun :
                        setdays();
                        sun.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.bluebutton));
                        days = 7;
                        break;
                    case R.id.one :
                        setnum();
                        one.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.bluebutton));
                        num = 0;
                        break;
                    case R.id.two :
                        setnum();
                        two.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.bluebutton));
                        num = 1;
                        break;
                    case R.id.three :
                        setnum();
                        three.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.bluebutton));
                        num = 2;
                        break;
                    case R.id.four :
                        setnum();
                        four.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.bluebutton));
                        num = 3;
                        break;
                    case R.id.five :
                        setnum();
                        five.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.bluebutton));
                        num = 4;
                        break;
                }
            }
        };

        mon = view.findViewById(R.id.mon);
        tue = view.findViewById(R.id.tue);
        wen = view.findViewById(R.id.wen);
        thu = view.findViewById(R.id.thu);
        fri = view.findViewById(R.id.fri);
        sat = view.findViewById(R.id.sat);
        sun = view.findViewById(R.id.sun);

        one = view.findViewById(R.id.one);
        two = view.findViewById(R.id.two);
        three = view.findViewById(R.id.three);
        four = view.findViewById(R.id.four);
        five = view.findViewById(R.id.five);

        mon.setOnClickListener(onClickListener);
        tue.setOnClickListener(onClickListener);
        wen.setOnClickListener(onClickListener);
        thu.setOnClickListener(onClickListener);
        fri.setOnClickListener(onClickListener);
        sat.setOnClickListener(onClickListener);
        sun.setOnClickListener(onClickListener);

        one.setOnClickListener(onClickListener);
        two.setOnClickListener(onClickListener);
        three.setOnClickListener(onClickListener);
        four.setOnClickListener(onClickListener);
        five.setOnClickListener(onClickListener);

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.timer_dialog);

        timedialog = new Dialog(getContext());
        timedialog.setContentView(R.layout.time_dialog);
/*
        timeset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        relast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showtime();
;
            }
        });
        notime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settimecolor();
                notime.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.bluebutton));
                timer.setText("");
                dateText.setText("");
            }
        });*/

        return view;

    }
    public void setdays(){
        mon.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
        tue.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
        wen.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
        thu.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
        fri.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
        sat.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
        sun.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
    }
    public void setnum(){
        one.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
        two.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
        three.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
        four.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
        five.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
    }

  /*
    public void settimecolor(){

        timeset.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.graybutton));
        relast.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.graybutton));
        notime.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.graybutton));
    }*/

    /*
    public void showDialog(){
        dialog.show();
        Button dialogset = dialog.findViewById(R.id.timerset);
        Button dialogback = dialog.findViewById(R.id.timerback);

        final NumberPicker nump1 = dialog.findViewById(R.id.nump1);
        final NumberPicker nump2 = dialog.findViewById(R.id.nump2);
        final NumberPicker nump3 = dialog.findViewById(R.id.nump3);

        nump1.setMaxValue(23);
        nump1.setMinValue(0);
        nump1.setValue(0);
        nump2.setMaxValue(59);
        nump2.setMinValue(0);
        nump2.setValue(0);
        nump3.setMaxValue(59);
        nump3.setMinValue(0);
        nump3.setValue(0);

        dialogback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialogset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = nump1.getValue();
                int min = nump2.getValue();
                int sec = nump3.getValue();


                timernum = hour*360+min*60+sec;
                if(min < 10 && sec < 10){
                    time = hour+":0"+min+":0"+sec;
                }else if (min < 10 && sec > 10){
                    time = hour + ":0" + min+":"+sec;
                }else if(min > 10 && sec < 10){
                    time = hour + ":" + min+":0"+sec;
                }else {
                    time = hour + ":" + min+":"+sec;
                }
                timer.setText(time);
                dateText.setText("");
                settimecolor();
                timeset.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.bluebutton));
                dialog.dismiss();
            }
        });

    }*/

    /*
    public void showtime(){
        int dhour = 0;
        int dminute = 0;

        Calendar cal = Calendar.getInstance();
        dhour = cal.get(Calendar.HOUR_OF_DAY);
        dminute = cal.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                if(i > 12){
                    i -= 12;
                }
                if(i1 < 10){
                    date = i+":0"+i1;
                }else {
                    date = i+":"+i1;

                }
                dateText.setText(date);
                settimecolor();
                relast.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.bluebutton));
                timer.setText("");
            }
        },dhour,dminute,false);
        timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        timePickerDialog.show();

    }*/
}
