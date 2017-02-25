package com.bpwei.examples.utils;

import android.app.Activity;
import android.graphics.Point;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;

import com.bpwei.examples.BuildConfig;

/**
 * Created by new on 15/8/31. 常用工具类
 */
public class Utils {
	public static void debug(String str) {
		if (BuildConfig.DEBUG) {
			Log.d("MyAndroid: ", str);
		}
	}

	/**
	 * 判断手机是否有SD卡。
	 *
	 * @return 有SD卡返回true，没有返回false。
	 */
	public boolean hasSDCard() {
		return Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState());
	}

	/**
	 * 获取屏幕尺寸
	 *
	 * @param activity
	 * @return
	 */
	public static Point getScreenSize(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return new Point(dm.widthPixels, dm.heightPixels);
	}

}
