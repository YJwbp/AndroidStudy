package com.example.wbp.practice.views;

import android.content.Context;
import android.widget.RelativeLayout;

import com.example.wbp.practice.R;

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
