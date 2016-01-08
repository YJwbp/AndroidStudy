package com.bpwei.examples.activity;

import android.content.Intent;
import android.widget.Button;

import com.bpwei.examples.R;

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
