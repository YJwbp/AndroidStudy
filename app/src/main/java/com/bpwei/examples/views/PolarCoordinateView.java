package com.bpwei.examples.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by new on 16/1/9.
 */
@EView
public class PolarCoordinateView extends View {

	@Pref
	ScreenSP_ screenSP;

	private final int RAY_NUMBER_DEFAULT = 12;
	private final int CIRCLE_NUMBER_DEFAULT = 6;
	private final float CIRCLE_FULL_ANGLE = 360F;
	private final float ANGLE_TO_RADIAN_FACTOR = (float) Math.PI / 180;
	private final double ZERO = 0.01;

	private float viewWidth;
	private float drawingAreaRadius;
	private int rayNumber = RAY_NUMBER_DEFAULT;
	private int circleNumber = CIRCLE_NUMBER_DEFAULT;
	private PointF centerOfView;

	private int blackDotRadius = 3;
	private int redDotRadius = 10;
	private int lineWidth = 1;
	private int xyLineWidth = 2;

	// 保存
	private List<Point> selectedPosList = new ArrayList<>();
	private PolorPoint[][] polarPoints; // 极坐标
	private PointF[][] xyCenterPoints; // 直角坐标系 原点在中心
	private float[] xyCenterPoints1d;
	private PointF[][] xyCornerPoints; // 直角坐标系 原点在左上角
	private float[] xyCornerPoints1d;

	private List<PointF> selectedXYCornerPointList = new ArrayList<>();
	private List<PointF> selectedXYCoordinates = new ArrayList<>();
	private float[] xyCornerPoints1dSelected;

	private PointF xyCenterPoint;

	Paint blackDotPain;
	Paint redDotPain;
	Paint lightLinePain;
	Paint darkLinePain;

	String X, Y, rawX, rawY;

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
	void afterViews() {
		selectedXYCoordinates.add(new PointF(540,540));
		init();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO 触摸选点
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:

				break;
			case MotionEvent.ACTION_MOVE:
				X = String.valueOf(event.getX());
				Y = String.valueOf(event.getY());
				rawX = String.valueOf(event.getRawX());
				rawY = String.valueOf(event.getRawY());

				Utils.debug("Point Move: " + " X=" + X + " Y=" + Y + " rawX=" + rawX + " rawY=" + rawY + " Top=" + getTop()+" centerX="+(event.getX() - viewWidth / 2)+" centerY="+(-event.getY() + viewWidth / 2));
				checkSelect(event.getX() - viewWidth / 2, -event.getY() + viewWidth / 2);
				break;
			case MotionEvent.ACTION_CANCEL:
			case MotionEvent.ACTION_UP:

				break;
		}
		return true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// canvas 绘制的坐标系原点在 当前View的左上角 而不是整个屏幕的左上角

//		canvas.drawCircle(0, 0, 50, blackDotPain);
//		canvas.drawCircle(viewWidth/2, viewWidth/2, 50, redDotPain);

		// 绘制所有同心圆
		for (int i = 0; i < circleNumber; i++) {
			canvas.drawCircle(viewWidth / 2, viewWidth / 2, (i + 1) * (viewWidth / 2) / circleNumber, lightLinePain);
		}

		// 绘制所有射线
		for (int i = 0; i < rayNumber; i++) {
			canvas.drawLine(viewWidth / 2, viewWidth / 2, xyCornerPoints[circleNumber - 1][i].x, xyCornerPoints[circleNumber - 1][i].y, lightLinePain);
		}

		// 绘制所有点
		if (xyCornerPoints1d != null) {
			canvas.drawPoints(xyCornerPoints1d, blackDotPain);
		}

		// 绘制所有已选点
//		if (xyCornerPoints1dSelected != null) {
//			canvas.drawPoints(xyCornerPoints1dSelected, redDotPain);
//		}
		for (PointF point :selectedXYCoordinates) {
			canvas.drawPoint(point.x, point.y, redDotPain);
			Utils.debug("Point Draw: x="+point.x+" y="+point.y);
		}

		canvas.drawPoint(675, 306, redDotPain);

		if (X == null || Y == null || rawX == null || rawY == null) {
			return;
		}
		canvas.drawText(X, 0, 0, lightLinePain);
		canvas.drawText(Y, 200, 0, lightLinePain);
		canvas.drawText(rawX, 400, 0, lightLinePain);
		canvas.drawText(rawY, 600, 0, lightLinePain);
	}

	private void init() {
		// TODO viewWidth 不是屏幕宽度的情况
		viewWidth = screenSP.width().get();
		drawingAreaRadius = viewWidth / 2 - getPaddingLeft();
		centerOfView = new PointF();
		centerOfView.x = viewWidth / 2 + getX();
		centerOfView.y = viewWidth / 2 + getY();

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

	private void initCoordinates() {

		xyCenterPoint = new PointF();
		xyCenterPoint.x = 0;
		xyCenterPoint.y = 0;

		polarPoints = new PolorPoint[circleNumber][rayNumber];

		for (int circle = 0; circle < circleNumber; circle++) {
			for (int ray = 0; ray < rayNumber; ray++) {
				polarPoints[circle][ray] = new PolorPoint();

				polarPoints[circle][ray].setAngle((ray + 1) * CIRCLE_FULL_ANGLE / rayNumber);
				polarPoints[circle][ray].setRadius((circle + 1) * drawingAreaRadius / circleNumber);
				polarPoints[circle][ray].setIsSelected(false);
			}
		}

		changePolarToXY();
	}

	/**
	 * 极坐标转换为XY坐标
	 */
	private void changePolarToXY() {
		float x, y;
		xyCenterPoints = new PointF[circleNumber][rayNumber];
		xyCenterPoints1d = new float[circleNumber * rayNumber * 2];

		xyCornerPoints = new PointF[circleNumber][rayNumber];
		xyCornerPoints1d = new float[circleNumber * rayNumber * 2];

		for (int circle = 0; circle < circleNumber; circle++) {
			for (int ray = 0; ray < rayNumber; ray++) {
				xyCenterPoints[circle][ray] = new PointF();

				x = polarPoints[circle][ray].getRadius() * (float) Math.cos(polarPoints[circle][ray].getAngle() * ANGLE_TO_RADIAN_FACTOR);
				y = polarPoints[circle][ray].getRadius() * (float) Math.sin(polarPoints[circle][ray].getAngle() * ANGLE_TO_RADIAN_FACTOR);

				// 生成以屏幕中心为原点的
				xyCenterPoints[circle][ray].x = x;
				xyCenterPoints[circle][ray].y = y;
				xyCenterPoints1d[circle * rayNumber * 2 + ray * 2] = x;
				xyCenterPoints1d[circle * rayNumber * 2 + ray * 2 + 1] = y;

				// 生成以屏幕左上角为原点的
				xyCornerPoints[circle][ray] = xyCenterToCorner(xyCenterPoints[circle][ray]);
				xyCornerPoints1d[circle * rayNumber * 2 + ray * 2] = x + viewWidth / 2;
				xyCornerPoints1d[circle * rayNumber * 2 + ray * 2 + 1] = -y + viewWidth / 2;
			}
		}

		Utils.debug("check points");
	}

	/**
	 * XY中心坐标系 转换为 XY左上角坐标系
	 *
	 * @param centerPoint
	 * @return
	 */
	private PointF xyCenterToCorner(PointF centerPoint) {
		return new PointF(centerPoint.x + viewWidth / 2, -centerPoint.y + viewWidth / 2);
	}

	/**
	 * 返回两点距离
	 *
	 * @param p1
	 * @param p2
	 * @return
	 */
	private double getDistance(PointF p1, PointF p2) {
		if (p1 == null || p2 == null) {
			return 0;
		}
		return Math.pow(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2), 0.5);
	}

	private double getAngle(PointF xyCenterP) {

		if (Math.abs(xyCenterP.x) < ZERO) {
			return xyCenterP.y > 0 ? 90.0 : 270.0;
		}
		double angle = Math.atan(xyCenterP.y / xyCenterP.x) * 180 / Math.PI;
		return xyCenterP.y > 0 ? angle : angle + 180;
	}

	private double getAngle(float x, float y) {
		if (Math.abs(x) < ZERO) {
			return y > 0 ? 90.0 : 270.0;
		}
		double angle = Math.atan(y / x) * 180 / Math.PI;
		if (x < 0) {
			return angle + 180;
		} else {
			return y > 0 ? angle : angle + 360;
		}
	}

	/**
	 * 返回所有点中的最小间距
	 *
	 * @return
	 */
	private double getMinPointDistance() {
		double minCircle = getDistance(xyCenterPoints[0][0], xyCenterPoints[0][1]);
		double minRay = viewWidth / 2 / circleNumber;
		return 50;
//		return minCircle < minRay ? minCircle : minRay;
	}

	/**
	 * 根据中心坐标系坐标 检查是否选中点
	 *
	 * @param x
	 * @param y
	 */
	private void checkSelect(float x, float y) {
		// 算出角度
		double angle = getAngle(x, y);
		// 算出长度
		double lenth = getDistance(new PointF(x, y), xyCenterPoint);
		// 判断归属

		double deltaAngle = 360 / rayNumber;
		double deltaLenth = viewWidth / 2 / circleNumber;
		int rayPosFloor = ((int) (angle / deltaAngle)-1+rayNumber)%rayNumber;
		int circlePosFloor = (int) (lenth / deltaLenth)-1;

		// TODO 注意0 与 360 的重合问题

		Utils.debug("Point select: angle="+angle+" length="+lenth+" dAngle="+deltaAngle+" dLen="+deltaLenth+" rayFloor="+rayPosFloor+" circleFloor="+circlePosFloor);
		// 判断选择了哪个点
		int rayPos, circlePos;
		for (int i = 0; i < 2; i++) {
			if (circlePosFloor < 0) {
				continue;
			}
			for (int j = 0; j < 2; j++) {
				rayPos = (rayPosFloor + j+rayNumber)%rayNumber;
				circlePos = circlePosFloor + i;
				if (circlePos > circleNumber - 1) {
					continue;
				}
				Utils.debug("Point Distance >> "+(i*2+j)+": "+getDistance(xyCenterPoints[circlePos][rayPos], new PointF(x, y))+ " threshold: "+getMinPointDistance()/2+" Seleceted >> "+(getDistance(xyCenterPoints[circlePos][rayPos], new PointF(x, y)) < getMinPointDistance()/2));
				if (getDistance(xyCenterPoints[circlePos][rayPos], new PointF(x, y)) < getMinPointDistance()/2) {
					// 增加选择点 防止相同点重复添加
					if(selectedPosList.size()>0 && selectedPosList.get(selectedPosList.size()-1).equals(new Point(circlePos, rayPos))){
						return;
					}
					selectedPosList.add(new Point(circlePos, rayPos));

					polarPoints[circlePos][rayPos].setIsSelected(true);
					PointF selectedPoint = getXYCornerPointFromPolar(polarPoints[circlePos][rayPos]);
					selectedXYCoordinates.add(selectedPoint);

					invalidate();

					Utils.debug("Select Points List: lenth -------->>" + selectedXYCoordinates.size());
					Utils.debug("Select Points List: " + selectedXYCoordinates.toString());

//					float[] selectedPoints = new float[selectedXYCoordinates.size() * 2];
//					if(xyCornerPoints1dSelected!=null){
//						System.arraycopy(xyCornerPoints1dSelected, 0, selectedPoints, 0, xyCornerPoints1dSelected.length);
//					}
//					selectedPoints[selectedPoints.length - 2] = selectedPoint.x;
//					selectedPoints[selectedPoints.length - 1] = selectedPoint.y;
//
//					xyCornerPoints1dSelected = selectedPoints;
//					Utils.debug("Select Point Polar: " + polarPoints[circlePos][rayPos] + " CornerXY: " + selectedPoint.x + " " + selectedPoint.y);
//
//					for(int m=0;m<selectedPoints.length;m = m+2){
//						Utils.debug("Select Points length:"+selectedPoints.length+": x=" + selectedPoints[m]+" y="+selectedPoints[m+1]);
//					}

					return;
				}
			}
		}

	}

	private PointF getXYCornerPointFromPolar(PolorPoint polarPoint) {
		if (polarPoint == null) {
			return null;
		}
		float centerX = polarPoint.getRadius() * (float) Math.cos(polarPoint.getAngle() * ANGLE_TO_RADIAN_FACTOR);
		float centerY = polarPoint.getRadius() * (float) Math.sin(polarPoint.getAngle() * ANGLE_TO_RADIAN_FACTOR);

		return xyCenterToCorner(new PointF(centerX, centerY));
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
