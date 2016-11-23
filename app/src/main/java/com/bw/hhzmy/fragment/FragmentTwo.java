package com.bw.hhzmy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.hhzmy.activity.R;
import com.bw.hhzmy.activity.SearchActivity;
import com.bw.hhzmy.adapter.MyRecyclerViewLeftAdapter;
import com.bw.hhzmy.adapter.MyRecyclerViewRightAdapter;
import com.bw.hhzmy.bean.Type;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PigGhost on 2016/11/8.
 */
public class FragmentTwo extends Fragment{
    private RecyclerView recycle_left;
    private RecyclerView recycle_right;
    private List<Type.RsBean> mlist = new ArrayList();
    private List<Type.RsBean.ChildrenBean> childrenList = new ArrayList<>();
    private MyRecyclerViewLeftAdapter adapter;
    private MyRecyclerViewRightAdapter contextAdapter;
    int index=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmenttwo, container, false);

        recycle_left = (RecyclerView) view.findViewById(R.id.recycle_left);
        recycle_right = (RecyclerView) view.findViewById(R.id.recycle_right);
        view.findViewById(R.id.search_jumpactivity2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SearchActivity.class);

                startActivity(intent);
            }
        });

        initData();
        setleftrecycle();
        setRightRecycle();

        return view;
    }

    /**
     * 右边内容设置
     */
    private void setRightRecycle() {
        GridLayoutManager manager=new GridLayoutManager(getActivity(),3);
        //设置布局管理器
        recycle_right.setLayoutManager(manager);
        //设置垂直
        manager.setOrientation(OrientationHelper.VERTICAL);
        //设置只有字体占行数
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                //判断是否为标题 ，是 返回3   否  返回1
                return childrenList.get(position).isHeader?3:1;
            }
        });
        //默认将第一个数据进行遍历展示
        List<Type.RsBean.ChildrenBean> childrenBeanList = mlist.get(0).children;
        for (int i = 0; i < childrenBeanList.size(); i++) {
            childrenBeanList.get(i).isHeader = true;
            childrenList.add(childrenBeanList.get(i));
            childrenList.addAll(childrenBeanList.get(i).children);
        }

        //设置适配器
        contextAdapter = new MyRecyclerViewRightAdapter(getActivity(),childrenList);
        recycle_right.setAdapter(contextAdapter);
    }

    private void setleftrecycle() {
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        //设置布局管理器
        recycle_left.setLayoutManager(manager);
        //设置垂直
        manager.setOrientation(OrientationHelper.VERTICAL);

        //设置适配器
        adapter = new MyRecyclerViewLeftAdapter(mlist, getActivity());
        //设置分割线
        recycle_left.addItemDecoration(new DividerItemDecoration(getActivity(), com.bw.hhzmy.adapter.DividerItemDecoration.VERTICAL_LIST));
        recycle_left.setAdapter(adapter);
        //设置点击监听
        adapter.setOnItemClickLitener(new MyRecyclerViewLeftAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int positon) {
                mlist.get(index).ischeck=false;
                mlist.get(positon).ischeck=true;
                index=positon;
                //点击之后进行修改数据
                update(positon);
            }

            @Override
            public void onItemLongClick(View view, int position) {


            }
        });
    }

    /**
     * 请求数据
     */
    private void initData() {
        try {
            //读取本地文件
            InputStream inputStream = getResources().getAssets().open("category.txt");

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String str = null;
            while((str = br.readLine())!=null){
                stringBuffer.append(str);
            }
            str=stringBuffer.toString();
            Gson gson=new Gson();
            Type myBean = gson.fromJson(str, Type.class);

            mlist.addAll(myBean.rs);
            //默认第一个选中
            mlist.get(0).ischeck=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改点击后进行展示的数据
     * @param positon
     */
    private void update(int positon) {
        adapter.notifyDataSetChanged();//进行数据刷新
        childrenList.clear();//数据进行清空，放更新的数据
        //第一层children
        List<Type.RsBean.ChildrenBean> childrenBeen=mlist.get(positon).children;
        for (int i = 0; i < childrenBeen.size(); i++) {
            childrenBeen.get(i).isHeader = true;
            childrenList.add(childrenBeen.get(i));
            childrenList.addAll(childrenBeen.get(i).children);
        }
        contextAdapter.notifyDataSetChanged();//刷新内容展示
    }
}
