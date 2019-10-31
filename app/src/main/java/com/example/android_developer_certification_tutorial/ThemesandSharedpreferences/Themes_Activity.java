package com.example.android_developer_certification_tutorial.ThemesandSharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.android_developer_certification_tutorial.R;

public class Themes_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        InitApplication.getInstance().onActivityCreateSetTheme(this);
        setContentView( R.layout.activity_themes );
    }


    public void ButtonClick(View v)
    {
        switch (v.getId())
        {
            case R.id.button1:
                InitApplication.getInstance().changeToTheme(this, 0);
                break;
            case R.id.button2:
                InitApplication.getInstance().changeToTheme(this, 1);
                break;
            case R.id.button3:
                InitApplication.getInstance().changeToTheme(this, 2);
                break;
        }
    }


}
