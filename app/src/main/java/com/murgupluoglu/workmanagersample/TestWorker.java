package com.murgupluoglu.workmanagersample;

import android.content.Context;
import android.support.annotation.NonNull;

import com.blankj.utilcode.util.LogUtils;

import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * Created by mustafa.urgupluoglu on 6/7/18.
 */
public class TestWorker extends Worker {

    private static final String TAG = TestWorker.class.getSimpleName();

    public TestWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Worker.Result doWork() {

        Context applicationContext = getApplicationContext();

        LogUtils.e(TAG + " started");

        String myInput = getInputData().getString("MyInput");
        LogUtils.e("myInput: " + myInput);

        Data output = new Data.Builder()
                .putString("MyOutput", myInput + " Urgupluoglu\nid:" + getId())
                .build();

        return Result.success(output);
    }
}