package com.example.android_developer_certification_tutorial.AlarmManager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.android_developer_certification_tutorial.R;

public class AlarmManagerActivity extends AppCompatActivity {
    private PendingIntent pendingAlarmIntent;
    private AlarmManager alarmManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_alarm_manager );
        alarmManager = (AlarmManager) getSystemService( Context.ALARM_SERVICE);
        Intent alarmIntent = new Intent(this, AlarmBroadcastReceiver.class);
        alarmIntent.setAction("AlarmIntent");
        pendingAlarmIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
    }
    public void StartAlarm(View view){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, 0, pendingAlarmIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, 0, pendingAlarmIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, 0, pendingAlarmIntent);
        }
    }
    public void StopAlarm(View view){
        alarmManager.cancel(pendingAlarmIntent);
    }

}
