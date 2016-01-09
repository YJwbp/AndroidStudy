package com.bpwei.examples;

import android.app.Application;
import android.widget.Toast;

import org.androidannotations.annotations.EApplication;

/**
 * Created by new on 16/1/8.
 */
@EApplication
public class MainApp extends Application {

	public void toast(String tips) {
		Toast.makeText(this.getApplicationContext(), tips, Toast.LENGTH_SHORT).show();
	}

	public void toast(int resId) {
		Toast.makeText(this.getApplicationContext(), resId, Toast.LENGTH_SHORT).show();
	}
}
