package com.example.newapp;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main_Activity extends FragmentActivity {

    private TextView days;
    private TabLayout tabs;
    private Button add;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        RDatabase db = RDatabase.getAppDatabase(this);

        GregorianCalendar calendar = new GregorianCalendar();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DATE);

        String dayValue = String.format("%04d-%02d-%02d",year,month,day);
        days = findViewById(R.id.day);//오늘 날짜

        days.setText(dayValue);

        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Add_Activity.class);
                startActivity(intent);
            }
        });

        ViewPager vp = findViewById(R.id.vp);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);
        vp.setCurrentItem(1);

        tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(vp);
        for(int i = 0; i < db.itemDao().getstarttime().size(); i++){
            Item time = db.itemDao().getstarttime().get(i);
            int timevalue = time.starttime;
            if(time.dayweek == 0){
                GregorianCalendar cal = new GregorianCalendar(time.year,time.month,time.day,timevalue/100,timevalue%100);
                setalarm(cal,false,i,time.title);
            }else{

            }
        }

    }
    public void setalarm(GregorianCalendar cal1, boolean repeate, int id, String title){
        Intent intent = new Intent(this, AlaramReceiver.class);
        PendingIntent pintent = PendingIntent.getBroadcast(this,id,intent, 0);
        intent.putExtra("title",title);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if(repeate){
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,cal1.getTimeInMillis(),7*24*60*60*1000,pintent);
        }
        alarmManager.set(AlarmManager.RTC_WAKEUP,cal1.getTimeInMillis(),pintent);

        alarmManager.cancel(pintent);
    }

}
