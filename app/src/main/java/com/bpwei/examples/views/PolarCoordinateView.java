package com.bpwei.examples.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.bpwei.examples.R;
import com.bpwei.examples.entites.PolorPoint;
import com.bpwei.examples.sp.ScreenSP_;
import com.bpwei.examples.utils.Utils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EView;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.util.List;

/**
 * Created by new on 16/1/9.
 */
@EView
public class PolarCoordinateView extends View {

	@Pref
	ScreenSP_ screenSP;

	private final int RAY_NUMBER_DEFAULT = 24;
	private final int CIRCLE_NUMBER_DEFAULT = 10;
	private final float CIRCLE_FULL_ANGLE = 360F;
	private final float ANGLE_TO_RADIAN_FACTOR = (float)Math.PI/180;

	private float viewWidth;
	private float drawingAreaRadius;
	private int rayNumber = RAY_NUMBER_DEFAULT;
	private int circleNumber = CIRCLE_NUMBER_DEFAULT;
	private PointF centerOfView;

	private int blackDotRadius = 3;
	private int redDotRadius = 4;
	private int lineWidth = 1;
	private int xyLineWidth = 2;

	// 保存
	private List<PolorPoint> selectedPolarPointList;
	private PolorPoint [][] polarPoints; // 极坐标
	private PointF[][] xyCenterPoints; // 直角坐标系 原点在中心
	private float[] xyCenterPoints1d;
	private PointF[][] xyCornerPoints; // 直角坐标系 原点在左上角
	private float[] xyCornerPoints1d;


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

	@AfterViews
	void afterViews(){
		init();
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO 触摸选点
		return super.onTouchEvent(event);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// canvas 绘制的坐标系原点在 当前View的左上角 而不是整个屏幕的左上角

//		canvas.drawCircle(0, 0, 50, blackDotPain);
//		canvas.drawCircle(viewWidth/2, viewWidth/2, 50, redDotPain);

		// 绘制所有同心圆
		for (int i=0;i<circleNumber;i++){
			canvas.drawCircle(viewWidth / 2, viewWidth / 2, (i + 1) * (viewWidth / 2) / circleNumber, lightLinePain);
		}

		// 绘制所有射线
		for (int i=0;i<rayNumber;i++){
			canvas.drawLine(viewWidth/2,viewWidth/2,xyCornerPoints[circleNumber-1][i].x,xyCornerPoints[circleNumber-1][i].y,lightLinePain);
		}

		// 绘制所有点
		canvas.drawPoints(xyCornerPoints1d, blackDotPain);

		// 绘制所有已选点
		canvas.drawPoints(xyCornerPoints1d, redDotPain);

	}

	private void init(){
		// TODO viewWidth 不是屏幕宽度的情况
		viewWidth = screenSP.width().get();
		drawingAreaRadius = viewWidth/2-getPaddingLeft();
		centerOfView = new PointF();
		centerOfView.x = viewWidth/2 + getX();
		centerOfView.y = viewWidth/2+getY();

		blackDotPain = new Paint();
		blackDotPain.setColor(getResources().getColor(R.color.bg_black_80));
		blackDotPain.setStrokeWidth(blackDotRadius);
		blackDotPain.setAntiAlias(true);

		redDotPain = new Paint();
		redDotPain.setColor(getResources().getColor(R.color.color_red));
		redDotPain.setStrokeWidth(redDotRadius);
		redDotPain.setAntiAlias(true);

		lightLinePain = new Paint();
		lightLinePain.setColor(getResources().getColor(R.color.bg_black_20));
		lightLinePain.setStrokeWidth(lineWidth);
		lightLinePain.setAntiAlias(true);
		lightLinePain.setStyle(Paint.Style.STROKE);

		darkLinePain = new Paint();
		darkLinePain.setColor(getResources().getColor(R.color.bg_black_80));
		darkLinePain.setStrokeWidth(xyLineWidth);
		darkLinePain.setAntiAlias(true);
		darkLinePain.setStyle(Paint.Style.STROKE);

		initCoordinates();
	}

	private void initCoordinates(){
		polarPoints = new PolorPoint[circleNumber][rayNumber];

		for(int circle=0;circle<circleNumber;circle++){
			for (int ray=0;ray<rayNumber;ray++){
				polarPoints[circle][ray] = new PolorPoint();

				polarPoints[circle][ray].setAngle((ray+1)*CIRCLE_FULL_ANGLE/rayNumber);
				polarPoints[circle][ray].setRadius((circle+1) * drawingAreaRadius / circleNumber);
				polarPoints[circle][ray].setIsSelected(false);
			}
		}

		changePolarToXY();
	}

	private void changePolarToXY() {
		float x,y;
		xyCenterPoints = new PointF[circleNumber][rayNumber];
		xyCenterPoints1d = new float[circleNumber*rayNumber*2];

		xyCornerPoints = new PointF[circleNumber][rayNumber];
		xyCornerPoints1d = new float[circleNumber*rayNumber*2];

		for (int circle = 0; circle < circleNumber; circle++) {
			for (int ray = 0; ray < rayNumber; ray++) {
				xyCenterPoints[circle][ray] = new PointF();

				x = polarPoints[circle][ray].getRadius() * (float) Math.cos(polarPoints[circle][ray].getAngle() * ANGLE_TO_RADIAN_FACTOR);
				y= polarPoints[circle][ray].getRadius() * (float) Math.sin(polarPoints[circle][ray].getAngle() * ANGLE_TO_RADIAN_FACTOR);

				// 生成以屏幕中心为原点的
				xyCenterPoints[circle][ray].x = x;
				xyCenterPoints[circle][ray].y = y;
				xyCenterPoints1d[circle*rayNumber*2+ray*2] = x;
				xyCenterPoints1d[circle*rayNumber*2+ray*2+1] = y;

				// 生成以屏幕左上角为原点的
				xyCornerPoints[circle][ray] = xyCenterToCorner(xyCenterPoints[circle][ray]);
				xyCornerPoints1d[circle*rayNumber*2+ray*2] = x+viewWidth/2;
				xyCornerPoints1d[circle*rayNumber*2+ray*2+1] = -y+viewWidth/2;
			}
		}

		Utils.debug("check points");
	}

	private PointF xyCenterToCorner(PointF centerPoint){
		return new PointF(centerPoint.x+viewWidth/2,-centerPoint.y+viewWidth/2);
	}


//	private PointF xyCornerToCenter(PointF cornerPoint){
//		return new PointF(cornerPoint.x-viewWidth,-cornerPoint.y+viewWidth);
//	}
//
//	private void genCornerFromCenter(PointF cornerPoint, PointF centerPoint){
//		cornerPoint.x = centerPoint.x +viewWidth;
//		cornerPoint.y = -centerPoint.y+viewWidth;
//	}
//
//	private void genCornerFromCenter(float[] centerPoint, float[] cornerPoint){
//	}

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
