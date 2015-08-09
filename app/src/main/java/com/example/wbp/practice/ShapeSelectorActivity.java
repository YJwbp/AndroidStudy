package com.example.wbp.practice;

import android.app.Activity;
import android.view.View;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by new on 15/8/9.
 */
@EActivity(R.layout.shape_selector_activity)
public class ShapeSelectorActivity extends Activity {

	@ViewById(R.id.shape_selector)
	ShapeSelectorView shapeSelectorView;


}
