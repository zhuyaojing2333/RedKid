package com.bw.hhzmy.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bw.hhzmy.fragment.GoodsFragmentOne;
import com.bw.hhzmy.fragment.GoodsFragmentThree;
import com.bw.hhzmy.fragment.GoodsFragmentTwo;

/**
 * Created by PigGhost on 2016/11/16.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter{

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch(position){
            case 0:
                fragment = new GoodsFragmentOne();
                break;
            case 1:
                fragment = new GoodsFragmentTwo();
                break;
            case 2:
                fragment = new GoodsFragmentThree();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
