package com.example.wbp.practice.utils;

import android.os.Environment;
import android.provider.SyncStateContract;
import android.util.Log;

import com.example.wbp.practice.BuildConfig;

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
}
