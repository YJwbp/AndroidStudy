package com.bpwei.examples.activity;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.DebugUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bpwei.examples.R;
import com.bpwei.examples.adapter.CustomPagerAdapter;
import com.bpwei.examples.adapter.MainPagerAdapter;
import com.bpwei.examples.adapter.RecyclerViewAdapter;
import com.bpwei.examples.bean.Character;
import com.bpwei.examples.fragment.BaseFragment;
import com.bpwei.examples.fragment.FirstFragment;
import com.bpwei.examples.fragment.SecondFragment;
import com.bpwei.examples.utils.Utils;
import com.bpwei.examples.views.CustomViewPager;
import com.bpwei.examples.views.TextIndicator;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wbp on 2017/1/15.
 */

@EActivity(R.layout.activity_butter_knife)
public class ButterKnifeActivity extends FragmentActivity {
    @ViewById(R.id.pager)
    ViewPager pager;


    @AfterViews
    void afterViews(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new FirstFragment());
        fragments.add(new SecondFragment());

        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), fragments);

        pager.setAdapter(mainPagerAdapter);
        pager.setCurrentItem(0);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Utils.debug("Pager >> onPageScrolled "+positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                Utils.debug("Pager >> onPageSelected "+position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Utils.debug("Pager >> onPageScrollStateChanged "+state);

            }
        });
    }
}
