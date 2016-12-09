package com.bw.hhzmy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.hhzmy.activity.BaiduMapActivity;
import com.bw.hhzmy.activity.R;
import com.bw.hhzmy.adapter.MySecondPageAdapter;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PigGhost on 2016/11/16.
 */
public class GoodsFragmentOne extends Fragment {
    private ViewPager viewPager;
    private LinearLayout layout;
    private List<View> viewList;
    private LayoutInflater layoutInflater;
    private LinearLayout jump_baidumap;
    private TextView place_txt;
    private ImageView share_icon;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.goodsfragmentone, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        layout=(LinearLayout) view.findViewById(R.id.ll_circle);
        jump_baidumap = (LinearLayout) view.findViewById(R.id.jump_baidumap);
        place_txt = (TextView) view.findViewById(R.id.place_txt);
        share_icon = (ImageView) view.findViewById(R.id.share_icon);

        share_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ShareAction(getActivity()).setPlatform(SHARE_MEDIA.QQ).withText("猪妖精给大家问好了").setCallback(umShareListener).share();
            }
        });

        Intent intent = getActivity().getIntent();

        if(intent.hasExtra("地址")){
            Bundle bundle = intent.getExtras();

            String data = bundle.getString("地址");

            place_txt.setText(data);
        }else{
            place_txt.setText("送你一辆价值连城的车~！");
        }


        jump_baidumap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BaiduMapActivity.class);

                startActivity(intent);
            }
    });



        initView();

        return view;
    }

    private UMShareListener umShareListener = new UMShareListener() {


        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat","platform"+platform);

            Toast.makeText(getActivity(), platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getActivity(), platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if(t!=null){
                Log.d("throw","throw:"+t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(),platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };


    private void initView() {
        viewList=new ArrayList<>();
        layoutInflater=LayoutInflater.from(getActivity());
        View view1=layoutInflater.inflate(R.layout.item, null);
        view1.setBackgroundResource(R.mipmap.icon1);
        View view2=layoutInflater.inflate(R.layout.item, null);
        view2.setBackgroundResource(R.mipmap.icon2);
        View view3=layoutInflater.inflate(R.layout.item, null);
        view3.setBackgroundResource(R.mipmap.icon3);
        View  view4=layoutInflater.inflate(R.layout.item, null);
        view4.setBackgroundResource(R.mipmap.icon4);
        View  view5=layoutInflater.inflate(R.layout.item, null);
        view5.setBackgroundResource(R.mipmap.icon5);
        viewList.add(view5);
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);

        MySecondPageAdapter adapter = new MySecondPageAdapter(getContext(), viewList);
        viewPager.setAdapter(adapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < layout.getChildCount(); i++) {

                    ImageView imageView = (ImageView) layout.getChildAt(i);
                    if (i == position) {
                        imageView.setSelected(true);
                    } else {
                        imageView.setSelected(false);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //添加下面的圆点
        for(int i=0;i<viewList.size();i++){
            //动态实例化ImageView对象，添加到LinearLayout里面
            ImageView imageView=new ImageView(getActivity());
            //手动代码设置间距
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(5, 0, 5, 0);
            //将当前属性设置给ImageView
            imageView.setLayoutParams(params);
            //给ImageView设置显示资源
            imageView.setBackgroundResource(R.drawable.circle_item_selector);
            //将ImageView添加到LinearLayout里面
            layout.addView(imageView);
            //设置默认选中第一个
            if(i==0){
                imageView.setSelected(true);
            }
        }
    }
}
