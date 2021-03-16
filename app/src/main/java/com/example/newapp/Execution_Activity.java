package com.example.newapp;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Execution_Activity extends AppCompatActivity {
    private TextView title;
    private TextView timer;
    private TextView time;
    private TextView starttime;
    private TextView days;
    private TextView starttitle;

    private TextView pribtn1;
    private TextView pribtn2;
    private TextView pribtn3;
    private TextView pribtn4;
    private TextView pribtn5;

    private LinearLayout start;
    private LinearLayout check;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.execution);


        int id = getIntent().getIntExtra("pos", 10);
        String titleex = getIntent().getStringExtra("title");
        int start = getIntent().getIntExtra("start", 0);
        int pri = getIntent().getIntExtra("pri",0);
        int check = getIntent().getIntExtra("check",0);
        int dayweek = getIntent().getIntExtra("dayweek",0);
        int year = getIntent().getIntExtra("year",0);
        int month = getIntent().getIntExtra("month",1)+1;
        int day = getIntent().getIntExtra("day",0);

        title = findViewById(R.id.title);
        timer = findViewById(R.id.timer);
        time = findViewById(R.id.time);
        starttime = findViewById(R.id.starttime);
        days = findViewById(R.id.days);
        starttitle = findViewById(R.id.starttitle);

        pribtn1 = findViewById(R.id.pribtn1);
        pribtn2 = findViewById(R.id.pribtn2);
        pribtn3 = findViewById(R.id.pribtn3);
        pribtn4 = findViewById(R.id.pribtn4);
        pribtn5 = findViewById(R.id.pribtn5);

        switch (pri){
            case 0:
                pribtn1.setBackground(ContextCompat.getDrawable(this,R.drawable.bluebutton));
                pribtn1.setText("1");
                break;
            case 1:
                pribtn2.setBackground(ContextCompat.getDrawable(this,R.drawable.bluebutton));
                pribtn1.setText("1");
                break;
            case 2:
                pribtn3.setBackground(ContextCompat.getDrawable(this,R.drawable.bluebutton));
                pribtn1.setText("1");
                break;
            case 3:
                pribtn4.setBackground(ContextCompat.getDrawable(this,R.drawable.bluebutton));
                pribtn1.setText("1");
                break;
                case 4:
                pribtn5.setBackground(ContextCompat.getDrawable(this,R.drawable.bluebutton));
                pribtn1.setText("1");
                break;
        }

        switch (dayweek){
            case 0:
                days.setText(year+"-"+month+"-"+day);
                break;
            case 1:
                days.setText("일요일");
                break;
            case 2:
                days.setText("월요일");
                break;
            case 3:
                days.setText("화요일");
                break;
            case 4:
                days.setText("수요일");
                break;
            case 5:
                days.setText("목요일");
                break;
            case 6:
                days.setText("금요일");
                break;
            case 7:
                days.setText("토요일");
                break;
        }

        if (start == 0){
            starttitle.setText("");
            starttime.setText("");
        }else {
            int hour = start/100;
            int minute = start%100;
            starttime.setText(hour+":"+minute);
        }


        title.setText(titleex);



    }
}
