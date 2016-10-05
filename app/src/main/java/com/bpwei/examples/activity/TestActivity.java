package com.bpwei.examples.activity;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bpwei.examples.R;
import com.bpwei.examples.utils.Utils;
import com.bpwei.examples.views.TestView_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by new on 15/8/14.
 */
@EActivity(R.layout.activity_test)
public class TestActivity extends BaseActivity {
@ViewById(R.id.check_real_time)
	CheckBox checkBox;
	@ViewById(R.id.rl_root)
	RelativeLayout relativeLayout;
//	@ViewById(R.id.ll_buttons)
//	LinearLayout llButtons;
	@ViewById(R.id.btn1)
	Button button1;
	@ViewById(R.id.btn2)
	Button button2;

	@AfterViews
	void afterViews(){
		List<String> test = new ArrayList();
		for (int i = 0; i < 5; i++) {
			test.add(String.valueOf(i));
		}
		for(String str:test){
			if(Integer.valueOf(str)==3){
				test.remove(str);
			}
		}
		for(String str:test){
			Utils.debug("Str: " + str);
		}
		final Context context = this;
//		llButtons.setOnTouchListener(new View.OnTouchListener() {
//			@Override
//			public boolean onTouch(View view, MotionEvent motionEvent) {
//				if(motionEvent.getPointerCount() > 1) {
//					System.out.println("Multitouch detected!");
//					return true;
//				}
//				else{
//					Toast.makeText(context,"Layout Touch",Toast.LENGTH_SHORT).show();
//					return false;
//				}
//			}
//		});
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(context,"Button 1 Click",Toast.LENGTH_SHORT).show();
			}
		});
//		button1.setOnTouchListener(new View.OnTouchListener() {
//			@Override
//			public boolean onTouch(View view, MotionEvent motionEvent) {
//				Toast.makeText(context,"Button 1 Touch",Toast.LENGTH_SHORT).show();
//
//				return true;
//			}
//		});
//		button2.setOnTouchListener(new View.OnTouchListener() {
//			@Override
//			public boolean onTouch(View view, MotionEvent motionEvent) {
//				Toast.makeText(context,"Button 2 Touch",Toast.LENGTH_SHORT).show();
//
//				return true;
//			}
//		});
	}
	@Click(R.id.check_real_time)
	void click(){
		View view = TestView_.build(this);
		relativeLayout.addView(view);
	}
}
