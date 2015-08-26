package com.example.wbp.practice.activity;

import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

import com.example.wbp.practice.R;
import com.example.wbp.practice.views.BezierView;
import com.example.wbp.practice.views.TestView;
import com.example.wbp.practice.views.TestView_;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by new on 15/8/14.
 */
@EActivity(R.layout.activity_test)
public class TestActivity extends Activity {
@ViewById(R.id.check_real_time)
	CheckBox checkBox;
	@ViewById(R.id.rl_root)
	RelativeLayout relativeLayout;

	@Click(R.id.check_real_time)
	void click(){
		View view = TestView_.build(this);
		relativeLayout.addView(view);
	}
}
