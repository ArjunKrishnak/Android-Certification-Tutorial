package com.example.android_developer_certification_tutorial.JobScheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.android_developer_certification_tutorial.R;

public class JobSchedulerExample extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_job_scheduler_example );
    }


    public void scheduleJob(View v) {
        Log.d(TAG, "Job scheduling started");
        ComponentName componentName = new ComponentName(this, ExampleJobService.class);
//        JobInfo info = new JobInfo.Builder(123, componentName)
//                .setRequiresCharging(true)
//                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
//                .setPersisted(true)
//                .setPeriodic(15 * 60 * 1000)
//                .build();

        // Bundle up parameters

        PersistableBundle bundle = new PersistableBundle();
        bundle.putLong("long_value_argument", 10);
        JobInfo info = new JobInfo.Builder(123, componentName)
                .setMinimumLatency(1000)   // Wait at least 1 second
                .setOverrideDeadline(5000) // But no longer than 5 seconds (Not working for some reason!)
                .setExtras(bundle)
                .build();

        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(info);
        TextView LoggerTextV = findViewById(R.id.LoggingTextView);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Job scheduled");
            LoggerTextV.setText("Job scheduled");
        } else {
            Log.d(TAG, "Job scheduling failed");
            LoggerTextV.setText("Job scheduling failed");
        }
    }

    public void cancelJob(View v) {
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(123);
        Log.d(TAG, "Job cancelled");
    }
}
