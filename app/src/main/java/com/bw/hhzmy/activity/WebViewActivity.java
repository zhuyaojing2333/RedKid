package com.bw.hhzmy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = (WebView) findViewById(R.id.webView);

        WebSettings webSettings=webView.getSettings();
        //设置支持javascript
        webSettings.setJavaScriptEnabled(true);

        //启用WebView访问文件数据
        webSettings.setAllowFileAccess(true);

        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);

        //设置用鼠标激活被选项
        webSettings.setLightTouchEnabled(true);

        Intent intent = getIntent();

        String url = intent.getStringExtra("网络地址");

        webView.loadUrl(url);

        webView.canGoBack();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (webView.canGoBack() && event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
