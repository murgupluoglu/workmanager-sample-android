package com.murgupluoglu.workmanagersample;

import android.content.Context;
import android.support.annotation.NonNull;

import com.blankj.utilcode.util.LogUtils;

import androidx.work.Data;
import androidx.work.Worker;

/**
 * Created by mustafa.urgupluoglu on 6/7/18.
 */
public class TestWorker extends Worker {

    private static final String TAG = TestWorker.class.getSimpleName();

    @NonNull
    @Override
    public WorkerResult doWork() {

        Context applicationContext = getApplicationContext();

        LogUtils.e(TAG + " started");

        String myInput = getInputData().getString("MyInput", null);
        LogUtils.e("myInput: " + myInput);

        Data output = new Data.Builder()
                .putString("MyOutput", myInput + " Urgupluoglu\nid:" + getId())
                .build();
        setOutputData(output);

        return WorkerResult.SUCCESS;
    }
}