package com.example.android_developer_certification_tutorial.WorkManager;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class WorkerExample extends Worker {


    public WorkerExample(
            @NonNull Context appContext,
            @NonNull WorkerParameters workerParams) {
        super(appContext, workerParams);
    }
    public static final String EXTRA_TITLE = "title";
    @NonNull
    @Override
    public Result doWork() {
        try{
            Thread.sleep(5000);
            String Input = getInputData().getString("input");
            Data output = new Data.Builder()
                    .putString("output", "received "+Input+"\n"+"Output from Worker")
                    .build();
            return Result.success(output);

        }catch (Throwable throwable){
            return Result.failure();
        }
    }
}