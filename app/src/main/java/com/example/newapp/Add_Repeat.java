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
import java.util.GregorianCalendar;

public class Add_Repeat extends Fragment{

    private View view;
    public int days;
    public int num;
    static EditText edit;
    public int starttime;

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

    private Button nonealram;
    private Button setalram;

    private TextView alramtext;
    private Dialog dialog;
    private Dialog timedialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.add_repeat,container,false);

        days = 1;
        num = 0;

        edit = view.findViewById(R.id.reedit);

        alramtext = view.findViewById(R.id.alramtext1);

        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    //요일 선택
                    case R.id.mon :
                        setdays();
                        mon.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.bluebutton));
                        days = 2;
                        break;
                    case R.id.tue :
                        setdays();
                        tue.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.bluebutton));
                        days = 3;
                        break;
                    case R.id.wen :
                        setdays();
                        wen.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.bluebutton));
                        days = 4;
                        break;
                    case R.id.thu :
                        setdays();
                        thu.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.bluebutton));
                        days = 5;
                        break;
                    case R.id.fri :
                        setdays();
                        fri.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.bluebutton));
                        days = 6;
                        break;
                    case R.id.sat :
                        setdays();
                        sat.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.bluebutton));
                        days = 7;
                        break;
                    case R.id.sun :
                        setdays();
                        sun.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.bluebutton));
                        days = 1;
                        break;
                    // 우선 순위
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
                    case R.id.nonealram :
                        setalram();
                        starttime = 0;
                        alramtext.setText("");
                        nonealram.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.bluebutton));
                        break;
                    case R.id.setalram :
                        setalram();
                        setDialog();
                        setalram.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.bluebutton));
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
        nonealram = view.findViewById(R.id.nonealram);
        setalram = view.findViewById(R.id.setalram);

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
        nonealram.setOnClickListener(onClickListener);
        setalram.setOnClickListener(onClickListener);

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.timer_dialog);

        timedialog = new Dialog(getContext());
        timedialog.setContentView(R.layout.time_dialog);

        return view;

    }
    //버튼 전체 색상 변경
    public void setdays(){
        mon.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
        tue.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
        wen.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
        thu.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
        fri.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
        sat.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
        sun.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
    }
    //버튼 전체 색상 변경
    public void setnum(){
        one.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
        two.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
        three.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
        four.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
        five.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
    }
    //버튼 전체 색상 변경
    public void setalram(){
        setalram.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
        nonealram.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
    }
    public void setDialog(){
        GregorianCalendar cal = new GregorianCalendar();

        TimePickerDialog dialog = new TimePickerDialog(view.getContext(), android.R.style.Theme_Holo_Light_Dialog ,new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                starttime = i*100+i1;
                String time = String.format("%02d : %02d", i, i1);
                alramtext.setText(time);
            }
        },cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),false);

        dialog.show();
    }


}
