package com.murgupluoglu.workmanagersample;

import android.support.multidex.MultiDexApplication;

import com.blankj.utilcode.util.Utils;

/**
 * Created by mustafa.urgupluoglu on 6/7/18.
 */
public class App extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
