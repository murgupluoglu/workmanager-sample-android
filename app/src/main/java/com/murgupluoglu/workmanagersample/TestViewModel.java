package com.murgupluoglu.workmanagersample;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

/**
 * Created by mustafa.urgupluoglu on 6/7/18.
 */
public class TestViewModel extends ViewModel {

    private final String TAG_OUTPUT = "TAG_OUTPUT";
    private WorkManager mWorkManager;
    private LiveData<List<WorkInfo>> mSavedWorkStatus;

    LiveData<List<WorkInfo>> getOutputStatus() { return mSavedWorkStatus; }

    public TestViewModel() {

        mWorkManager = WorkManager.getInstance();

        // This transformation makes sure that whenever the current work Id changes the WorkStatus
        // the UI is listening to changes
        mSavedWorkStatus = mWorkManager.getWorkInfosByTagLiveData(TAG_OUTPUT);
    }

    void doJob(String randomString){
        Data inputData = new Data.Builder()
                .putString("MyInput", randomString + "\nMustafa")
                .build();

        OneTimeWorkRequest compressionWork =
                new OneTimeWorkRequest.Builder(TestWorker.class)
                        .addTag(TAG_OUTPUT)
                        .setInputData(inputData)
                        .build();

        WorkManager.getInstance().enqueue(compressionWork);
    }
}
