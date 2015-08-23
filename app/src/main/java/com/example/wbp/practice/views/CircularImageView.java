package com.example.wbp.practice.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
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
		// shader成功
		Bitmap src = BitmapFactory.decodeResource(getResources(),
				R.drawable.gaara).copy(Bitmap.Config.ARGB_8888, true);
		int radius = src.getWidth() / 4;
		BitmapShader bitmapShader = new BitmapShader(src,
				Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setShader(bitmapShader);
		canvas.drawCircle(radius, radius, radius, paint);
	}

}
