package com.bpwei.examples.activity;

import android.os.Message;
import android.widget.Button;

import com.bpwei.examples.R;
import com.bpwei.examples.views.DownloadStatusProgressBar;
import com.bpwei.examples.views.FanProgressBar;
import com.bpwei.examples.views.HorizontalProgressBarWithNumber;
import com.bpwei.examples.views.RoundProgressBarWithNumber;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by new on 15/8/10.
 */
@EActivity(R.layout.activity_progress_bar_customed)
public class CustomProgressBarActivity extends BaseActivity {
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
	@ViewById(R.id.progress_bar_downloadStatus)
	DownloadStatusProgressBar mProgressDownloadStatus;


	private int count = 0;
	private int countSimplest = 100;

	private String downloadStatus = "off";

	private static final int MSG_PROGRESS_UPDATE = 0x110;

	@Click(R.id.btn_start)
	void clickStart() {
		mProgressBar.setProgress(0);
		progressBar.setProgress(0);
		mProgressBarRound.setProgress(0);
		fanProgressBar.setProgress(100);

		handlerSimplest.postDelayed(runnable, 100);
		mHandler.sendEmptyMessage(MSG_PROGRESS_UPDATE);
		Toast.makeText(this, "开始", Toast.LENGTH_SHORT).show();
	}
	@Click(R.id.btn_pause)
	void clickPause() {
		Toast.makeText(this, "暂停", Toast.LENGTH_SHORT).show();
		mHandler.removeMessages(MSG_PROGRESS_UPDATE);
	}

	@Click(R.id.progress_bar_downloadStatus)
	void downloadClick(){
		if(downloadStatus.equals("on")){
			mProgressDownloadStatus.setStatus("off");

		}else if(downloadStatus.equals("off")){
			mProgressDownloadStatus.setStatus("on");
		}
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			int progress = mProgressBar.getProgress();

			mProgressBar.setProgress(++progress);
			progressBar.setProgress(++progress);
			mProgressBarRound.setProgress(++progress);

			mProgressDownloadStatus.setProgress(++progress);
			if(progress>50){
				mProgressDownloadStatus.setStatus("on");
			}else {
				mProgressDownloadStatus.setStatus("off");
			}

			if (progress >= 100) {
				mHandler.removeMessages(MSG_PROGRESS_UPDATE);
			}
			mHandler.sendEmptyMessageDelayed(MSG_PROGRESS_UPDATE, 100);
		};
	};

	// 最简单的计时任务
	private Handler handlerSimplest = new Handler();
	private Runnable runnable = new Runnable() {
		@Override
		public void run() {
			countSimplest--;
			fanProgressBar.setProgress(countSimplest);
			handlerSimplest.postDelayed(runnable, 100);
		}
	};

	private Handler statusHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {



		}
	};
}
