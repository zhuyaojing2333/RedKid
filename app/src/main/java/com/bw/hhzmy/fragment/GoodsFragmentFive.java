package com.bw.hhzmy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.bw.hhzmy.activity.R;

/**
 * Created by PigGhost on 2016/11/17.
 */
public class GoodsFragmentFive extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.goodsfragmentfive, container, false);
        WebView wv = (WebView) view.findViewById(R.id.webView3);
        WebSettings webSettings2 = wv.getSettings();
        //设置支持javascript
        webSettings2.setJavaScriptEnabled(true);

        //启用WebView访问文件数据
        webSettings2.setAllowFileAccess(true);

        //设置支持缩放
        webSettings2.setBuiltInZoomControls(true);

        //设置用鼠标激活被选项
        webSettings2.setLightTouchEnabled(true);

        wv.loadUrl("http://product.suning.com/pds-web/product/graphicDetailsApp/0000000000/102295661/10051/R9000413/1.html");

        return view;
    }
}
