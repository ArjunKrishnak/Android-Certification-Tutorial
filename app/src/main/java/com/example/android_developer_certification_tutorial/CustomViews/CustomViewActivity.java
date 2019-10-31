package com.example.android_developer_certification_tutorial.CustomViews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.android_developer_certification_tutorial.R;

public class CustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_custom_view );
    }

    public void onClick(View view) {
        SimpleCustomView sv= findViewById(R.id.simpleCustomView);
        switch (view.getId()){
            case R.id.one:{
                sv.customPaddingDown(30);
                break;
            }
            case R.id.two:{
                sv.swapColor();
                break;
            }
            case R.id.three:{
                sv.customPaddingUp(30);
            }
        }
    }
}
