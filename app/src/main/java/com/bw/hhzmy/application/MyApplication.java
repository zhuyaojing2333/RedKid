package com.bw.hhzmy.application;

import android.app.Application;
import android.util.Log;

import com.bw.hhzmy.tools.ImageLoaderUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by PigGhost on 2016/11/14.
 */
public class MyApplication extends Application {
    private static final String TAG = "JPush";

    {

        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
        PlatformConfig.setQQZone("1105866488", "07iyZhc8vvFYEyyl");


    }
    @Override
    public void onCreate() {
        super.onCreate();

        SpeechUtility.createUtility(getApplicationContext(), SpeechConstant.APPID+"=584a9744");

        Log.d(TAG, "[MyApplication] onCreate");
        initImageLoader();
        UMShareAPI.get(this);

        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
    }

    private void initImageLoader() {
        ImageLoaderUtils.initConfiguration(getApplicationContext());
    }

}

