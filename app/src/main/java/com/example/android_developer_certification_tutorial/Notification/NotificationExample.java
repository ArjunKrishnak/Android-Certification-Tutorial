package com.example.android_developer_certification_tutorial.Notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;

import com.example.android_developer_certification_tutorial.BroadcastReciever.BroadcastReceiverExample;
import com.example.android_developer_certification_tutorial.R;

public class NotificationExample extends AppCompatActivity {

    private static final String CHANNEL_ID = "1" ;
    public static int notificationId = 100;
    private static final String TAG = "NotificationExample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_notification_example );
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "xyzChannel";
            String description = "dummy channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void CreateNotification(View view) {
        // Create an explicit intent for an Activity in your app
        //Explicit — by using an explicit Intent, we can tell the Android system which exact Activity to start
        Intent intent = new Intent(this, NotificationExample.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //pendingIntent trigger foreign application to execute some predefined code at some point in the future,
        //by using the listed permissions of your application.
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);



        Intent BRintent = new Intent(this, BroadcastReceiverExample.class);
        BRintent.setAction("com.CUSTOM_INTENT");
        PendingIntent BRPendingIntent =
                PendingIntent.getBroadcast(this, 0, BRintent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder( this, CHANNEL_ID )
                .setSmallIcon( R.drawable.ic_launcher_background )
                .setContentTitle( "Notification" )
                .setContentText( "This is your notification" )
                .setPriority( NotificationCompat.PRIORITY_DEFAULT )
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.ic_launcher_foreground,"Start BR", BRPendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define

        notificationManager.notify(notificationId, builder.build());
    }

    public void CreateProgressNotitification(View view) {
        ComponentName componentName = new ComponentName(this, ProgressService.class);
        PersistableBundle bundle = new PersistableBundle();
        bundle.putString("ChannelID", CHANNEL_ID);
        JobInfo info = new JobInfo.Builder( 123, componentName )
                .setMinimumLatency( 1000 )   // Wait at least 1 second
                .setOverrideDeadline( 5000 ) // But no longer than 5 seconds (Not working for some reason!)
                .setExtras(bundle)
                .build();

        JobScheduler scheduler = (JobScheduler) getSystemService( JOB_SCHEDULER_SERVICE );
        int resultCode = scheduler.schedule(info);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Job scheduled");
        } else {
            Log.d(TAG, "Job scheduling failed");
        }
    }

}
