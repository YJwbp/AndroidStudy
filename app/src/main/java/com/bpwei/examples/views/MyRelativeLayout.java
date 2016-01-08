package com.bpwei.examples.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by new on 15/9/10.
 */
public class MyRelativeLayout extends RelativeLayout {
	public MyRelativeLayout(Context context) {
		super(context);
	}

	public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public MyRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		Log.d("MyRelativeLayout: ", "onInterceptTouchEvent");

		return true;//super.onInterceptTouchEvent(ev);
	}
}
