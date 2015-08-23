package com.example.wbp.practice.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wbp.practice.R;
import com.example.wbp.practice.views.CircularImageView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.InputStream;

/**
 * Created by new on 15/8/12.
 */
@EActivity(R.layout.activity_draw)
public class CircularImageViewActivity extends Activity {
	@ViewById(R.id.circular_image_view)
	CircularImageView circularImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


	}

	@AfterInject
	void afterViews(){


	}
	@Click(R.id.circular_image_view)
	void click(){
		Toast.makeText(this,"dianji",Toast.LENGTH_SHORT).show();
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sss).copy(Bitmap.Config.ARGB_8888, true);
		Paint p = new Paint();
		p.setAntiAlias(true); //去锯齿
		p.setColor(Color.BLACK);
		p.setStyle(Paint.Style.STROKE);
		Canvas canvas = new Canvas(bitmap);  //bitmap就是我们原来的图,比如头像
		p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));  //因为我们先画了图所以DST_IN
		int radius = bitmap.getWidth()/4; //假设图片是正方形的
		canvas.drawCircle(radius, radius, radius, p);
		circularImageView.draw(canvas);
		circularImageView.invalidate();
	}

}
