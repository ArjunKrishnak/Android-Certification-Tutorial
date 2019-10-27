package com.example.android_developer_certification_tutorial.ForegroundService;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.android_developer_certification_tutorial.R;

public class ForegroundServiceActivity extends AppCompatActivity {
    private String CHANNEL_ID = "Foreground service channel";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_foreground_service );
        createNotificationChannel();
    }

    public void StartService(View view){
        String input = CHANNEL_ID;

        Intent serviceIntent = new Intent(getBaseContext(), ForegroundServiceExample.class);
        serviceIntent.putExtra("inputExtra", input);

        ContextCompat.startForegroundService(getBaseContext(), serviceIntent);
//        startService(new Intent(this, ForegroundServiceExample.class));
//        startService(serviceIntent);
    }

    public void StopService(View view){
        Intent serviceIntent = new Intent(this, ForegroundServiceExample.class);
        stopService(serviceIntent);
    }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Example Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }
}
