package com.example.wbp.practice.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.wbp.practice.R;

/**
 * Created by new on 15/8/12.
 */
public class CircularImageView extends ImageView {

	public CircularImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	public CircularImageView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
	}

	@Override
	protected void onDraw(Canvas canvas) {

		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.sss).copy(Bitmap.Config.ARGB_8888, true);

//		Canvas canvas1 = new Canvas(bitmap);
		Paint paint = new Paint();
		paint.setColor(Color.GREEN);
		paint.setStyle(Paint.Style.FILL);
		paint.setStrokeWidth(3.0f);
		canvas.drawBitmap(bitmap, 0, 0, paint);
		paint.setAntiAlias(true); // 去锯齿
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));

		int radius = bitmap.getWidth() / 4;

		canvas.drawCircle(radius, radius, radius, paint);

		paint.setXfermode(null);//
//		canvas.drawBitmap(bitmap, 0, 0, paint);

		// // shader成功
		// Bitmap src = BitmapFactory.decodeResource(getResources(),
		// R.drawable.sss).copy(Bitmap.Config.ARGB_8888, true);
		// int radius = src.getWidth() / 4;
		// BitmapShader bitmapShader = new BitmapShader(src,
		// Shader.TileMode.REPEAT,
		// Shader.TileMode.REPEAT);
		// Paint paint = new Paint();
		// paint.setAntiAlias(true);
		// paint.setShader(bitmapShader);
		// canvas.drawCircle(radius,radius, radius, paint);

		//
	}

}
