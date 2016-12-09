package com.bw.hhzmy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.hhzmy.activity.R;
import com.bw.hhzmy.tools.ImageLoaderUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by PigGhost on 2016/11/8.
 */
public class FragmentFour extends Fragment{
    private ImageView icon;
    private TextView txt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentfour, container, false);

        icon = (ImageView) view.findViewById(R.id.myhead_icon);
        txt = (TextView) view.findViewById(R.id.myname_txt);

        Intent intent = getActivity().getIntent();

        String qqname = intent.getStringExtra("qq_name");
        String imageUrl = intent.getStringExtra("img_url");

        if(qqname != null){
            txt.setText(qqname);
        }
        if(imageUrl != null){
            ImageLoader.getInstance().displayImage(imageUrl, icon, ImageLoaderUtils.initOptions());
        }

        return view;
    }
}
