package com.example.wbp.practice.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.example.wbp.practice.R;

/**
 * Created by new on 15/8/10.
 */
public class RoundProgressBarWithNumber extends HorizontalProgressBarWithNumber {

	protected static final int DEFAULT_RADIUS = 30;
	protected int mRadius = 0;
	protected int paintWidth = 0;
	protected int totalWidth = 0;
	protected int totalHeight = 0;

	public RoundProgressBarWithNumber(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	public RoundProgressBarWithNumber(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);

		setHorizontalScrollBarEnabled(true);

		TypedArray array = context.obtainStyledAttributes(attrs,
				R.styleable.RoundProgressBarWidthNumber);
		mRadius = (int) array.getDimension(
				R.styleable.RoundProgressBarWidthNumber_radius, DEFAULT_RADIUS);

		array.recycle();
		// 设置画笔
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
	}

	@Override
	protected synchronized void onMeasure(int widthMeasureSpec,
			int heightMeasureSpec) {
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);

		paintWidth = Math.max(mReachedProgressBarHeight,
				mUnReachedProgressBarHeight);

		if (heightMode != MeasureSpec.EXACTLY) {

			totalWidth = (int) (getPaddingLeft() + getPaddingRight() + mRadius
					* 2 + paintWidth * 2);
			totalHeight = (int) (getPaddingTop() + getPaddingBottom() + mRadius
					* 2 + paintWidth * 2);
			heightMeasureSpec = MeasureSpec.makeMeasureSpec(totalWidth,
					MeasureSpec.EXACTLY);
		}
		if (widthMode != MeasureSpec.EXACTLY) {
			widthMeasureSpec = MeasureSpec.makeMeasureSpec(totalWidth,
					MeasureSpec.EXACTLY);
		}
		setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);

//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

	}

	@Override
	protected synchronized void onDraw(Canvas canvas) {
		String text = getProgress() + "%";
		int textWidth = (int) mPaint.measureText(text);

		canvas.save();
		canvas.translate(getPaddingLeft(), getPaddingTop());
		// 绘制未达到的进度条
		// 防止再次绘制Style被覆盖
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setColor(mUnReachedBarColor);
		mPaint.setStrokeWidth(mUnReachedProgressBarHeight);
		// 注意半径、圆心的选择
		canvas.drawCircle(totalWidth / 2, totalWidth / 2, mRadius, mPaint);

		// 绘制达到的进度条
		mPaint.setColor(mReachedBarColor);
		mPaint.setStrokeWidth(mReachedProgressBarHeight);
		float sweepAngle = getProgress() * 1.0f / getMax() * 360;
		// 关于绘制 drawArc ，得到的结果会fit inside,
		canvas.drawArc(
				new RectF(totalWidth / 2 - mRadius, totalHeight / 2 - mRadius,
						totalWidth / 2 + mRadius, totalHeight / 2 + mRadius),
				0, sweepAngle, false, mPaint);

		// 绘制进度数字
		mPaint.setColor(mTextColor);
		mPaint.setStyle(Paint.Style.FILL);
		canvas.drawText(text, totalWidth / 2 - textWidth / 2, totalWidth / 2
				+ mPaint.descent(), mPaint);

		canvas.restore();
	}
}
