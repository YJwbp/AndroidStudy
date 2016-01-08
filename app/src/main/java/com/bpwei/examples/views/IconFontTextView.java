package com.bpwei.examples.views;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EBean;

/**
 * Created by new on 16/1/7.
 */
@EBean
public class IconFontTextView extends TextView {
	public IconFontTextView(Context context) {
		super(context);
	}

	@AfterViews
	void afterViews(){
		Typeface iconTypeface = Typeface.createFromAsset(getContext().getAssets(),"IconFont.ttf");
		setTypeface(iconTypeface);
	}
}
