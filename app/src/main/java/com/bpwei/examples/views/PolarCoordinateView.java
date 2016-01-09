package com.bpwei.examples.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.bpwei.examples.R;
import com.bpwei.examples.entites.PolorPoint;

import org.androidannotations.annotations.EView;

import java.util.List;

/**
 * Created by new on 16/1/9.
 */
public class PolarCoordinateView extends View {

	private final int RAY_NUMBER_DEFAULT = 24;
	private final int CIRCLE_NUMBER_DEFAULT = 10;
	private final float CIRCLE_FULL_ANGLE = 360F;
	private final float ANGLE_TO_RADIAN_FACTOR = (float)Math.PI/180;

	private float viewWidth;
	private float drawingAreaRadius;
	private int rayNumber = RAY_NUMBER_DEFAULT;
	private int circleNumber = CIRCLE_NUMBER_DEFAULT;
	private PointF centerOfView;

	private int dotRadius = 2;
	private int lineWidth = 1;
	private int xyLineWidth = 2;

	// 保存
	private List<PolorPoint> selectedPolarPointList;
	private PolorPoint [][] polarPoints;
	private PointF[][] xyPoints;
	private float[] xyPoints1d;


	Paint blackDotPain;
	Paint redDotPain;
	Paint lightLinePain;
	Paint darkLinePain;

	public PolarCoordinateView(Context context) {
		super(context);
	}

	public PolarCoordinateView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PolarCoordinateView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return super.onTouchEvent(event);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);



		// 绘制所有点
		canvas.drawPoints(xyPoints1d, blackDotPain);

		// 绘制所有同心圆
		canvas.drawCircle();

		// 绘制所有射线
		canvas.drawLines();
	}

	private void init(){
		viewWidth = getWidth();
		drawingAreaRadius = viewWidth/2-getPaddingLeft();
		centerOfView = new PointF();
		centerOfView.x = viewWidth/2+getX();
		centerOfView.y = viewWidth/2+getY();

		blackDotPain = new Paint();
		blackDotPain.setColor(getResources().getColor(R.color.bg_black_80));
		blackDotPain.setStrokeWidth(4);

		redDotPain = new Paint();
		redDotPain.setColor(getResources().getColor(R.color.color_red));
		redDotPain.setStrokeWidth(4);

		lightLinePain = new Paint();
		lightLinePain.setColor(getResources().getColor(R.color.bg_black_20));
		lightLinePain.setStrokeWidth(1);

		darkLinePain = new Paint();
		darkLinePain.setColor(getResources().getColor(R.color.bg_black_80));
		darkLinePain.setStrokeWidth(1);

		initCoordinates();
	}

	private void initCoordinates(){
		polarPoints = new PolorPoint[circleNumber][rayNumber];
		xyPoints = new PointF[circleNumber][rayNumber];
		xyPoints1d = new float[circleNumber*rayNumber];

		for(int circle=0;circle<circleNumber;circle++){
			for (int ray=0;ray<rayNumber;ray++){
				polarPoints[circle][ray].setAngle(ray*CIRCLE_FULL_ANGLE/rayNumber);
				polarPoints[circle][ray].setRadius(circle * drawingAreaRadius / circleNumber);
				polarPoints[circle][ray].setIsSelected(false);
			}
		}

		changePolarToXY();
	}

	private void changePolarToXY() {
		float x,y;
		for (int circle = 0; circle < circleNumber; circle++) {
			for (int ray = 0; ray < rayNumber; ray++) {
				x = polarPoints[circle][ray].getRadius() * (float) Math.cos(polarPoints[circle][ray].getAngle() * ANGLE_TO_RADIAN_FACTOR);
				y= polarPoints[circle][ray].getRadius() * (float) Math.sin(polarPoints[circle][ray].getAngle() * ANGLE_TO_RADIAN_FACTOR);
				xyPoints[circle][ray].x = x;
				xyPoints[circle][ray].y = y;
				xyPoints1d[circle*rayNumber*2] = x;
				xyPoints1d[circle*rayNumber*2+ray*2+1] = y;

			}
		}
	}

	public int getRayNumber() {
		return rayNumber;
	}

	public void setRayNumber(int rayNumber) {
		this.rayNumber = rayNumber;
	}

	public int getCircleNumber() {
		return circleNumber;
	}

	public void setCircleNumber(int circleNumber) {
		this.circleNumber = circleNumber;
	}
}
