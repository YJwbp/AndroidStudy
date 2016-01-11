package com.bpwei.examples.activity;

import android.graphics.Point;
import android.view.View;
import android.widget.AdapterView;

import com.bpwei.examples.R;
import com.bpwei.examples.sp.ScreenSP_;
import com.bpwei.examples.utils.Utils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.sharedpreferences.Pref;

/**
 * Created by new on 16/1/11.
 */
@EActivity(R.layout.activity_splash)
public class SplashActivity extends BaseActivity {
	@Pref
	ScreenSP_ screenSP;

	@AfterViews
	void afterViews() {
		saveSaveScreenResolution();
		enter();
	}

	@UiThread(delay = 10)
	void enter() {
		PolarCoordinateActivity_.intent(this).start();
		finish();
		overridePendingTransition(0, 0);
	}

	private void saveSaveScreenResolution() {
		Point point = Utils.getScreenSize(this);
		screenSP.width().put(point.x);
		screenSP.height().put(point.y);
		screenSP.edit().width().put(point.x).height().put(point.y).apply();
	}

}
