package com.example.android_developer_certification_tutorial.AsyncTaskExample;

import androidx.appcompat.app.AppCompatActivity;
import com.example.android_developer_certification_tutorial.R;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AsyncTaskActivity extends AppCompatActivity {
    private Button button;
    private EditText time;
    private TextView finalResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_async_task );
        time = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        finalResult = (TextView) findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskRunner runner = new AsyncTaskRunner();
                String sleepTime = time.getText().toString();
                runner.execute(sleepTime);
            }
        });
    }

    public void onClick(View view){

    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Sleeping..."); // Calls onProgressUpdate()
            try {
                int time = Integer.parseInt(params[0])*1000;

                Thread.sleep(time);
                resp = "Slept for " + params[0] + " seconds";
            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }


        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            progressDialog.dismiss();
            finalResult.setText(result);
        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(AsyncTaskActivity.this,
                    "ProgressDialog",
                    "Wait for "+time.getText().toString()+ " seconds");
        }


        @Override
        protected void onProgressUpdate(String... text) {
            finalResult.setText(text[0]);

        }
    }
}

//    The basic methods used in an android AsyncTask class are defined below :
//
//        doInBackground() : This method contains the code which needs to be executed in background. In this method we can send results multiple times to the UI thread by publishProgress() method. To notify that the background processing has been completed we just need to use the return statements
//        onPreExecute() : This method contains the code which is executed before the background processing starts
//        onPostExecute() : This method is called after doInBackground method completes processing. Result from doInBackground is passed to this method
//        onProgressUpdate() : This method receives progress updates from doInBackground method, which is published via publishProgress method, and this method can use this progress update to update the UI thread
//        The three generic types used in an android AsyncTask class are given below :
//
//        Params : The type of the parameters sent to the task upon execution
//        Progress : The type of the progress units published during the background computation
//        Result : The type of the result of the background computation
