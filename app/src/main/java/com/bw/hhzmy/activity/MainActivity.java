package com.bw.hhzmy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.umeng.analytics.MobclickAgent;

public class MainActivity extends AppCompatActivity{
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(MainActivity.this, MenuActivity.class);

        MobclickAgent.startWithConfigure(new MobclickAgent.UMAnalyticsConfig(MainActivity.this, "584aa506c89576147600036a", "PigGhost"));

        handler.sendEmptyMessageDelayed(0,3000);

    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            startActivity(intent);

            finish();
        }
    };

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

}
