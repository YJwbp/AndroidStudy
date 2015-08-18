package com.example.wbp.practice.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.wbp.practice.R;

/**
 * Created by new on 15/8/16.
 */
public class BezierView extends View {

	private int width = 1;
	private int height = 1;
	private int intervalNarrow = 1;
	private int maxDragLength;
	private int color;
	private Paint paint;
	private Path path;
	private int widthChanging;

	private int ctrlX;
	private int ctrlY;
	private int ctrlX2;
	private int ctrlY2;
	private boolean ifNeedToUpdate = true;

	public BezierView(Context context, AttributeSet attrs) {
		super(context, attrs);
		getAttrs(attrs);
		setPaint();
		updateWidth(width);
	}

	private void getAttrs(AttributeSet attrs) {
		width = getResources().getDimensionPixelOffset(R.dimen.bezier_width);
		height = getResources().getDimensionPixelOffset(R.dimen.bezier_height);
		widthChanging = width;

		TypedArray array = getContext().getTheme().obtainStyledAttributes(
				attrs, R.styleable.BezierView, 0, 0);
		// Extract custom attributes into member variables
		try {
			intervalNarrow = array.getDimensionPixelOffset(
					R.styleable.BezierView_interval_narrowest, 1);
			maxDragLength = array.getDimensionPixelOffset(
					R.styleable.BezierView_max_drag_length, 1);
			color = array.getColor(R.styleable.BezierView_bezier_color,
					Color.BLACK);
		} finally {
			// TypedArray objects are shared and must be recycled.
			array.recycle();
		}
	}
	private void setPaint() {
		paint = new Paint();
		paint.setColor(color);
		paint.setStyle(Paint.Style.FILL);
		paint.setAntiAlias(true);
		paint.setDither(true);

		path = new Path();
	}
	public void updateWidth(int widthChanging) {
		if (widthChanging >= maxDragLength) {
			ifNeedToUpdate = false;
			return;
		}
		ifNeedToUpdate = true;
		this.widthChanging = widthChanging;
		// 计算贝塞尔曲线控制点坐标
		ctrlX = widthChanging / 2 + height / 4;
		ctrlY = (height - intervalNarrow) * (widthChanging - width)
				/ (maxDragLength - width);
		ctrlX2 = ctrlX;
		ctrlY2 = height - ctrlY;

	}
	public void reset() {

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (ifNeedToUpdate) {
			setMeasuredDimension(widthChanging, height);
			return;
		}
		setMeasuredDimension(maxDragLength, height);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawCircle(height / 2, height / 2, height / 2, paint);

		// 绘制贝塞尔方形
		path.reset();
		path.moveTo(height / 2, 0);
		path.quadTo(ctrlX, ctrlY, widthChanging, 0);
		path.lineTo(widthChanging, height);
		path.quadTo(ctrlX2, ctrlY2, height / 2, height);
		path.moveTo(height / 2, 0);
		canvas.drawPath(path, paint);

	}

}
