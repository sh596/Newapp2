package com.example.newapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Add_UnRepeat extends Fragment {

    View view;
    public int unyear;
    public int unmonth;
    public int unday;
    public int unnum;
    public String untime;
    public String date;
    public int untimernum;
    public EditText unedittext;
    public int unalram;
    public int timelimit;
    public int lasttime;

    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
/*
    private Button untimeset;
    private Button notime;
    private Button unlast;*/
    private Button dayset;
    private Button today;
    private Button tomorrow;

    private Button alram;
    private Button noalram;

    private TextView unfinish;
    private TextView unstart;
    private TextView alramtext;
    private Dialog dialog;

    TextView timer;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.add_unrepeat,container,false);



        alramtext = view.findViewById(R.id.alramtext);
        unstart = view.findViewById(R.id.unstartday);
        /*
        timer = view.findViewById(R.id.undeadline);
        unfinish = view.findViewById(R.id.unfinish);
        untimeset = view.findViewById(R.id.untimeset);
        unlast = view.findViewById(R.id.unlast);
        notime= view.findViewById(R.id.notime);
        */
        unedittext = view.findViewById(R.id.unedit);

        noalram = view.findViewById(R.id.noalarm);
        alram = view.findViewById(R.id.alram);


        final GregorianCalendar calendar = new GregorianCalendar();

        unyear = calendar.get(Calendar.YEAR);
        unmonth = calendar.get(Calendar.MONTH);
        unday = calendar.get(Calendar.DATE);


        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {

                    case R.id.unone :
                        setnum();
                        one.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.bluebutton));
                        unnum = 0;
                        break;
                    case R.id.untwo :
                        setnum();
                        two.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.bluebutton));
                        unnum = 1;
                        break;
                    case R.id.unthree :
                        setnum();
                        three.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.bluebutton));
                        unnum = 2;
                        break;
                    case R.id.unfour :
                        setnum();
                        four.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.bluebutton));
                        unnum = 3;
                        break;
                    case R.id.unfive :
                        setnum();
                        five.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.bluebutton));
                        unnum = 4;
                        break;
                    case R.id.today :
                        setdaycolor();
                        unyear = calendar.get(Calendar.YEAR);
                        unmonth = calendar.get(Calendar.MONTH);
                        unday = calendar.get(Calendar.DATE);
                        today.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.bluebutton));

                        break;
                    case R.id.tomorrow :
                        setdaycolor();
                        unyear = calendar.get(Calendar.YEAR);
                        unmonth = calendar.get(Calendar.MONTH);
                        unday = calendar.get(Calendar.DATE)+1;
                        tomorrow.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.bluebutton));
                        break;
                }
            }
        };

        one = view.findViewById(R.id.unone);
        two = view.findViewById(R.id.untwo);
        three = view.findViewById(R.id.unthree);
        four = view.findViewById(R.id.unfour);
        five = view.findViewById(R.id.unfive);

        one.setOnClickListener(onClickListener);
        two.setOnClickListener(onClickListener);
        three.setOnClickListener(onClickListener);
        four.setOnClickListener(onClickListener);
        five.setOnClickListener(onClickListener);


        dayset = view.findViewById(R.id.dayset);
        today = view.findViewById(R.id.today);
        tomorrow = view.findViewById(R.id.tomorrow);


        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.timer_dialog);
        /*
        untimeset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        unlast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdate();
            }
        });*/
        dayset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdayDailog();
            }
        });
        today.setOnClickListener(onClickListener);
        tomorrow.setOnClickListener(onClickListener);

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
                            alramtext.setText(i+":0"+i1);
                        }else{
                            alramtext.setText(i+":"+i1);}

                        unalram = i*100 + i1;
                    }
                },cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),false);
                timePickerDialog.show();
            }
        });

        return view;
    }
    public void setnum(){
        one.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
        two.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
        three.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
        four.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
        five.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
    }

    public void settimecolor(){
        /*
        untimeset.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.graybutton));
        unlast.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.graybutton));
        notime.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.graybutton));*/
    }
    public void setdaycolor(){
        dayset.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.graybutton));
        today.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.graybutton));
        tomorrow.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.graybutton));
    }

    public void showdayDailog(){
        Calendar cal = Calendar.getInstance();
        DatePickerDialog datePicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                unyear = i;
                unmonth = i1;
                unday = i2;
                unstart.setText(i1+"월" +i2+"일");
            }
        },cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE));

        datePicker.show();
        datePicker.setTitle("날짜 설정");
        setdaycolor();
        dayset.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.bluebutton));
        datePicker.getDatePicker().setMinDate(System.currentTimeMillis());
    }
    /*
    public void showDialog(){
        dialog.show();
        Button dialogset = dialog.findViewById(R.id.timerset);
        Button dialogback = dialog.findViewById(R.id.timerback);

        final NumberPicker nump1 = dialog.findViewById(R.id.nump1);
        final NumberPicker nump2 = dialog.findViewById(R.id.nump2);
        final NumberPicker nump3 = dialog.findViewById(R.id.nump3);

        nump1.setMaxValue(99);
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

                untimernum = hour*360+min*60+sec;
                if(min < 10 && sec < 10){
                    untime = hour+":0"+min+":0"+sec;
                }else if (min < 10 && sec > 10){
                    untime = hour + ":0" + min+":"+sec;
                }else if(min > 10 && sec < 10){
                    untime = hour + ":" + min+":0"+sec;
                }else {
                    untime = hour + ":" + min+":"+sec;
                }
                timer.setText(untime);
                unfinish.setText("");
                timelimit = hour*10000+min*100+sec;
                settimecolor();
                untimeset.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.bluebutton));
                dialog.dismiss();
            }
        });
}*/
/*
    public void showdate(){
        int dyear = 0;
        int dmonth = 0;
        int dday = 0;

        Calendar cal = Calendar.getInstance();
        dyear = cal.get(Calendar.YEAR);
        dmonth = cal.get(Calendar.MONTH);
        dday = cal.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String datevalue = i1+"월"+i2+"일";
                showtime(datevalue);
            }
        },dyear,dmonth,dday);
        datePickerDialog.show();
        datePickerDialog.setTitle("날짜 설정");
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
    }

    public void showtime(final String ddate){
        int dhour = 0;
        int dminute = 0;

        String sminute;

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

                if (i1 < 10){
                    date = ddate+"\n"+ i+":0"+i1;
                }else{
                    date = ddate+"\n"+i+":"+i1;}
                unfinish.setText(date);
                settimecolor();
                unlast.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.bluebutton));
                timer.setText("");
            }
        },dhour,dminute,false);
        timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        timePickerDialog.show();

    }*/
}
