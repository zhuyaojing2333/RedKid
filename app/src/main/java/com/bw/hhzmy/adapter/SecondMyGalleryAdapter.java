package com.bw.hhzmy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bw.hhzmy.activity.R;
import com.bw.hhzmy.bean.Bean;
import com.bw.hhzmy.tools.ImageLoaderUtils;
import com.bw.hhzmy.tools.Tools;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by PigGhost on 2016/11/15.
 */
public class SecondMyGalleryAdapter extends RecyclerView.Adapter<SecondMyGalleryAdapter.MyViewHolder>{
    private List<Bean.DataBean.TagBean> mlist;
    private Context context;
    private ImageLoader imageLoader;

    public SecondMyGalleryAdapter(List<Bean.DataBean.TagBean> mlist, Context context) {
        this.mlist = mlist;
        this.context = context;

        imageLoader = ImageLoader.getInstance();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.hrzt_item, parent, false));

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        imageLoader.displayImage(Tools.URL+mlist.get(position).getPicUrl(), holder.hrzt_image, ImageLoaderUtils.initOptions());
    }

    @Override
    public int getItemCount() {
        return mlist.size() - 1;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView hrzt_image;

        public MyViewHolder(View itemView) {
            super(itemView);

            hrzt_image = (ImageView) itemView.findViewById(R.id.hrzt_image);
        }
    }
}
