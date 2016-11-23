package com.bw.hhzmy.fragment;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bw.hhzmy.activity.GoodsActivity;
import com.bw.hhzmy.activity.LoginActivity;
import com.bw.hhzmy.activity.R;
import com.bw.hhzmy.activity.SearchActivity;
import com.bw.hhzmy.activity.TestScanActivity;
import com.bw.hhzmy.activity.WebViewActivity;
import com.bw.hhzmy.adapter.MyGalleryAdapter;
import com.bw.hhzmy.adapter.MyGridViewAdapter;
import com.bw.hhzmy.adapter.MyPageAdapter;
import com.bw.hhzmy.adapter.SecondGridViewAdapter;
import com.bw.hhzmy.bean.Bean;
import com.bw.hhzmy.http.OkHttp;
import com.bw.hhzmy.listener.RecyclerViewClickListener;
import com.bw.hhzmy.tools.ImageLoaderUtils;
import com.bw.hhzmy.tools.Tools;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by PigGhost on 2016/11/8.
 */
public class FragmentOne extends Fragment implements EasyPermissions.PermissionCallbacks{
    private static final String TAG = FragmentOne.class.getSimpleName();

    private ImageView checkmore_img;
    private ImageView yunzhibei_img;
    private ImageView kangbei_img;
    private ImageView xilang_img;
    private ImageView lingyaobaby_img;
    private ImageView queshi_img;
    private ImageView spellgroup_img;
    private RecyclerView my_gallery2;
    private RecyclerView my_gallery3;
    private RecyclerView my_gallery4;
    private RecyclerView my_gallery5;
    private ImageView shengmoney_buy;
    private ImageView liangbanhui_img;
    private ImageView bigjuhui_img;
    private ImageView outseabuy_img;
    private ImageView theme_buy_img;
    private ImageView newstart_img;
    private ImageView baby_img;
    private ImageView child_img;
    private ImageView before_study_img;
    private ImageView before_pregnant_img;
    private ImageView pregnant_next_img;
    private ImageView mom_baby_img;
    private GridView menu_mGridView2;
    private ImageView brand_img;
    private static final int REQUEST_CODE_QRCODE_PERMISSIONS = 1;
    private RecyclerView my_gallery1;
    private ImageLoader imageLoader;
    private List<Bean.DataBean.TagBean> mglylist;
    private ImageView gallery_title_icon;
    private MyGridViewAdapter mgvAdapter;
    private List<Bean.DataBean.TagBean> mgvlist = new ArrayList<>();
    private GridView menu_mGridView;
    private MyPageAdapter mpAdapter;
    private LinearLayout small_circle;
    private List<Bean.DataBean.TagBean> mvplist = new ArrayList<>();
    private ViewPager menu_mViewPager;
    private LinearLayout search_jumpactivity;
    private ImageView sys;
    private String url = "http://mock.eoapi.cn/success/jSWAxchCQfuhI6SDlIgBKYbawjM3QIga";
    private ImageView message_icon;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentone, container, false);

        imageLoader = ImageLoader.getInstance();

        search_jumpactivity = (LinearLayout) view.findViewById(R.id.search_jumpactivity);
        sys = (ImageView) view.findViewById(R.id.saoyisao);
        menu_mViewPager = (ViewPager) view.findViewById(R.id.menu_mViewPager);
        small_circle = (LinearLayout) view.findViewById(R.id.small_circle);
        menu_mGridView = (GridView) view.findViewById(R.id.menu_mGridView);
        gallery_title_icon = (ImageView) view.findViewById(R.id.gallery_title_icon);
        my_gallery1 = (RecyclerView) view.findViewById(R.id.my_gallery1);
        brand_img = (ImageView) view.findViewById(R.id.brand_img);
        menu_mGridView2 = (GridView) view.findViewById(R.id.menu_mGridView2);
        mom_baby_img = (ImageView) view.findViewById(R.id.mom_baby_img);
        before_pregnant_img = (ImageView) view.findViewById(R.id.before_pregnant_img);
        pregnant_next_img = (ImageView) view.findViewById(R.id.pregnant_next_img);
        newstart_img = (ImageView) view.findViewById(R.id.newstart_img);
        baby_img = (ImageView) view.findViewById(R.id.baby_img);
        child_img = (ImageView) view.findViewById(R.id.child_img);
        before_study_img = (ImageView) view.findViewById(R.id.before_study_img);
        theme_buy_img = (ImageView) view.findViewById(R.id.theme_buy_img);
        outseabuy_img = (ImageView) view.findViewById(R.id.outseabuy_img);
        my_gallery2 = (RecyclerView) view.findViewById(R.id.my_gallery2);
        bigjuhui_img = (ImageView) view.findViewById(R.id.bigjuhui_img);
        my_gallery3 = (RecyclerView) view.findViewById(R.id.my_gallery3);
        liangbanhui_img = (ImageView) view.findViewById(R.id.liangbanhui_img);
        my_gallery4 = (RecyclerView) view.findViewById(R.id.my_gallery4);
        shengmoney_buy = (ImageView) view.findViewById(R.id.shengmoney_buy);
        my_gallery5 = (RecyclerView) view.findViewById(R.id.my_gallery5);
        spellgroup_img = (ImageView) view.findViewById(R.id.spellgroup_img);
        yunzhibei_img = (ImageView) view.findViewById(R.id.yunzhibei_img);
        kangbei_img = (ImageView) view.findViewById(R.id.kangbei_img);
        xilang_img = (ImageView) view.findViewById(R.id.xilang_img);
        lingyaobaby_img = (ImageView) view.findViewById(R.id.lingyaobaby_img);
        queshi_img = (ImageView) view.findViewById(R.id.queshi_img);
        checkmore_img = (ImageView) view.findViewById(R.id.checkmore_img);
        message_icon = (ImageView) view.findViewById(R.id.message_icon);

        //跳转到登录界面
        initLogin();

        //跳转到搜索界面
        initSearch();

        //扫一扫
        initSao();

        //请求网络数据
        initHttp();

        myHandler.sendEmptyMessage(1);

        return view;
    }

    /**
     * 跳转到登录界面
     */
    private void initLogin() {
        message_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginActivity.class);

                startActivity(intent);
            }
        });
    }

    /**
     * 请求网络数据
     */
    private void initHttp() {

        OkHttp.getAsync(url, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Gson gson = new Gson();

                Bean b = gson.fromJson(result, Bean.class);

                //加载无限轮播布局
                mvplist = b.getData().get(0).getTag();

                mpAdapter = new MyPageAdapter(getContext(), mvplist);

                menu_mViewPager.setAdapter(mpAdapter);

                menu_mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        //将arg0换算到viewList的索引范围之内
                        int a = position % mvplist.size();
                        //遍历所有的点对应的ImageView ，判断点的索引是否跟ViewPager当前的索引一致
                        for (int i = 0; i < small_circle.getChildCount(); i++) {

                            ImageView imageView = (ImageView) small_circle.getChildAt(i);
                            if (i == a) {
                                imageView.setSelected(true);
                            } else {
                                imageView.setSelected(false);
                            }
                        }

                    }

                    @Override
                    public void onPageScrollStateChanged(final int state) {

                    }
                });
                //添加下面的圆点
                for (int i = 0; i < mvplist.size(); i++) {
                    //动态实例化ImageView对象，添加到LinearLayout里面
                    ImageView imageView = new ImageView(getActivity());
                    //手动代码设置间距
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.setMargins(5, 0, 5, 0);
                    //将当前属性设置给ImageView
                    imageView.setLayoutParams(params);
                    //给ImageView设置显示资源
                    imageView.setBackgroundResource(R.drawable.circle_item_selector);
                    //将ImageView添加到LinearLayout里面
                    small_circle.addView(imageView);
                    //设置默认选中第一个
                    if (i == 0) {
                        imageView.setSelected(true);
                    }
                }

                //加载首页GridView布局
                mgvlist = b.getData().get(1).getTag();

                mgvAdapter = new MyGridViewAdapter(getContext(), mgvlist);

                menu_mGridView.setAdapter(mgvAdapter);

                menu_mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        jumpWv(mgvlist.get(i).getLinkUrl());
                    }
                });

                //加载首页gallery效果布局
                mglylist = b.getData().get(2).getTag();

                imageLoader.displayImage(Tools.URL+mglylist.get(0).getPicUrl(), gallery_title_icon, ImageLoaderUtils.initOptions());

                my_gallery1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));

                MyGalleryAdapter adapter = new MyGalleryAdapter(mglylist,getContext());

                my_gallery1.setAdapter(adapter);

                //傲娇品牌衣食住行布局
                List<Bean.DataBean.TagBean> mblist = b.getData().get(4).getTag();
                imageLoader.displayImage(Tools.URL + mblist.get(0).getPicUrl(), brand_img, ImageLoaderUtils.initOptions());

                final List<Bean.DataBean.TagBean> mcelupslist = new ArrayList<>();

                mcelupslist.addAll(b.getData().get(5).getTag());
                mcelupslist.addAll(b.getData().get(6).getTag());
                mcelupslist.addAll(b.getData().get(7).getTag());

                menu_mGridView2.setAdapter(new SecondGridViewAdapter(getContext(), mcelupslist));

                menu_mGridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        jumpWv(mcelupslist.get(i).getLinkUrl());
                    }
                });

                //加载母婴专区布局
                List<Bean.DataBean.TagBean> mmylist = b.getData().get(9).getTag();
                imageLoader.displayImage(Tools.URL + mmylist.get(0).getPicUrl(), mom_baby_img, ImageLoaderUtils.initOptions());

                final List<Bean.DataBean.TagBean> mqhplist = b.getData().get(10).getTag();
                imageLoader.displayImage(Tools.URL + mqhplist.get(0).getPicUrl(), before_pregnant_img, ImageLoaderUtils.initOptions());
                imageLoader.displayImage(Tools.URL + mqhplist.get(1).getPicUrl(), pregnant_next_img, ImageLoaderUtils.initOptions());

                before_pregnant_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        jumpWv(mcelupslist.get(0).getLinkUrl());
                    }
                });
                pregnant_next_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        jumpWv(mcelupslist.get(1).getLinkUrl());
                    }
                });
                final List<Bean.DataBean.TagBean> mnbcplist = b.getData().get(11).getTag();
                imageLoader.displayImage(Tools.URL + mnbcplist.get(0).getPicUrl(), newstart_img, ImageLoaderUtils.initOptions());
                imageLoader.displayImage(Tools.URL + mnbcplist.get(1).getPicUrl(), baby_img, ImageLoaderUtils.initOptions());
                imageLoader.displayImage(Tools.URL + mnbcplist.get(2).getPicUrl(), child_img, ImageLoaderUtils.initOptions());
                imageLoader.displayImage(Tools.URL + mnbcplist.get(3).getPicUrl(), before_study_img, ImageLoaderUtils.initOptions());
                newstart_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        jumpWv(mnbcplist.get(0).getLinkUrl());
                    }
                });
                baby_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        jumpWv(mnbcplist.get(1).getLinkUrl());
                    }
                });
                child_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        jumpWv(mnbcplist.get(2).getLinkUrl());
                    }
                });
                before_study_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        jumpWv(mnbcplist.get(3).getLinkUrl());
                    }
                });

                //主题特卖专区
                List<Bean.DataBean.TagBean> mtblist = b.getData().get(13).getTag();
                imageLoader.displayImage(Tools.URL + mtblist.get(0).getPicUrl(), theme_buy_img, ImageLoaderUtils.initOptions());
                //海外购专区
                final List<Bean.DataBean.TagBean> mosblist = b.getData().get(14).getTag();
                imageLoader.displayImage(Tools.URL + mosblist.get(0).getPicUrl(), outseabuy_img, ImageLoaderUtils.initOptions());
                outseabuy_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        jumpWv(mosblist.get(0).getLinkUrl());
                    }
                });

                List<Bean.DataBean.TagBean> mglrlist1 = b.getData().get(15).getTag();
                my_gallery2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
                MyGalleryAdapter adapter2 = new MyGalleryAdapter(mglrlist1,getContext());
                my_gallery2.setAdapter(adapter2);

                //大聚惠专区
                final List<Bean.DataBean.TagBean> mbjhlist = b.getData().get(16).getTag();
                imageLoader.displayImage(Tools.URL + mbjhlist.get(0).getPicUrl(), bigjuhui_img, ImageLoaderUtils.initOptions());
                bigjuhui_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        jumpWv(mbjhlist.get(0).getLinkUrl());
                    }
                });

                List<Bean.DataBean.TagBean> mglrlist2 = b.getData().get(17).getTag();
                my_gallery3.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
                MyGalleryAdapter adapter3 = new MyGalleryAdapter(mglrlist2,getContext());
                my_gallery3.setAdapter(adapter3);

                //量版惠专区
                final List<Bean.DataBean.TagBean> mlbhlist = b.getData().get(18).getTag();
                imageLoader.displayImage(Tools.URL + mlbhlist.get(0).getPicUrl(), liangbanhui_img, ImageLoaderUtils.initOptions());
                liangbanhui_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        jumpWv(mlbhlist.get(0).getLinkUrl());
                    }
                });

                List<Bean.DataBean.TagBean> mglrlist3 = b.getData().get(19).getTag();
                my_gallery4.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
                MyGalleryAdapter adapter4 = new MyGalleryAdapter(mglrlist3,getContext());
                my_gallery4.setAdapter(adapter4);

                //省钱购专区
                final List<Bean.DataBean.TagBean> msmblist = b.getData().get(20).getTag();
                imageLoader.displayImage(Tools.URL + msmblist.get(0).getPicUrl(), shengmoney_buy, ImageLoaderUtils.initOptions());
                shengmoney_buy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        jumpWv(msmblist.get(0).getLinkUrl());
                    }
                });
                List<Bean.DataBean.TagBean> mglrlist4 = b.getData().get(21).getTag();
                my_gallery5.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
                MyGalleryAdapter adapter5 = new MyGalleryAdapter(mglrlist4,getContext());
                my_gallery5.setAdapter(adapter5);

                //辣妈拼团专区
                List<Bean.DataBean.TagBean> msglist = b.getData().get(23).getTag();
                imageLoader.displayImage(Tools.URL + msglist.get(0).getPicUrl(), spellgroup_img, ImageLoaderUtils.initOptions());

                final List<Bean.DataBean.TagBean> mykxlqlist = new ArrayList<>();
                mykxlqlist.addAll(b.getData().get(24).getTag());
                mykxlqlist.addAll(b.getData().get(26).getTag());
                mykxlqlist.addAll(b.getData().get(28).getTag());
                mykxlqlist.addAll(b.getData().get(30).getTag());
                mykxlqlist.addAll(b.getData().get(32).getTag());

                //运智贝
                final List<Bean.DataBean.TagBean> myzblist = b.getData().get(24).getTag();
                imageLoader.displayImage(Tools.URL + myzblist.get(0).getPicUrl(), yunzhibei_img, ImageLoaderUtils.initOptions());

                yunzhibei_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        jumpWv(myzblist.get(0).getLinkUrl());
                    }
                });

                //康贝
                final List<Bean.DataBean.TagBean> mkblist = b.getData().get(26).getTag();
                imageLoader.displayImage(Tools.URL + mkblist.get(0).getPicUrl(), kangbei_img, ImageLoaderUtils.initOptions());

                kangbei_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        jumpWv(mkblist.get(0).getLinkUrl());
                    }
                });

                //喜郎
                final List<Bean.DataBean.TagBean> mxllist = b.getData().get(28).getTag();
                imageLoader.displayImage(Tools.URL + mxllist.get(0).getPicUrl(), xilang_img, ImageLoaderUtils.initOptions());

                xilang_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        jumpWv(mxllist.get(0).getLinkUrl());
                    }
                });

                //玲瑶宝宝
                final List<Bean.DataBean.TagBean> mlybblist = b.getData().get(30).getTag();
                imageLoader.displayImage(Tools.URL + mlybblist.get(0).getPicUrl(), lingyaobaby_img, ImageLoaderUtils.initOptions());

                lingyaobaby_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        jumpWv(mlybblist.get(0).getLinkUrl());
                    }
                });

                //雀氏
                final List<Bean.DataBean.TagBean> mqslist = b.getData().get(32).getTag();
                imageLoader.displayImage(Tools.URL + mqslist.get(0).getPicUrl(), queshi_img, ImageLoaderUtils.initOptions());

                queshi_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        jumpWv(mqslist.get(0).getLinkUrl());
                    }
                });

                //查看更多
                final List<Bean.DataBean.TagBean> mcmlist = b.getData().get(33).getTag();
                imageLoader.displayImage(Tools.URL + mcmlist.get(0).getPicUrl(), checkmore_img, ImageLoaderUtils.initOptions());

                checkmore_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        jumpWv(mcmlist.get(0).getLinkUrl());
                    }
                });

                //RecyclerView点击事件
                my_gallery1.addOnItemTouchListener(new RecyclerViewClickListener(getContext(), new RecyclerViewClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getContext(), GoodsActivity.class);

                        startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                }));
            }
        });
    }

    /**
     * 跳转并传网络地址
     */
    public void jumpWv(String path){
        Intent intent = new Intent(getActivity(), WebViewActivity.class);

        intent.putExtra("网络地址", path);

        startActivity(intent);
    }

    /**
     * 扫一扫
     */
    private void initSao() {
        sys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 添加二维码扫描
                startActivity(new Intent(getActivity(),TestScanActivity.class));
            }
        });
    }

    /**
     * 跳转到搜索界面
     */
    private void initSearch() {
        search_jumpactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);

                startActivity(intent);
            }
        });
    }

    /**
     *
     */
    public Handler myHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    //获得ViewPager当前索引
                    int index = menu_mViewPager.getCurrentItem();
                    //当前页卡索引加1

                    //设置当前显示页卡
                    menu_mViewPager.setCurrentItem(index += 1);
                    //延迟发送消息，调用自己
                    myHandler.sendEmptyMessageDelayed(1, 2000);

                    break;
            }
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        requestCodeQrcodePermissions();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
    }

    @AfterPermissionGranted(REQUEST_CODE_QRCODE_PERMISSIONS)
    private void requestCodeQrcodePermissions() {
        String[] perms = {Manifest.permission.CAMERA};
        if (!EasyPermissions.hasPermissions(getActivity(), perms)) {
            EasyPermissions.requestPermissions(this, "扫描二维码需要打开相机和散光灯的权限", REQUEST_CODE_QRCODE_PERMISSIONS, perms);
        }
    }
}
