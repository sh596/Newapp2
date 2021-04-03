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
    public EditText unedittext;
    public int starttime;

    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;

    private Button dayset;
    private Button today;
    private Button tomorrow;

    private GregorianCalendar calendar;

    private Button nonealram;
    private Button setalram;

    private TextView alramtext;
    private TextView unstart;
    private Dialog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.add_unrepeat, container, false);

        unstart = view.findViewById(R.id.unstartday);
        unedittext = view.findViewById(R.id.unedit);
        alramtext = view.findViewById(R.id.alramtext2);

        calendar = new GregorianCalendar();

        //기본 날짜 값을 오늘로 설정
        unyear = calendar.get(Calendar.YEAR);
        unmonth = calendar.get(Calendar.MONTH);
        unday = calendar.get(Calendar.DATE);


        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    //우선순위 설정
                    case R.id.unone:
                        setnum();
                        one.setBackground(ContextCompat.getDrawable(view.getContext(), R.drawable.bluebutton));
                        unnum = 0;
                        break;
                    case R.id.untwo:
                        setnum();
                        two.setBackground(ContextCompat.getDrawable(view.getContext(), R.drawable.bluebutton));
                        unnum = 1;
                        break;
                    case R.id.unthree:
                        setnum();
                        three.setBackground(ContextCompat.getDrawable(view.getContext(), R.drawable.bluebutton));
                        unnum = 2;
                        break;
                    case R.id.unfour:
                        setnum();
                        four.setBackground(ContextCompat.getDrawable(view.getContext(), R.drawable.bluebutton));
                        unnum = 3;
                        break;
                    case R.id.unfive:
                        setnum();
                        five.setBackground(ContextCompat.getDrawable(view.getContext(), R.drawable.bluebutton));
                        unnum = 4;
                        break;
                    //날짜 설정
                    case R.id.today:
                        setdaycolor();
                        unyear = calendar.get(Calendar.YEAR);
                        unmonth = calendar.get(Calendar.MONTH);
                        unday = calendar.get(Calendar.DATE);
                        today.setBackground(ContextCompat.getDrawable(view.getContext(), R.drawable.bluebutton));
                        unstart.setText("");

                        break;
                    case R.id.tomorrow:
                        setdaycolor();
                        unyear = calendar.get(Calendar.YEAR);
                        unmonth = calendar.get(Calendar.MONTH);
                        unday = calendar.get(Calendar.DATE) + 1;
                        tomorrow.setBackground(ContextCompat.getDrawable(view.getContext(), R.drawable.bluebutton));
                        unstart.setText("");
                        break;
                    case R.id.nonealram:
                        setalram();
                        starttime = 0;
                        alramtext.setText("");
                        nonealram.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.bluebutton));
                        break;
                    case R.id.setalram:
                        setalram();
                        setDialog();
                        setalram.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.bluebutton));
                        break;
                }
            }
        };

        one = view.findViewById(R.id.unone);
        two = view.findViewById(R.id.untwo);
        three = view.findViewById(R.id.unthree);
        four = view.findViewById(R.id.unfour);
        five = view.findViewById(R.id.unfive);
        nonealram = view.findViewById(R.id.nonealram);
        setalram = view.findViewById(R.id.setalram);

        one.setOnClickListener(onClickListener);
        two.setOnClickListener(onClickListener);
        three.setOnClickListener(onClickListener);
        four.setOnClickListener(onClickListener);
        five.setOnClickListener(onClickListener);
        nonealram.setOnClickListener(onClickListener);
        setalram.setOnClickListener(onClickListener);

        dayset = view.findViewById(R.id.dayset);
        today = view.findViewById(R.id.today);
        tomorrow = view.findViewById(R.id.tomorrow);


        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.timer_dialog);

        //날짜 직접 설정
        dayset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdayDailog();
            }
        });

        today.setOnClickListener(onClickListener);
        tomorrow.setOnClickListener(onClickListener);


        return view;
    }
    ////버튼 전체 색상 변경
    public void setnum(){
        one.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
        two.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
        three.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
        four.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
        five.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
    }

    //버튼 전체 색상 변경
    public void setdaycolor(){
        dayset.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.graybutton));
        today.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.graybutton));
        tomorrow.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.graybutton));
    }
    //버튼 전체 색상 변경
    public void setalram(){
        setalram.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
        nonealram.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
    }

    //날짜 생성 다이얼로그
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

    public void setDialog(){
        GregorianCalendar cal = new GregorianCalendar();

        TimePickerDialog dialog = new TimePickerDialog(view.getContext(), android.R.style.Theme_Holo_Light_Dialog ,new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                starttime = i*100+i1;
                if(i > 12){
                    i-=12;
                }

                String time = String.format("%02d : %02d", i, i1);
                alramtext.setText(time);
            }
        },cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),false);

        dialog.show();
    }

}
