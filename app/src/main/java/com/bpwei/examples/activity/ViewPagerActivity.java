package com.bpwei.examples.activity;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bpwei.examples.R;
import com.bpwei.examples.adapter.CustomPagerAdapter;
import com.bpwei.examples.adapter.RecyclerViewAdapter;
import com.bpwei.examples.bean.Character;
import com.bpwei.examples.views.CustomViewPager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bpwei on 2016/8/19.
 */
@EActivity(R.layout.activity_viewpager)
public class ViewPagerActivity extends BaseActivity {
    @ViewById(R.id.pager)
    CustomViewPager pager;

    RecyclerView recyclerView;
    RecyclerView recyclerView2;

    RecyclerView.Adapter adapter;
    private List<Character> characters = new ArrayList<Character>();
    private String[] names = {"艾露莎&杰拉尔", "艾露莎", "四人组", "⊙﹏⊙b汗", "梅比斯", "纳兹",
            "艾露莎&纳兹"};
    private int[] pics = {R.drawable.couple, R.drawable.drown_deep,
            R.drawable.four, R.drawable.han, R.drawable.master,
            R.drawable.nazi, R.drawable.nazi_and_ailusa};

    @AfterViews
    void afterViews(){
        recyclerView = new RecyclerView(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        for (int i = 0; i < names.length; i++) {
            characters.add(new Character(names[i], pics[i]));
        }
        adapter = new RecyclerViewAdapter(this, characters);
        recyclerView.setAdapter(adapter);

        recyclerView2 = new RecyclerView(this);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setAdapter(adapter);

        CustomPagerAdapter pagerAdapter = new CustomPagerAdapter();
        pagerAdapter.add(recyclerView);
        pagerAdapter.add(recyclerView2);
        pager.setAdapter(pagerAdapter);

    }
}
