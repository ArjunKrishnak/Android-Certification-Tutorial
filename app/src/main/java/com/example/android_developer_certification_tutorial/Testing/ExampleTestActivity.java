package com.example.android_developer_certification_tutorial.Testing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android_developer_certification_tutorial.R;

public class ExampleTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_example_test );
    }

    public void onCheck(View view){
        String FirstString = ((EditText)findViewById(R.id.FirstEditText )).getText().toString();
        String SecondString = ((EditText)findViewById(R.id.FirstEditText )).getText().toString();
        StringComparer Comparer = new StringComparer();
        String Result = Comparer.CheckString(FirstString,SecondString);
        TextView resultTV = (TextView) findViewById(R.id.ResultTextView );
        resultTV.setText(Result);
    }

}
