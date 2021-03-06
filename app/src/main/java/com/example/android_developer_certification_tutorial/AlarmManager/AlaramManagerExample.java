package com.example.android_developer_certification_tutorial.AlarmManager;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;

public class AlaramManagerExample extends IntentService {

    public AlaramManagerExample() {
        super("AlaramManagerExample");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        AlarmManager alarmManager = (AlarmManager) getSystemService( Context.ALARM_SERVICE);
        Intent alarmIntent = new Intent(this, AlarmBroadcastReceiver.class);
        alarmIntent.setAction("AlarmIntent");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        //alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, 0, 10000, pendingIntent);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,60000,pendingIntent);
    }
}
