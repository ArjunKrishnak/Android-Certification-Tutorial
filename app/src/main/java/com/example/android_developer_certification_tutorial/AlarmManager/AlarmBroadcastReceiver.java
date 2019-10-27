package com.example.android_developer_certification_tutorial.AlarmManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
//        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
//            Intent serviceIntent = new Intent(context, AlaramManagerExample.class);
//            context.startService(serviceIntent);
//        } else {
//            Toast.makeText(context.getApplicationContext(), "Alarm Manager just ran", Toast.LENGTH_LONG).show();
//        }
        Toast.makeText(context.getApplicationContext(), "Alarm just ran", Toast.LENGTH_SHORT).show();
        Intent serviceIntent = new Intent(context, AlaramManagerExample.class);
        context.startService(serviceIntent);
    }
}