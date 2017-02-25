package com.bpwei.examples.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;

import com.bpwei.examples.R;
import com.bpwei.examples.utils.Utils;

/**
 * Created by new on 16/4/13.
 */
public class DownloadStatusProgressBar extends RoundProgressBarWithNumber {

    public static final int STATUS_IDLE = 0;
    public static final int STATUS_WAITING = 1;
    public static final int STATUS_ONGOING = 2;
    public static final int STATUS_DONE = 3;
    public static final int STATUS_FAILED = 4;

    protected int status = STATUS_IDLE;

    protected int btnStatusWidth;
    protected int btnStatusHeight;

    private float startAngle = 0;
    private long timeLast = 0;
    private long timeNew = 0;
    private int timeInterval = 100;
    private boolean firstDraw = true;
    private float deltaAngle = 30;
    private float anglePerSecond = 360;

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
        status = array.getInt(R.styleable.DownloadStatusProgressBar_status, STATUS_IDLE);

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

        drawBase(canvas);
        switch (status) {
            case STATUS_IDLE:
                drawIdle(canvas);
                break;
            case STATUS_WAITING:
                drawWaiting(canvas);
                break;
            case STATUS_ONGOING:
                drawOngoing(canvas);
                break;
            case STATUS_DONE:
                break;
            case STATUS_FAILED:
                break;
        }

        Utils.debug("Size onDraw>> " + btnStatusHeight + " " + btnStatusWidth);

        canvas.restore();
    }

    // 绘制基底
    private void drawBase(Canvas canvas) {
        // 绘制未达到的进度条
        // 防止再次绘制Style被覆盖
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mUnReachedBarColor);
        mPaint.setStrokeWidth(mUnReachedProgressBarHeight);
        // 注意半径、圆心的选择
        canvas.drawCircle(totalWidth / 2, totalWidth / 2, mRadius, mPaint);
    }

    // 绘制空闲状态
    private void drawIdle(Canvas canvas) {
        drawProgress(canvas);

        drawCenterTriangle(canvas);
    }

    // 绘制进行状态
    private void drawOngoing(Canvas canvas) {
        drawProgress(canvas);

        drawCenterRect(canvas);
    }

    // 绘制等待状态
    private void drawWaiting(Canvas canvas) {
        Utils.debug(" drawWaiting !!! ");
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mReachedBarColor);
        mPaint.setStrokeWidth(mReachedProgressBarHeight);

        // 绘制等待标记
        canvas.drawArc(
                new RectF(totalWidth / 2 - mRadius, totalHeight / 2 - mRadius,
                        totalWidth / 2 + mRadius, totalHeight / 2 + mRadius),
                startAngle, deltaAngle, false, mPaint);
    }

    private void drawProgress(Canvas canvas) {
        // 绘制达到的进度条
        mPaint.setColor(mReachedBarColor);
        mPaint.setStrokeWidth(mReachedProgressBarHeight);
        float sweepAngle = getProgress() * 1.0f / getMax() * 360;
        // 关于绘制 drawArc ，得到的结果会fit inside,
        canvas.drawArc(
                new RectF(totalWidth / 2 - mRadius, totalHeight / 2 - mRadius,
                        totalWidth / 2 + mRadius, totalHeight / 2 + mRadius),
                0, sweepAngle, false, mPaint);
    }

    private void drawCenterRect(Canvas canvas) {
        setCenterPaint();
        canvas.drawRect(getRect(), mPaint);
    }

    private void drawCenterTriangle(Canvas canvas) {
        setCenterPaint();
        canvas.drawPath(getTrianglePath(), mPaint);
    }

    private void setCenterPaint() {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mUnReachedBarColor);
        mPaint.setStrokeWidth(mUnReachedProgressBarHeight);
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


    public void setStatus(int status) {
        this.status = status;
        invalidate();
        if (status == STATUS_WAITING) {
            startAngle = 0;
            startTickTock();
        } else {
            stopTickTock();
        }
    }

    public int getStatus() {
        return status;
    }

    private void startTickTock(){
        handlerSimplest.postDelayed(runnable, timeInterval);
    }
    private void stopTickTock(){
        handlerSimplest.removeCallbacks(runnable);
    }
    // 最简单的计时任务
    private Handler handlerSimplest = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            postInvalidate();
            startAngle += timeInterval * (anglePerSecond / 1000);
            startTickTock();
        }
    };
}

