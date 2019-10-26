package com.example.android_developer_certification_tutorial;

import android.app.job.JobParameters;
        import android.app.job.JobService;
        import android.util.Log;



public class ExampleJobService extends JobService {
    private static final String TAG = "ExampleJobService";
    private boolean jobCancelled = false;

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "Job started");
        long LongValue = params.getExtras().getLong("long_value_argument", -1);
        Log.d(TAG,"long_value_argument = "+LongValue);
        doBackgroundWork(params);
        return true;//return false if job is not scheduled on a new thread
    }

    private void doBackgroundWork(final JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    Log.d(TAG, "run: " + i);
                    if (jobCancelled) {
                        return;
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Log.d(TAG, "Job finished");
                jobFinished(params, false);
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "Job cancelled before completion");
        jobCancelled = true;
        return true;//return false if you dont want to reshedule the job
    }
}