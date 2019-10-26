package com.example.android_developer_certification_tutorial;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BroadcastReceiverExample extends BroadcastReceiver {
    private static final String TAG = "BRExample";
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Intent Detected with Action: " + intent.getAction(), Toast.LENGTH_LONG).show();
        Log.d(TAG,"reached on receive");
    }
}
