package com.bpwei.examples.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bpwei.examples.R;
import com.bpwei.examples.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bpwei on 2016/11/16.
 */

public class TextIndicator extends LinearLayout implements ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    private List<TextView> textViewList;
    private PagerAdapter pagerAdapter;

    private int TEXT_COLOR_UNSELECTED = 0X88888888;
    private int TEXT_COLOR_SELECTED = 0XFFFF0000;
    private int UNDERLINE_COLOR = 0XFFFF0000;
    private int TEXT_SIZE = 12;
    private int UNDERLINE_HEIGHT = 3;
    private int UNDERLINE_WIDTH = 100;

    // 属性
    private int textColorUnSelected = TEXT_COLOR_UNSELECTED;
    private int textColorSelected = TEXT_COLOR_SELECTED;
    private int underlineColor = UNDERLINE_COLOR;
    private int textSize = TEXT_SIZE;
    private int underlineHeight = UNDERLINE_HEIGHT;
    private int underlineWidth = UNDERLINE_WIDTH;
    private boolean showDivider;

    private int underlineLeft = 0;
    private int itemWidth = 100;
    private Paint linePaint;

    public TextIndicator(Context context) {
        super(context);
        init(context);
    }

    public TextIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TextIndicator);
        textColorUnSelected = array.getColor(R.styleable.TextIndicator_text_color_unselected, TEXT_COLOR_UNSELECTED);
        textColorSelected = array.getColor(R.styleable.TextIndicator_text_color_selected, TEXT_COLOR_SELECTED);
        underlineColor = array.getColor(R.styleable.TextIndicator_underline_color, UNDERLINE_COLOR);
        textSize = array.getInt(R.styleable.TextIndicator_text_size, TEXT_SIZE);
        underlineHeight = array.getInt(R.styleable.TextIndicator_underline_height, UNDERLINE_HEIGHT);
        underlineWidth = array.getInt(R.styleable.TextIndicator_underline_width, UNDERLINE_WIDTH);
        showDivider = array.getBoolean(R.styleable.TextIndicator_show_divider, false);
        array.recycle();
        init(context);
    }

    private void init(Context context) {
        textViewList = new ArrayList<>();
        linePaint = new Paint();
        linePaint.setStyle(Paint.Style.FILL);
        linePaint.setColor(underlineColor);
    }

    public void setViewPager(final ViewPager viewPager) {
        this.viewPager = viewPager;
        this.pagerAdapter = viewPager.getAdapter();

        int pagerCount = pagerAdapter.getCount();
        for (int i = 0; i < pagerCount; i++) {
            TextView textView = new TextView(viewPager.getContext());
            textView.setText(pagerAdapter.getPageTitle(i));
            textView.setGravity(Gravity.CENTER);
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

            textViewList.add(textView);
            addView(textView);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Utils.debug("pos>> " + position + "posOffset>> " + positionOffset + "posOffPixel >> " + positionOffsetPixels);
        underlineLeft = positionOffsetPixels / 2;
        invalidate();
    }

    @Override
    public void onPageSelected(int position) {
        Utils.debug("Select page >> " + position);
        selectTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.save();
        canvas.translate(underlineLeft, 0);
        canvas.drawRect(underlineLeft, getBottom() - underlineHeight, underlineLeft + underlineWidth, getBottom(), linePaint);
        canvas.restore();
    }

    private void selectTab(int pos) {
        for (TextView textView : textViewList) {
            textView.setTextColor(TEXT_COLOR_UNSELECTED);
        }
        textViewList.get(pos).setTextColor(TEXT_COLOR_SELECTED);
    }
}
