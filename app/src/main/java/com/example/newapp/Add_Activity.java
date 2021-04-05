package com.example.newapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Add_Activity extends AppCompatActivity {


    Add_Repeat add_repeat;
    Add_UnRepeat add_unRepeat;

    private Button rebutton;
    private Button unrebutton;
    private Button addbutton;

    private ImageButton backbtn;

    boolean repeate = false; // 반복여부 값

    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        final RDatabase db = RDatabase.getAppDatabase(this);

        fm = getSupportFragmentManager();
        rebutton = findViewById(R.id.repeat_button);
        unrebutton = findViewById(R.id.unrepeat_button);

        add_repeat = new Add_Repeat();
        add_unRepeat = new Add_UnRepeat();

        backbtn = findViewById(R.id.backbtn);

        //프래그먼트 설정
        ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout,add_unRepeat).commitAllowingStateLoss();
        rebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!repeate) {
                    rebutton.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.bluebutton));
                    unrebutton.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.graybutton));
                    repeate = !repeate;
                }
                ft = fm.beginTransaction();
                ft.replace(R.id.frameLayout,add_repeat).commitAllowingStateLoss();
            }
        });
        unrebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (repeate) {
                    rebutton.setBackground(ContextCompat.getDrawable(view.getContext(), R.drawable.graybutton));
                    unrebutton.setBackground(ContextCompat.getDrawable(view.getContext(), R.drawable.bluebutton));
                    repeate = !repeate;
                }
                ft = fm.beginTransaction();
                ft.replace(R.id.frameLayout,add_unRepeat).commitAllowingStateLoss();
            }
        });



        //추가 버튼을 클릭했을 때
        addbutton = findViewById(R.id.addButton);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GregorianCalendar cal = new GregorianCalendar();
                if(repeate){
                    String name = add_repeat.edit.getText().toString();
                    if (!(name.getBytes().length == 0)){//제목이 없으면 추가되지 않음
                        int num = add_repeat.num;//우선 순위
                        int days = add_repeat.days;
                        int starttime = add_repeat.starttime;
                        db.itemDao().insert(new Item(name,0,starttime,num,0,days,0,0,0));


                        finish();
                    }else {
                        Toast.makeText(view.getContext(),"제목을 입력해주세요",Toast.LENGTH_SHORT).show();
                    }
                }else{

                    String unname = add_unRepeat.unedittext.getText().toString();

                    if (!(unname.getBytes().length == 0)){//제목이 없으면 추가되지 않음
                        int num = add_unRepeat.unnum;//우선 순위
                        int day = add_unRepeat.unday;
                        int month = add_unRepeat.unmonth;
                        int year = add_unRepeat.unyear;
                        int starttime = add_unRepeat.starttime;
                        db.itemDao().insert(new Item(unname,0,starttime,num,0,0,year,
                                month,day));
                        Intent intent = new Intent(view.getContext(), AlaramReceiver.class);
                        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                        for (int i = 0; i <db.itemDao().getstarttime().size(); i++){
                            PendingIntent pintent = PendingIntent.getBroadcast(view.getContext(),i,intent, 0);
                            alarmManager.cancel(pintent);
                        }

                        for(int i = 0; i < db.itemDao().getstarttime().size(); i++){
                            Item time = db.itemDao().getstarttime().get(i);
                            int timevalue = time.starttime;
                            PendingIntent pintent = PendingIntent.getBroadcast(view.getContext(),i,intent, 0);
                            if(time.dayweek == 0){
                                Calendar cal2 = Calendar.getInstance();
                                cal2.set(time.year,time.month,time.day,timevalue/100,timevalue%100);
                                if(cal2.getTimeInMillis() >= cal.getTimeInMillis()){
                                    setalarm(alarmManager,cal2,false,i,time.title,pintent,intent);
                                    Log.d("알림","알림 설정");
                                }
                            }
                        }

                        finish();
                    }else {
                        Toast.makeText(view.getContext(),"제목을 입력해주세요",Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        //뒤로가기 버튼
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void setalarm(AlarmManager alarmManager,Calendar cal, boolean repeate, int id, String title,PendingIntent pintent,Intent intent){
        intent.putExtra("title",title);
        intent.putExtra("id",id);

        if(repeate){
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),7*24*60*60*1000,pintent);
        }else{
            alarmManager.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),pintent);
        }
    }


}
