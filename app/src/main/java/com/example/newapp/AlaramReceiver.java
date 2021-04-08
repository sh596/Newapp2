package com.example.newapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.util.GregorianCalendar;

public class AlaramReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        RDatabase db = RDatabase.getAppDatabase(context);

        Bundle bundle = intent.getExtras();
        int id = bundle.getInt("id");
        String title = bundle.getString("title");
        Log.d("id", "id : " + id);

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent notify = new Intent(context,Main_Activity.class);

        notify.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pintent = PendingIntent.getActivity(context,0,notify,0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "default");

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            builder.setSmallIcon(R.drawable.ic_hexagon);

            String channelName = "반복알람 채널";
            String description = "반복하는 알람";
            int imporatance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel("default",channelName, imporatance);
            channel.setDescription(description);

            if(notificationManager != null){
                notificationManager.createNotificationChannel(channel);
            }
        }else builder.setSmallIcon(R.mipmap.ic_logo_round);
        builder
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_hexagon)
                .setContentTitle(title)
                .setContentText("할 일이 생겼어요")
                .setContentInfo("INFO")
                .setContentIntent(pintent);
        if(notificationManager != null){
            notificationManager.notify(id,builder.build());
        }

    }
}

