package com.bw.hhzmy.application;

import android.app.Application;

import com.bw.hhzmy.tools.ImageLoaderUtils;

/**
 * Created by PigGhost on 2016/11/14.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        initImageLoader();
    }

    private void initImageLoader() {
        ImageLoaderUtils.initConfiguration(getApplicationContext());
    }

}

