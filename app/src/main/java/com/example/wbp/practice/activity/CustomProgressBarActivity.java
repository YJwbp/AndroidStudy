package com.example.wbp.practice.activity;

import android.app.Activity;
import android.os.Debug;
import android.os.Message;
import android.util.Log;
import android.widget.Button;

import com.example.wbp.practice.R;
import com.example.wbp.practice.views.FanProgressBar;
import com.example.wbp.practice.views.HorizontalProgressBarWithNumber;
import com.example.wbp.practice.views.RoundProgressBarWithNumber;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by new on 15/8/10.
 */
@EActivity(R.layout.activity_progress_bar_customed)
public class CustomProgressBarActivity extends Activity {
	@ViewById(R.id.progress_bar_horizontal)
	HorizontalProgressBarWithNumber mProgressBar;
	@ViewById(R.id.progress_bar_round)
	RoundProgressBarWithNumber mProgressBarRound;
	@ViewById(R.id.progress_bar_fan)
	FanProgressBar fanProgressBar;
	@ViewById(R.id.btn_start)
	Button btnStart;
	@ViewById(R.id.btn_pause)
	Button btnPause;
	@ViewById(R.id.progress_bar_normal)
	ProgressBar progressBar;

	private int count = 0;
	private static final int MSG_PROGRESS_UPDATE = 0x110;

	@Click(R.id.btn_start)
	void clickStart() {
		mProgressBar.setProgress(0);
		progressBar.setProgress(0);
		mProgressBarRound.setProgress(0);
		fanProgressBar.setProgress(0);
		mHandler.sendEmptyMessage(MSG_PROGRESS_UPDATE);
		Toast.makeText(this, "开始", Toast.LENGTH_SHORT).show();
//		horizontalProgressBarWithNumber.setProgress(0);
//		progressBar.setProgress(0);
//		Toast.makeText(this, "开始", Toast.LENGTH_SHORT).show();
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				for (int i = 1; i < 101; i++) {
//					count++;
//					try {
//						Thread.sleep(100);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//					Message msg = new Message();
//					msg.arg1 = count;
//					handler.sendMessage(msg);
//				}
//				count =0;
//			}
//		}).start();
	}
	@Click(R.id.btn_pause)
	void clickPause() {
		Toast.makeText(this, "暂停", Toast.LENGTH_SHORT).show();
		mHandler.removeMessages(MSG_PROGRESS_UPDATE);
	}

//	Handler handler = new Handler() {
//		@Override
//		public void handleMessage(Message msg) {
//			int progress = msg.arg1;
//
//			horizontalProgressBarWithNumber.setProgress(progress);
//			Log.i("Progress 1: ", horizontalProgressBarWithNumber.getProgress() + "");
//
//			progressBar.setProgress(progress);
//			Log.i("Progress 2: ", progressBar.getProgress() + "");
//
//		}
//	};
private Handler mHandler = new Handler() {
	public void handleMessage(android.os.Message msg) {
		int progress = mProgressBar.getProgress();

		mProgressBar.setProgress(++progress);
		progressBar.setProgress(++progress);
		mProgressBarRound.setProgress(++progress);
		fanProgressBar.setProgress(++progress);
		if (progress >= 100) {
			mHandler.removeMessages(MSG_PROGRESS_UPDATE);

		}
		mHandler.sendEmptyMessageDelayed(MSG_PROGRESS_UPDATE, 100);
	};
};
}
