package com.example.wbp.practice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.wbp.practice.R;

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
		holder.imageView.setImageResource(datas.get(pos));

		return view;
	}

	class ViewHolder {
		ImageView imageView;
		ViewHolder(View view) {
			this.imageView = (ImageView) view.findViewById(R.id.item_image);
		}
	}
}
