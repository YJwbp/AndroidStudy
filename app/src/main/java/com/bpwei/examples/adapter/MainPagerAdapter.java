package com.bpwei.examples.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by wbp on 2017/1/15.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> fragmentArrayList;

    public MainPagerAdapter(FragmentManager fm, ArrayList<Fragment> baseFragments){
        super(fm);
        fragmentArrayList = baseFragments;
    }


    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

}
