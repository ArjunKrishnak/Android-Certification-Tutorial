package com.example.android_developer_certification_tutorial.Notification;

import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.android_developer_certification_tutorial.R;

public class ProgressService extends JobService {
    private static final String TAG = "ProgressJobService";
    private boolean jobCancelled = false;
    private int notificationId;
    private NotificationCompat.Builder builder;
    private NotificationManagerCompat notificationManager;
    private int PROGRESS_MAX;
    private int PROGRESS_CURRENT;

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "Job started");
        notificationId = 100;
        notificationManager = NotificationManagerCompat.from(this);
        String CHANNEL_ID = params.getExtras().getString("ChannelID", "0");
        builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setContentTitle("Task Progress")
                .setContentText("Task in progress")
                .setSmallIcon( R.drawable.ic_launcher_foreground)
                .setOnlyAlertOnce(true)
                .setPriority(NotificationCompat.PRIORITY_LOW);

        // Issue the initial notification with zero progress
        PROGRESS_MAX = 100;
        PROGRESS_CURRENT = 0;
        builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);
        notificationManager.notify(notificationId, builder.build());


        doBackgroundWork(params);
        return true;//return false if job is not scheduled on a new thread
    }

    private void doBackgroundWork(final JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    Log.d(TAG, "run: " + i);
                    PROGRESS_CURRENT = i*10;
                    builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);
                    notificationManager.notify(notificationId, builder.build());

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