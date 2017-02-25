package com.bpwei.examples.activity;

import com.bpwei.examples.R;
import com.bpwei.examples.views.ShapeSelectorView;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by new on 15/8/9.
 */
@EActivity(R.layout.shape_selector_activity)
public class ShapeSelectorActivity extends BaseActivity {

	@ViewById(R.id.shape_selector)
	ShapeSelectorView shapeSelectorView;


}
