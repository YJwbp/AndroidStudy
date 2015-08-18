//package com.example.wbp.practice.views;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.graphics.PorterDuff;
//import android.graphics.PorterDuffXfermode;
//import android.graphics.RectF;
//import android.util.AttributeSet;
//import android.util.TypedValue;
//import android.view.View;
//
//import com.example.wbp.practice.R;
//
///**
// * Created by new on 15/8/14.
// */
//public class CustomImageView extends View
//{
//
//	/**
//	 * TYPE_CIRCLE / TYPE_ROUND
//	 */
//	private int type;
//	private static final int TYPE_CIRCLE = 0;
//	private static final int TYPE_ROUND = 1;
//
//	/**
//	 * Í¼Æ¬
//	 */
//	private Bitmap mSrc;
//
//	/**
//	 * Ô²½ÇµÄ´óÐ¡
//	 */
//	private int mRadius;
//
//	/**
//	 * ¿Ø¼þµÄ¿í¶È
//	 */
//	private int mWidth;
//	/**
//	 * ¿Ø¼þµÄ¸ß¶È
//	 */
//	private int mHeight;
//
//	public CustomImageView(Context context, AttributeSet attrs)
//	{
//		this(context, attrs, 0);
//	}
//
//	public CustomImageView(Context context)
//	{
//		this(context, null);
//	}
//
//	/**
//	 * ³õÊ¼»¯Ò»Ð©×Ô¶¨ÒåµÄ²ÎÊý
//	 *
//	 * @param context
//	 * @param attrs
//	 * @param defStyle
//	 */
//	public CustomImageView(Context context, AttributeSet attrs, int defStyle)
//	{
//		super(context, attrs, defStyle);
//
//		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
//				R.styleable.CustomImageView, defStyle, 0);
//
//		int n = a.getIndexCount();
//		for (int i = 0; i < n; i++)
//		{
//			int attr = a.getIndex(i);
//			switch (attr)
//			{
//				case R.styleable.CustomImageView_src:
//					mSrc = BitmapFactory.decodeResource(getResources(),
//							a.getResourceId(attr, 0));
//					break;
//				case R.styleable.CustomImageView_type:
//					type = a.getInt(attr, 0);// Ä¬ÈÏÎªCircle
//					break;
//				case R.styleable.CustomImageView_borderRadius:
//					mRadius = a.getDimensionPixelSize(attr, (int) TypedValue
//							.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f,
//									getResources().getDisplayMetrics()));// Ä¬ÈÏÎª10DP
//					break;
//			}
//		}
//		a.recycle();
//	}
//
//	/**
//	 * ¼ÆËã¿Ø¼þµÄ¸ß¶ÈºÍ¿í¶È
//	 */
//	@Override
//	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
//	{
//		// super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//		/**
//		 * ÉèÖÃ¿í¶È
//		 */
//		int specMode = MeasureSpec.getMode(widthMeasureSpec);
//		int specSize = MeasureSpec.getSize(widthMeasureSpec);
//
//		if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate
//		{
//			mWidth = specSize;
//		} else
//		{
//			// ÓÉÍ¼Æ¬¾ö¶¨µÄ¿í
//			int desireByImg = getPaddingLeft() + getPaddingRight()
//					+ mSrc.getWidth();
//			if (specMode == MeasureSpec.AT_MOST)// wrap_content
//			{
//				mWidth = Math.min(desireByImg, specSize);
//			} else
//
//				mWidth = desireByImg;
//		}
//
//		/***
//		 * ÉèÖÃ¸ß¶È
//		 */
//
//		specMode = MeasureSpec.getMode(heightMeasureSpec);
//		specSize = MeasureSpec.getSize(heightMeasureSpec);
//		if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate
//		{
//			mHeight = specSize;
//		} else
//		{
//			int desire = getPaddingTop() + getPaddingBottom()
//					+ mSrc.getHeight();
//
//			if (specMode == MeasureSpec.AT_MOST)// wrap_content
//			{
//				mHeight = Math.min(desire, specSize);
//			} else
//				mHeight = desire;
//		}
//
//		setMeasuredDimension(mWidth, mHeight);
//
//	}
//
//	/**
//	 * »æÖÆ
//	 */
//	@Override
//	protected void onDraw(Canvas canvas)
//	{
//		switch (type)
//		{
//			// Èç¹ûÊÇTYPE_CIRCLE»æÖÆÔ²ÐÎ
//			case TYPE_CIRCLE:
//				int min = Math.min(mWidth, mHeight);
//				/**
//				 * ³¤¶ÈÈç¹û²»Ò»ÖÂ£¬°´Ð¡µÄÖµ½øÐÐÑ¹Ëõ
//				 */
//				mSrc = Bitmap.createScaledBitmap(mSrc, min, min, false);
//				canvas.drawBitmap(createCircleImage(mSrc, min), 0, 0, null);
//				break;
//			case TYPE_ROUND:
//				canvas.drawBitmap(createRoundConerImage(mSrc), 0, 0, null);
//				break;
//
//		}
//
//	}
//
//	/**
//	 * ¸ù¾ÝÔ­Í¼ºÍ±ä³¤»æÖÆÔ²ÐÎÍ¼Æ¬
//	 *
//	 * @param source
//	 * @param min
//	 * @return
//	 */
//	private Bitmap createCircleImage(Bitmap source, int min)
//	{
//		final Paint paint = new Paint();
//		paint.setAntiAlias(true);
//		Bitmap target = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
//		/**
//		 * ²úÉúÒ»¸öÍ¬Ñù´óÐ¡µÄ»­²¼
//		 */
//		Canvas canvas = new Canvas(target);
//		/**
//		 * Ê×ÏÈ»æÖÆÔ²ÐÎ
//		 */
//		canvas.drawCircle(min / 2, min / 2, min / 2, paint);
//		/**
//		 * Ê¹ÓÃSRC_IN£¬²Î¿¼ÉÏÃæµÄËµÃ÷
//		 */
//		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//		/**
//		 * »æÖÆÍ¼Æ¬
//		 */
//		canvas.drawBitmap(source, 0, 0, paint);
//		return target;
//	}
//
//	/**
//	 * ¸ù¾ÝÔ­Í¼Ìí¼ÓÔ²½Ç
//	 *
//	 * @param source
//	 * @return
//	 */
//	private Bitmap createRoundConerImage(Bitmap source)
//	{
//		final Paint paint = new Paint();
//		paint.setAntiAlias(true);
//		Bitmap target = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
//		Canvas canvas = new Canvas(target);
//		RectF rect = new RectF(0, 0, source.getWidth(), source.getHeight());
//		canvas.drawRoundRect(rect, mRadius, mRadius, paint);
//		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//		canvas.drawBitmap(source, 0, 0, paint);
//		return target;
//	}
//}