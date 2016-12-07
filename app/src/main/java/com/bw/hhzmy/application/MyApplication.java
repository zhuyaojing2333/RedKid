package com.bw.hhzmy.application;

import android.app.Application;

import com.bw.hhzmy.tools.ImageLoaderUtils;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by PigGhost on 2016/11/14.
 */
public class MyApplication extends Application {
    {

        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");


    }
    @Override
    public void onCreate() {
        super.onCreate();

        initImageLoader();
        UMShareAPI.get(this);
    }

    private void initImageLoader() {
        ImageLoaderUtils.initConfiguration(getApplicationContext());
    }

}

