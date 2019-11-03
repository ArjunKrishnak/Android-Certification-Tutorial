package com.example.android_developer_certification_tutorial.OrientationChange;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android_developer_certification_tutorial.R;

public class OrientationChange extends AppCompatActivity {
    int mCount;
    TextView mTextViewCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_orientation_change );
        mTextViewCount = findViewById(R.id.textView);

        if (savedInstanceState != null) {
            mCount = savedInstanceState.getInt("count");
            mTextViewCount.setText(String.valueOf(mCount));
            //you can also do this in overrided onRestoreInstanceState() method
            // which is called after onStart() and before onResume().
        }
    }

    public void decrement(View view) {
        mCount--;
        mTextViewCount.setText(String.valueOf(mCount));
    }

    public void increment(View view) {
        mCount++;
        mTextViewCount.setText(String.valueOf(mCount));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("count", mCount);
    }
}
