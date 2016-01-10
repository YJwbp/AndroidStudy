package com.bpwei.examples.activity;

import android.graphics.Point;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bpwei.examples.R;
import com.bpwei.examples.sp.ScreenSP_;
import com.bpwei.examples.utils.Utils;
import com.bpwei.examples.views.PolarCoordinateView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

/**
 * Created by new on 16/1/10.
 */
@EActivity(R.layout.layout_polar_coordinate)
public class PolarCoordinateActivity extends BaseActivity {

	@ViewById(R.id.v_polar_coordinate)
	PolarCoordinateView polarCoordinateView;

	@Pref
	ScreenSP_ screenSP;

	@AfterViews
	void afterViews(){
		RelativeLayout.LayoutParams rlParams = (RelativeLayout.LayoutParams)polarCoordinateView.getLayoutParams();
		rlParams.height = screenSP.width().get();

		polarCoordinateView.setLayoutParams(rlParams);
	}
}
