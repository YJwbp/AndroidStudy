package com.bpwei.examples.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EView;

/**
 * Created by new on 16/1/7.
 */
@EView
public class IconFontTextView extends TextView {
	public IconFontTextView(Context context) {
		super(context);
	}

	public IconFontTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@AfterInject
	void afterViews(){
		Typeface iconTypeface = Typeface.createFromAsset(getContext().getAssets(),"IconFont.ttf");
		setTypeface(iconTypeface);
	}
}
