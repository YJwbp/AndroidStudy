package com.bpwei.examples.activity;


import com.bpwei.examples.MainApp;
import com.bpwei.examples.R;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

/**
 * Created by new on 16/1/8.
 */
@EActivity(R.layout.activity_face_detected)
public class FaceDetectActivity extends BaseActivity {
// TODO 添加opencv的人脸识别

	@App
	MainApp app;

	@Click(R.id.title_back)
	void backClick() {
		onBackPressed();
		app.toast(R.string.back);
	}

}
