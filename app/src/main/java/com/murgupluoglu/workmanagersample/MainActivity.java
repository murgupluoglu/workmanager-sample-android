package com.murgupluoglu.workmanagersample;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;

import java.util.List;
import java.util.Random;

import androidx.work.Data;
import androidx.work.WorkInfo;

public class MainActivity extends AppCompatActivity {

    private TestViewModel mViewModel;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mViewModel = ViewModelProviders.of(this).get(TestViewModel.class);

        textView = findViewById(R.id.textView);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random generator = new Random();
                String randomString = String.valueOf (generator.nextInt(96) + 32);
                mViewModel.doJob(randomString);
            }
        });

        mViewModel.getOutputStatus().observe(this, new Observer<List<WorkInfo>>() {
            @Override
            public void onChanged(@Nullable List<WorkInfo> listOfWorkStatuses) {

                // If there are no matching work statuses, do nothing
                if (listOfWorkStatuses == null || listOfWorkStatuses.isEmpty()) {
                    return;
                }

                WorkInfo workStatus = listOfWorkStatuses.get(0);

                boolean finished = workStatus.getState().isFinished();
                if (finished) {
                    Data outputData = workStatus.getOutputData();

                    String sampleData = outputData.getString("MyOutput");
                    textView.setText(sampleData);
                    LogUtils.e(sampleData);
                }

            }
        });
    }
}
