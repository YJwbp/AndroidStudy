package com.bpwei.examples.interfaces;

/**
 * Created by new on 16/4/13.
 */
public interface DownloadProgressListener {
	void onProgress(int progress);
	void onStatusChange(String status);
	void onStart();
	void onPause();
	void onResume();
	void onStop();
}
