package com.example.wbp.practice.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.RelativeLayout;

import com.example.wbp.practice.R;
import com.example.wbp.practice.views.BezierView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by new on 15/8/16.
 */
@EActivity(R.layout.activity_bezier)
public class BezierActivity extends Activity {
	@ViewById(R.id.bezier)
	BezierView bezierView;
	@ViewById(R.id.rl_root)
	RelativeLayout rlRoot;

	int downX = 0;
	int delta = 0;
	int width = 1;
	RelativeLayout.LayoutParams lp;

	@AfterViews
	void afterViews() {
		width = getResources().getDimensionPixelOffset(R.dimen.bezier_width);
		lp = (RelativeLayout.LayoutParams) bezierView.getLayoutParams();
		bezierView.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				switch (motionEvent.getAction()) {
					case MotionEvent.ACTION_DOWN :
						downX = (int) motionEvent.getRawX();
						break;
					case MotionEvent.ACTION_MOVE :
						delta = (int) (width - motionEvent.getRawX() + downX);
						// 不能小于最小宽度
						if (delta < width) {
							break;
						}

						update(delta);
						Log.i("MOVE: ", "pos: " + motionEvent.getRawX()
								+ "  delta:" + delta);
						break;
					case MotionEvent.ACTION_UP :
						bezierView.reset();
						resetWithAnim();
						break;

				}
				return true;
			}
		});

	}
	private void resetWithAnim() {
		ValueAnimator anim = ValueAnimator.ofInt(delta, width);
		anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator valueAnimator) {
				int currentWidth = (int) valueAnimator.getAnimatedValue();
				update(currentWidth);

			}
		});
		anim.setInterpolator(new OvershootInterpolator());
		anim.setDuration(500);
		anim.start();
	}
	private void update(int width) {
		bezierView.updateWidth(width);
		// 目前想不到其他方法，在View之外通知View重新测量
		// requestLayout会导致调用measure layout,但是这需要我重写他们，因为我没有改变该View 的固有属性。
		lp.width = width;
		bezierView.setLayoutParams(lp);
		// 重新测量了，必定会重绘，无需再调用
		// bezierView.invalidate();
	}
}
