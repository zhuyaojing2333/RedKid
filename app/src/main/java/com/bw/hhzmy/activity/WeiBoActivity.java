package com.bw.hhzmy.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class WeiBoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_bo);

        UMShareAPI  mShareAPI = UMShareAPI.get( WeiBoActivity.this );
        mShareAPI.doOauthVerify(WeiBoActivity.this, SHARE_MEDIA.SINA, umAuthListener);
        mShareAPI.getPlatformInfo(WeiBoActivity.this, SHARE_MEDIA.SINA, umAuthListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(getApplicationContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText( getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText( getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };
}
