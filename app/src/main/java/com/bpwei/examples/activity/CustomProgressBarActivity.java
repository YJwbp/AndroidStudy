package com.bpwei.examples.activity;

import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bpwei.examples.R;
import com.bpwei.examples.views.DownloadStatusProgressBar;
import com.bpwei.examples.views.FanProgressBar;
import com.bpwei.examples.views.HorizontalProgressBarWithNumber;
import com.bpwei.examples.views.RoundProgressBarWithNumber;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

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
    void downloadClick() {
        switch (mProgressDownloadStatus.getStatus()) {
            case DownloadStatusProgressBar.STATUS_IDLE:
                mProgressDownloadStatus.setStatus(DownloadStatusProgressBar.STATUS_WAITING);
                enterDownloading();
                break;
            case DownloadStatusProgressBar.STATUS_WAITING:
                mProgressDownloadStatus.setStatus(DownloadStatusProgressBar.STATUS_IDLE);
                break;
            case DownloadStatusProgressBar.STATUS_ONGOING:
                mProgressDownloadStatus.setStatus(DownloadStatusProgressBar.STATUS_IDLE);
                break;
            case DownloadStatusProgressBar.STATUS_DONE:
                break;
            case DownloadStatusProgressBar.STATUS_FAILED:
                break;
        }

    }

    @UiThread(delay = 2000)
    void enterDownloading() {
        mProgressDownloadStatus.setStatus(DownloadStatusProgressBar.STATUS_ONGOING);
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            int progress = mProgressBar.getProgress();

            mProgressBar.setProgress(++progress);
            progressBar.setProgress(++progress);
            mProgressBarRound.setProgress(++progress);

            if (mProgressDownloadStatus.getStatus() == DownloadStatusProgressBar.STATUS_ONGOING) {
                mProgressDownloadStatus.setProgress(++progress);
            }

            if (progress >= 100) {
                mHandler.removeMessages(MSG_PROGRESS_UPDATE);
                mProgressDownloadStatus.setStatus(DownloadStatusProgressBar.STATUS_IDLE);
            }
            mHandler.sendEmptyMessageDelayed(MSG_PROGRESS_UPDATE, 300);
        }

        ;
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

    private Handler statusHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {


        }
    };
}
