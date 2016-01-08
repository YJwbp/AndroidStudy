package com.bpwei.examples.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bpwei.examples.R;
import com.bpwei.examples.utils.Utils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by new on 15/8/25.
 */
public class GridPhotoWallAdapter extends BaseAdapter {
	List<Integer> datas;
	Context context;
	public void init(Context context, List<Integer> datas) {
		this.datas = new ArrayList<Integer>();
		this.datas = datas;
		this.context = context;
	}
	@Override
	public int getCount() {
		return this.datas.size();
	}

	@Override
	public Object getItem(int pos) {
		return null;
	}

	@Override
	public long getItemId(int pos) {
		return pos;
	}

	@Override
	public View getView(int pos, View view, ViewGroup viewGroup) {
		ViewHolder holder;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(
					R.layout.item_grid_photo_wall, null);
			holder = new ViewHolder(view);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		long startTime = System.currentTimeMillis();
		holder.imageView.setImageBitmap(decodeSampledBitmapFromResource(context.getResources(),datas.get(pos),100,100));
		long endTime = System.currentTimeMillis();
		long deltaTime = endTime - startTime;
		Utils.debug(deltaTime + "");
		return view;
	}

	class ViewHolder {
		ImageView imageView;
		ViewHolder(View view) {
			this.imageView = (ImageView) view.findViewById(R.id.item_image);
		}
	}

	public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
														 int reqWidth, int reqHeight) {
		// 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);
		// 调用上面定义的方法计算inSampleSize值
		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
		// 使用获取到的inSampleSize值再次解析图片
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(res, resId, options);
	}

	public static int calculateInSampleSize(BitmapFactory.Options options,
											int reqWidth, int reqHeight) {
		// 源图片的高度和宽度
		final int height = options.outHeight;
		final int width = options.outWidth;

		int inSampleSize = 1;
		if (height > reqHeight || width > reqWidth) {
			// 计算出实际宽高和目标宽高的比率
			final int heightRatio = Math.round((float) height / (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			// 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
			// 一定都会大于等于目标的宽和高。
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}
}
