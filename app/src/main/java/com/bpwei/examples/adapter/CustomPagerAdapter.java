package com.bpwei.examples.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by bpwei on 2016/8/19.
 */
public class CustomPagerAdapter extends PagerAdapter {
    private ArrayList<View> views;

    public CustomPagerAdapter() {
        this.views = new ArrayList<>();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return position == 0 ? "第一" : "第二";
    }

    public void addAll(ArrayList<View> datas) {
        this.views.addAll(datas);
        this.notifyDataSetChanged();
    }

    public void add(View view) {
        this.views.add(view);
        this.notifyDataSetChanged();
    }

    public void removeAll() {
        this.views.clear();
    }

    @Override
    public int getCount() {
        return this.views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
