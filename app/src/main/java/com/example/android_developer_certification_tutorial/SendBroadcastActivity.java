package com.example.android_developer_certification_tutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SendBroadcastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_send_broadcast );
    }

    public void SendCustomBroadcast(View v){ //Doesnt Work on Emulator for some reason
        Intent intent = new Intent();
        intent.setAction("com.CUSTOM_INTENT");
        sendBroadcast(intent);
    }

}
