package com.bpwei.examples.activity;

import android.widget.RelativeLayout;

import com.bpwei.examples.MainApp;
import com.bpwei.examples.MainApp_;
import com.bpwei.examples.R;
import com.bpwei.examples.entites.PolarPoint;
import com.bpwei.examples.sp.ScreenSP_;
import com.bpwei.examples.views.PolarCoordinateView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.util.List;

/**
 * Created by new on 16/1/10.
 */
@EActivity(R.layout.layout_polar_coordinate)
public class PolarCoordinateActivity extends BaseActivity {

	@ViewById(R.id.v_polar_coordinate)
	PolarCoordinateView polarCoordinateView;

	@App
	MainApp app;

	@Pref
	ScreenSP_ screenSP;

	@AfterViews
	void afterViews(){
		RelativeLayout.LayoutParams rlParams = (RelativeLayout.LayoutParams)polarCoordinateView.getLayoutParams();
		rlParams.height = screenSP.width().get();

		polarCoordinateView.setLayoutParams(rlParams);
	}

	@Click(R.id.tv_clear)
	void clearClick(){
		polarCoordinateView.clearPolarMap();
		app.toast(R.string.clear);
	}

	@Click(R.id.tv_send)
	void sendClick(){
		List<PolarPoint> polarPoints = polarCoordinateView.getSelecetedPoints();
		app.toast(polarPoints.toString());
	}
}
