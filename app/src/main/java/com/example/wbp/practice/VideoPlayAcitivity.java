package com.example.wbp.practice;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by new on 15/8/9.
 */
@EActivity(R.layout.activity_video_play)
public class VideoPlayAcitivity extends Activity {
	@ViewById
	TextView textView;
	@ViewById
	Button button;
	@ViewById
	Button button2;
	@ViewById
	Button button3;
	@ViewById
	VideoView videoView;

	@AfterViews
	void playVideo() {

		videoView.setVideoPath("/sdcard/movies/xperia_hd_landscapes.mp4");
	}
	@Click(R.id.button)
	void btn1click() {
		Toast.makeText(this, "111111", Toast.LENGTH_SHORT).show();
		videoView.start();
	}
	@Click(R.id.button2)
	void btn2click() {
		Toast.makeText(this, "22222", Toast.LENGTH_SHORT).show();
		videoView.pause();
	}
	@Click(R.id.button3)
	void btn3click() {
		Toast.makeText(this, "333333", Toast.LENGTH_SHORT).show();
		videoView.stopPlayback();
	}

}