package com.example.wbp.practice.views;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wbp.practice.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by new on 15/8/9.
 */
@EViewGroup(R.layout.item_application)
public class LauncherAdapterItem extends RelativeLayout {
	@ViewById(R.id.tv_desc)
	TextView tvDesc;

	public LauncherAdapterItem(Context context) {
		super(context);
	}

	public void bind(String name){
		tvDesc.setText(name);
	}
}
