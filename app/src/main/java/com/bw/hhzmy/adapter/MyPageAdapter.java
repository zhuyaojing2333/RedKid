package com.bw.hhzmy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bw.hhzmy.activity.R;
import com.bw.hhzmy.activity.WebViewActivity;
import com.bw.hhzmy.bean.Bean;
import com.bw.hhzmy.tools.ImageLoaderUtils;
import com.bw.hhzmy.tools.Tools;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by PigGhost on 2016/11/14.
 */
public class MyPageAdapter extends PagerAdapter{
    private ImageLoader imageLoader;
    private Context context;

    private List<Bean.DataBean.TagBean> mlist;

    public MyPageAdapter(Context context, List<Bean.DataBean.TagBean> mlist) {
        this.context = context;
        this.mlist = mlist;

        imageLoader =  ImageLoader.getInstance();
    }

    @Override
    public Object instantiateItem(final ViewGroup container,int position) {
        position = position % mlist.size();

        View view = View.inflate(context, R.layout.circle_item, null);

        ImageView banana_img = (ImageView) view.findViewById(R.id.banana_img);

        imageLoader.displayImage(Tools.URL+mlist.get(position).getPicUrl(), banana_img, ImageLoaderUtils.initOptions());

        container.addView(view);

        final int finalPosition = position;

        banana_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebViewActivity.class);

                intent.putExtra("网络地址", mlist.get(finalPosition).getLinkUrl());

                context.startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        //最大的整数值
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
