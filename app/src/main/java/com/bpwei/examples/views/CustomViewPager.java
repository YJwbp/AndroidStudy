package com.bpwei.examples.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by bpwei on 2016/8/19.
 */
public class CustomViewPager extends ViewPager {
    private float LastX;
    private float LastY;
    private float distanceX;
    private float distanceY;
    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                distanceX = 0f;
                distanceY = 0f;
                LastX = ev.getX();
                LastY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:

                final float currentX = ev.getX();
                final float currentY = ev.getY();

                distanceX += Math.abs(currentX - LastX);
                distanceY += Math.abs(currentY - LastY);

                LastX = currentX;
                LastY = currentY;

                if(distanceX > distanceY){
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
