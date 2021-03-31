package com.example.newapp;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Execution_Activity extends AppCompatActivity {
    private boolean stopboolean = false;
    private RDatabase db;
    private int id;
    private int stoptime;
    private int stopwatch;
    int compl;

    private TextView title;
    private TextView timer;
    private TextView days;

    private TextView pribtn1;
    private TextView pribtn2;
    private TextView pribtn3;
    private TextView pribtn4;
    private TextView pribtn5;

    private ImageButton del;
    private ImageButton back;

    private LinearLayout startbtn;
    private ImageView startimage;
    private TextView starttext;

    private LinearLayout checkbtn;
    private ImageView comimage;
    private TextView comtext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.execution);

        db = RDatabase.getAppDatabase(this);

        //할 일의 정보를 받음
        id = getIntent().getIntExtra("pos", 10);
        String titleex = getIntent().getStringExtra("title");
        stopwatch = getIntent().getIntExtra("stop",0);
        int pri = getIntent().getIntExtra("pri",0);
        final int complete = getIntent().getIntExtra("check",0);
        int dayweek = getIntent().getIntExtra("dayweek",0);
        int year = getIntent().getIntExtra("year",0);
        int month = getIntent().getIntExtra("month",1)+1;
        int day = getIntent().getIntExtra("day",0);

        title = findViewById(R.id.title);// 제목
        timer = findViewById(R.id.timer);// 시간 측정
        days = findViewById(R.id.days);//날짜

        del = findViewById(R.id.del);
        back = findViewById(R.id.back);

        pribtn1 = findViewById(R.id.pribtn1);
        pribtn2 = findViewById(R.id.pribtn2);
        pribtn3 = findViewById(R.id.pribtn3);
        pribtn4 = findViewById(R.id.pribtn4);
        pribtn5 = findViewById(R.id.pribtn5);

        startbtn = findViewById(R.id.start); //시간 측정 버튼
        startimage = findViewById(R.id.startimage);
        starttext = findViewById(R.id.starttext);

        checkbtn = findViewById(R.id.check); // 완료 여부 버튼
        comimage = findViewById(R.id.comimage);
        comtext = findViewById(R.id.comtext);

        //시간 측정 쓰레드 시작
        Thread timestart = new Thread(new timeThread());
        timestart.start();

        //완료 여부에 따라 완료 버튼을 변경함
        if (complete == 0){
            setwhite();
        }else {
            setblue();
        }

        //할 일 삭제
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.itemDao().deleteid(id);
                finish();
            }
        });
        //종료 시 시간을 기록
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopupdate();
            }
        });

        checkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compl = complete;
                if (compl == 0){
                    dialogset(db,1,id);
                    compl = 1 ;
                }else {
                    db.itemDao().updatecom(0,id);
                    finish();
                    setwhite();
                    compl = 0;
                }
            }
        });
        // 시간 측정
        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopboolean = !stopboolean;
                if(stopboolean && compl == 0){
                    startbtn.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.bluebutton));
                    startimage.setColorFilter(Color.parseColor("#ffffff"));
                    starttext.setTextColor(Color.parseColor("#ffffff"));
                }else{
                    startbtn.setBackgroundColor(Color.parseColor("#00000000"));
                    startimage.setColorFilter(Color.parseColor("#AEBAFF"));
                    starttext.setTextColor(Color.parseColor("#AEBAFF"));
                }
            }
        });


        //우선 순위
        switch (pri){
            case 0:
                pribtn1.setBackground(ContextCompat.getDrawable(this,R.drawable.bluebutton));
                pribtn1.setText("1");
                break;
            case 1:
                pribtn2.setBackground(ContextCompat.getDrawable(this,R.drawable.bluebutton));
                pribtn2.setText("2");
                break;
            case 2:
                pribtn3.setBackground(ContextCompat.getDrawable(this,R.drawable.bluebutton));
                pribtn3.setText("3");
                break;
            case 3:
                pribtn4.setBackground(ContextCompat.getDrawable(this,R.drawable.bluebutton));
                pribtn4.setText("4");
                break;
                case 4:
                pribtn5.setBackground(ContextCompat.getDrawable(this,R.drawable.bluebutton));
                pribtn5.setText("5");
                break;
        }
        //요일 및 날짜
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



        title.setText(titleex);

    }
    //완료 표시 다이얼로그
    public void dialogset(final RDatabase db, final int com, final int id){
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("완료")
                .setMessage("완료로 표시하시겠습니까?")
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.itemDao().updatestop(stopwatch,id);
                        db.itemDao().updatecom(com,id);
                        setblue();
                        finish();
                    }
                })
                .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void setwhite(){
        comtext.setTextColor(Color.parseColor("#AEBAFF"));
        comimage.setColorFilter(Color.parseColor("#AEBAFF"));
        checkbtn.setBackgroundColor(Color.parseColor("#00000000"));
    }

    public void setblue(){
        comtext.setTextColor(Color.parseColor("#ffffff"));
        comimage.setColorFilter(Color.parseColor("#ffffff"));
        checkbtn.setBackground(ContextCompat.getDrawable(this,R.drawable.bluebutton));
    }

    //시간 계산을 위한 핸들러
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            stoptime = msg.arg1;
            int sec = msg.arg1 %60;
            int min = msg.arg1 /60;
            int hour = msg.arg1 /360;

            String result = String.format("%02d:%02d:%02d",hour,min,sec);
            timer.setText(result);
            super.handleMessage(msg);
        }
    };
    //시간 측정을 위한 쓰레드
    public class timeThread implements Runnable{

        @Override
        public void run() {
            int i = 0;
            while (true){
                while (stopboolean){
                    Message msg = new Message();
                    msg.arg1 = i++;
                    handler.sendMessage(msg);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    //액티비티 종료시 시간을 기록
    @Override
    public void onBackPressed() {
        stopupdate();
        super.onBackPressed();
    }
    public void stopupdate(){
        db.itemDao().updatestop(stopwatch+stoptime,id);
        finish();
    }
}