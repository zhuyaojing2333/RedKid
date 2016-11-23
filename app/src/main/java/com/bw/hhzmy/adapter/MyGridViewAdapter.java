package com.bw.hhzmy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.hhzmy.activity.R;
import com.bw.hhzmy.bean.Bean;
import com.bw.hhzmy.tools.ImageLoaderUtils;
import com.bw.hhzmy.tools.Tools;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by PigGhost on 2016/11/14.
 */
public class MyGridViewAdapter extends BaseAdapter{
    private Context context;
    private List<Bean.DataBean.TagBean> mlist;
    private ImageLoader imageLoader;

    public MyGridViewAdapter(Context context, List<Bean.DataBean.TagBean> mlist) {
        this.context = context;
        this.mlist = mlist;
        imageLoader = ImageLoader.getInstance();
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;

        if(view == null){
            viewHolder = new ViewHolder();
            view = View.inflate(context, R.layout.gridview_item, null);

            viewHolder.gridView_icon = (ImageView) view.findViewById(R.id.gridView_icon);
            viewHolder.gridView_txt  = (TextView) view.findViewById(R.id.gridView_txt);

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        imageLoader.displayImage(Tools.URL+mlist.get(i).getPicUrl(), viewHolder.gridView_icon, ImageLoaderUtils.initOptions());
        viewHolder.gridView_txt.setText(mlist.get(i).getElementName());

        return view;
    }

    class ViewHolder{
        ImageView gridView_icon;
        TextView gridView_txt;
    }
}
