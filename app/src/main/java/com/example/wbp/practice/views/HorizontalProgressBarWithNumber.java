package com.example.wbp.practice.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;

import com.example.wbp.practice.R;

/**
 * Created by new on 15/8/10.
 */
public class HorizontalProgressBarWithNumber extends ProgressBar {

	private static final int DEFAULT_TEXT_SIZE = 10;
	private static final int DEFAULT_TEXT_COLOR = 0XFFFC00D1;
	private static final int DEFAULT_COLOR_UNREACHED_COLOR = 0xFFd3d6da;
	private static final int DEFAULT_HEIGHT_REACHED_PROGRESS_BAR = 2;
	private static final int DEFAULT_HEIGHT_UNREACHED_PROGRESS_BAR = 2;
	private static final int DEFAULT_SIZE_TEXT_OFFSET = 10;

	protected Paint mPaint = new Paint();
	protected int mTextColor = DEFAULT_TEXT_COLOR;
	protected int mTextSize = sp2px(DEFAULT_TEXT_SIZE);

	protected int mTextOffset = dp2px(DEFAULT_SIZE_TEXT_OFFSET);

	protected int mReachedProgressBarHeight = dp2px(DEFAULT_HEIGHT_REACHED_PROGRESS_BAR);

	protected int mReachedBarColor = DEFAULT_TEXT_COLOR;

	protected int mUnReachedBarColor = DEFAULT_COLOR_UNREACHED_COLOR;

	protected int mUnReachedProgressBarHeight = dp2px(DEFAULT_HEIGHT_UNREACHED_PROGRESS_BAR);

	protected int mRealWidth;

	protected boolean mIfDrawText = true;

	protected static final int VISIBLE = 0;

	public HorizontalProgressBarWithNumber(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}
	public HorizontalProgressBarWithNumber(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		setHorizontalScrollBarEnabled(true);
		// 读取自定义属性
		obtainStyledAttributes(attrs);
		// 设置画笔
		mPaint.setTextSize(mTextSize);
		mPaint.setColor(mTextColor);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
	}

	private void obtainStyledAttributes(AttributeSet attrs) {
		final TypedArray array = getContext().obtainStyledAttributes(attrs,
				R.styleable.HorizontalProgressBarWithNumber);

		mTextColor = array
				.getColor(
						R.styleable.HorizontalProgressBarWithNumber_progress_text_color,
						DEFAULT_TEXT_COLOR);
		mTextSize = (int) array
				.getDimension(
						R.styleable.HorizontalProgressBarWithNumber_progress_text_size,
						DEFAULT_TEXT_SIZE);
		mReachedBarColor = array
				.getColor(
						R.styleable.HorizontalProgressBarWithNumber_progress_reached_color,
						mTextColor);
		mUnReachedBarColor = array
				.getColor(
						R.styleable.HorizontalProgressBarWithNumber_progress_unreached_color,
						DEFAULT_COLOR_UNREACHED_COLOR);
		mReachedProgressBarHeight = (int) array
				.getDimension(
						R.styleable.HorizontalProgressBarWithNumber_progress_reached_bar_height,
						mReachedProgressBarHeight);
		mUnReachedProgressBarHeight = (int) array
				.getDimension(
						R.styleable.HorizontalProgressBarWithNumber_progress_unreached_bar_height,
						mUnReachedProgressBarHeight);
		mTextOffset = (int) array
				.getDimension(
						R.styleable.HorizontalProgressBarWithNumber_progress_text_offset,
						mTextOffset);

		int textVisible = array
				.getInt(R.styleable.HorizontalProgressBarWithNumber_progress_text_visibility,
						VISIBLE);
		if (textVisible == INVISIBLE) {
			mIfDrawText = false;
		}
		array.recycle();
	}

	// 我们所有的是属性比如进度条宽度都让用户自定义了，所以测量也需要稍微变一下
	@Override
	protected synchronized void onMeasure(int widthMeasureSpec,
			int heightMeasureSpec) {
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		if (heightMode != MeasureSpec.EXACTLY) {
			float textHeight = (mPaint.descent() - mPaint.ascent());
			int exceptHeight = (int) (getPaddingTop() + getPaddingBottom() + Math
					.max(Math.max(mUnReachedProgressBarHeight,
							mReachedProgressBarHeight), Math.abs(textHeight)));
			// 这里设定为MeasureSpec.EXACTLY！！！否则进度不更新，或者不显示进度条
			heightMeasureSpec = MeasureSpec.makeMeasureSpec(exceptHeight,
					MeasureSpec.EXACTLY);
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
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

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mRealWidth = w - getPaddingRight() - getPaddingLeft();
	}

	protected int dp2px(int dpVal) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				dpVal, getResources().getDisplayMetrics());
	}
	protected int sp2px(int spVal) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
				spVal, getResources().getDisplayMetrics());

	}
}
