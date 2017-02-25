package com.bpwei.examples.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;

import com.bpwei.examples.R;

/**
 * Created by new on 15/8/10.
 *
 */
public class HorizontalProgressBarWithNumber extends BaseProgressBar {

	public HorizontalProgressBarWithNumber(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	public HorizontalProgressBarWithNumber(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		setHorizontalScrollBarEnabled(true);
	}

	@Override
	protected synchronized void onDraw(Canvas canvas) {
		canvas.save();
		// 画笔平移到指定paddingLeft， getHeight() / 2位置，注意以后坐标都为以此为0，0
		canvas.translate(getPaddingLeft(), getHeight() / 2);

		boolean noNeedBg = false;
		// 当前进度和总值的比例
		float radio = getProgress() * 1.0f / getMax();
		float progressPosX = (int) (mRealWidth * radio);
		// 绘制的文本
		String text = getProgress() + "%";

		// 拿到字体的宽度和高度
		float textWidth = mPaint.measureText(text);
		float textHeight = (mPaint.descent() - mPaint.ascent());

		// 如果达到最后，则未达到的进度条不需要绘制
		if (progressPosX + textWidth > mRealWidth) {
			progressPosX = mRealWidth - textWidth;
			noNeedBg = true;
		}

		// 绘制已经达到的进度
		float endX = progressPosX - mTextOffset / 2;
		if (endX > 0) {
			mPaint.setColor(mReachedBarColor);
			mPaint.setStrokeWidth(mUnReachedProgressBarHeight);
			canvas.drawLine(0, 0, endX, 0, mPaint);
		}
		// 绘制文本
		if (mIfDrawText) {
			mPaint.setColor(mTextColor);
			// 注意绘制位置 mPaint.descent()
			canvas.drawText(text, progressPosX, mPaint.descent(), mPaint);
		}

		// 绘制未达到的进度条
		if (!noNeedBg) {
			float start = progressPosX + mTextOffset / 2 + textWidth;
			mPaint.setColor(mUnReachedBarColor);
			mPaint.setStrokeWidth(mUnReachedProgressBarHeight);
			canvas.drawLine(start, 0, mRealWidth, 0, mPaint);
		}

		canvas.restore();
	}

}
