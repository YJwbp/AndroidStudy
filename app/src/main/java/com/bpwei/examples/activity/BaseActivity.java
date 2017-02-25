package com.bpwei.examples.activity;

import android.app.Activity;
import android.os.Bundle;

import com.bpwei.examples.utils.Utils;

/**
 * Created by bpwei on 16/1/7.
 */
public class BaseActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Utils.debug("onCreate --------> " + this.getClass().getSimpleName());
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onStart() {
		Utils.debug("onStart --------> "+this.getClass().getSimpleName());
		super.onStart();
	}

	@Override
	protected void onResume() {
		Utils.debug("onResume --------> "+this.getClass().getSimpleName());
		super.onResume();
	}

	@Override
	protected void onPause() {
		Utils.debug("onPause --------> "+this.getClass().getSimpleName());
		super.onPause();
	}

	@Override
	protected void onStop() {
		Utils.debug("onStop --------> "+this.getClass().getSimpleName());
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		Utils.debug("onDestroy --------> "+this.getClass().getSimpleName());
		super.onDestroy();
	}
}
