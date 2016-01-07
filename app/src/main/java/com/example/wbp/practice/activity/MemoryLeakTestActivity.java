package com.example.wbp.practice.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Button;

import com.example.wbp.practice.R;
import com.example.wbp.practice.views.ShapeSelectorView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Vector;

/**
 * Created by new on 15/8/12.
 */
@EActivity(R.layout.activity_memory_leak)
public class MemoryLeakTestActivity extends BaseActivity{
	@ViewById(R.id.btn_load)
	Button btn;

	Vector v;

	@Click(R.id.btn_load)
	void click(){
		v=new Vector(100);
		for (int i=1;i<100; i++)
		{
			Object o=new Intent();
			v.add(o);
			o=null;
		}
	}
}
