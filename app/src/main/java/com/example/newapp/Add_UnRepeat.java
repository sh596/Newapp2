package com.example.newapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

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
    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;

    private Button dayset;
    private Button today;
    private Button tomorrow;

    private GregorianCalendar calendar;

    private TextView unstart;
    private Dialog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.add_unrepeat, container, false);

        unstart = view.findViewById(R.id.unstartday);
        unedittext = view.findViewById(R.id.unedit);

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

    ////버튼 전체 색상 변경
    public void setdaycolor(){
        dayset.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.graybutton));
        today.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.graybutton));
        tomorrow.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.graybutton));
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

}
