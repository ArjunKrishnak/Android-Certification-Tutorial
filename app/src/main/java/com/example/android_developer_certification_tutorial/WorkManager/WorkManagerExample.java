package com.example.android_developer_certification_tutorial.WorkManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android_developer_certification_tutorial.R;

import java.util.UUID;

public class WorkManagerExample extends AppCompatActivity {

    private UUID workId;
    private WorkManager mWorkmanger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_work_manager_example );
    }
    public void StartWork(View view) {
        Data data = new Data.Builder()
                .putString("input", "Message from Activity!")
                .build();
        Constraints constraints = new Constraints.Builder()
                .setRequiresCharging(true)
                .setRequiresDeviceIdle(true)
                .build();
        OneTimeWorkRequest simpleRequest = new OneTimeWorkRequest.Builder( WorkerExample.class )
                .setInputData(data)
//                .setConstraints(constraints)
                .build();
        workId = simpleRequest.getId();
        mWorkmanger = WorkManager.getInstance(this);
        mWorkmanger.enqueue( simpleRequest );
        final TextView mTextView = findViewById( R.id.StatusMessages );

        mWorkmanger.getWorkInfoByIdLiveData(simpleRequest.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(@Nullable WorkInfo workStatus) {
                        if (workStatus != null) {
                            mTextView.append("SimpleWorkRequest: " + workStatus.getState().name() + "\n");
                        }
                        if (workStatus != null && workStatus.getState().isFinished()) {
                            String output = workStatus.getOutputData().getString("output");
                            mTextView.append("SimpleWorkRequest (Data): " + output);
                        }
                    }
                });
    }

    public void CancelWork(View view){
        mWorkmanger.cancelWorkById(workId);
    }

}
