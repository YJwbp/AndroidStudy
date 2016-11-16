package com.bpwei.examples.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bpwei on 2016/11/16.
 */

public class TextIndicator extends LinearLayout implements ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    private List<TextView> textViewList;
    private PagerAdapter pagerAdapter;
    private boolean showDivider;

    // 属性
    private int textColorUnSelected = 0X888888;
    private int textColorSelected = 0XAA0000;
    private int textSize = 12;
    private int itemWidth = 100;
    private Paint linePaint;

    public TextIndicator(Context context) {
        super(context);
        init();
    }

    public TextIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        textViewList = new ArrayList<>();
        linePaint = new Paint();
        linePaint.setStyle(Paint.Style.FILL);
        linePaint.setColor(Color.RED);
    }


    public void setViewPager(final ViewPager viewPager) {
        this.viewPager = viewPager;
        this.pagerAdapter = viewPager.getAdapter();

        int pagerCount = pagerAdapter.getCount();
        for (int i = 0; i < pagerCount; i++) {
            TextView textView = new TextView(viewPager.getContext());
            textView.setText(pagerAdapter.getPageTitle(i));
            LinearLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.weight = 1;
            params.width = 0;
            textView.setLayoutParams(params);

            final int finalI = i;
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(finalI);
                }
            });

            addView(textView);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.save();
        canvas.drawRect(0, 0, 100, 5f, linePaint);
        canvas.restore();
    }
}
