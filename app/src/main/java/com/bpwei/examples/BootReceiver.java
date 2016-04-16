package com.bpwei.examples;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.bpwei.examples.activity.SplashActivity_;
import com.bpwei.examples.utils.Utils;

import org.androidannotations.annotations.Receiver;

/**
 * Created by new on 16/3/1.
 */
public class BootReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Utils.debug("BootReceiver >> BOOT_COMPLETED "+intent);
		SplashActivity_.intent(context).flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
	}
}
