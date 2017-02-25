package com.bpwei.examples.views;

import android.content.Context;
import android.widget.RelativeLayout;


import com.bpwei.examples.R;

import org.androidannotations.annotations.EViewGroup;

/**
 * Created by new on 15/8/25.
 */
@EViewGroup(R.layout.view_test)
public class TestView extends RelativeLayout {
	public TestView(Context context) {
		super(context);
	}
}
