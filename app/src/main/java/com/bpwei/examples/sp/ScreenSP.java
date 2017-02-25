package com.bpwei.examples.sp;

import org.androidannotations.annotations.sharedpreferences.DefaultInt;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by new on 16/1/10.
 */
@SharedPref(value = SharedPref.Scope.UNIQUE)
public interface ScreenSP {

	@DefaultInt(0)
	int width();

	@DefaultInt(0)
	int height();
}
