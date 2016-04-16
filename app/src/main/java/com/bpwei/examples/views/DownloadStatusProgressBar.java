package com.bpwei.examples.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.bpwei.examples.R;
import com.bpwei.examples.utils.Utils;

/**
 * Created by new on 16/4/13.
 */
public class DownloadStatusProgressBar extends RoundProgressBarWithNumber {

	final String DEFAULT_STATUS = "off";
	protected String status = DEFAULT_STATUS;
	protected int btnStatusWidth;
	protected int btnStatusHeight;

	// 修正显示三角形时的位置
	protected int triangleOffset;

	public DownloadStatusProgressBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DownloadStatusProgressBar(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		obtainStyledAttributes(attrs);
	}

	@Override
	protected void obtainStyledAttributes(AttributeSet attrs) {
		super.obtainStyledAttributes(attrs);

		TypedArray array = getContext().obtainStyledAttributes(attrs,
				R.styleable.DownloadStatusProgressBar);
		status = array.getString(R.styleable.DownloadStatusProgressBar_status);

		btnStatusHeight = (int) array.getDimension(
				R.styleable.DownloadStatusProgressBar_center_width, 160f);
		btnStatusWidth = (int) array.getDimension(
				R.styleable.DownloadStatusProgressBar_center_width, 160f);
		triangleOffset = (int) ((1 - Math.pow(3, 0.5) / 3) * (btnStatusWidth / 2)) / 2;

		Utils.debug("DownloadStatusProgressBar Size >> " + btnStatusHeight
				+ " " + btnStatusWidth + " status " + status);
		array.recycle();
	}

	@Override
	protected synchronized void onDraw(Canvas canvas) {
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

		Utils.debug("Size onDraw>> " + btnStatusHeight + " " + btnStatusWidth);

		if (status.equals("off")) {
			canvas.drawPath(getTrianglePath(), mPaint);
		} else if (status.equals("on")) {
			canvas.drawRect(getRect(), mPaint);
		}

		canvas.restore();
	}

	// 居中 矩形
	private Rect getRect() {
		return new Rect(totalWidth / 2 - btnStatusWidth / 2, totalHeight / 2
				- btnStatusHeight / 2, totalWidth / 2 + btnStatusWidth / 2,
				totalHeight / 2 + btnStatusHeight / 2);
	}

	// 居中，顶角向右 的等腰三角形
	private Path getTrianglePath() {
		Path path = new Path();
		path.moveTo(totalWidth / 2 - btnStatusWidth / 2 + triangleOffset,
				totalHeight / 2 - btnStatusHeight / 2);
		path.lineTo(totalWidth / 2 + btnStatusWidth / 2 + triangleOffset,
				totalHeight / 2);
		path.lineTo(totalWidth / 2 - btnStatusWidth / 2 + triangleOffset,
				totalHeight / 2 + btnStatusHeight / 2);
		path.close();
		return path;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}

