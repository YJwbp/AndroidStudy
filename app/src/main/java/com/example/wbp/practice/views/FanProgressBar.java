package com.example.wbp.practice.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

/**
 * Created by new on 15/8/11.
 */
public class FanProgressBar extends RoundProgressBarWithNumber {
	public FanProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected synchronized void onDraw(Canvas canvas) {
		String text = getProgress() + "%";
		int textWidth = (int) mPaint.measureText(text);

		canvas.save();
		canvas.translate(getPaddingLeft(), getPaddingTop());
		// 绘制未达到的进度条
		// 防止再次绘制Style被覆盖
		mPaint.setStyle(Paint.Style.FILL);
		mPaint.setColor(mUnReachedBarColor);
		// 注意半径、圆心的选择
		canvas.drawCircle(totalWidth / 2, totalWidth / 2, mRadius, mPaint);

		// 绘制达到的进度条
		mPaint.setColor(mReachedBarColor);
		float sweepAngle = getProgress() * 1.0f / getMax() * 360;
		// 关于绘制 drawArc ，得到的结果会fit inside,
		canvas.drawArc(
				new RectF(totalWidth / 2 - mRadius, totalHeight / 2 - mRadius,
						totalWidth / 2 + mRadius, totalHeight / 2 + mRadius),
				0, sweepAngle, true, mPaint);

		// 绘制进度数字
		mPaint.setColor(mTextColor);
		canvas.drawText(text, totalWidth / 2 - textWidth / 2, totalWidth / 2
				+ mPaint.descent(), mPaint);

		canvas.restore();
	}
}
