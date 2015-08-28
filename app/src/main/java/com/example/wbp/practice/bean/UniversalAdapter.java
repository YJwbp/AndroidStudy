package com.example.wbp.practice.bean;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;

import com.example.wbp.practice.R;

import java.util.List;

/**
 * Created by new on 15/8/28.
 */
public abstract class UniversalAdapter<T> extends BaseAdapter {
	protected List<T> mDatas;
	protected Context mContext;
	protected int mItemLayoutId;

	public UniversalAdapter(Context context, int layoutId, List<T> datas) {
		this.mContext = context;
		this.mDatas = datas;
		this.mItemLayoutId = layoutId;
	}
	@Override
	public int getCount() {
		return mDatas.size();
	}

	@Override
	public T getItem(int pos) {
		return mDatas.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return pos;
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {

		ViewHolder holder = ViewHolder.getViewHolder(mContext, convertView,
				parent, mItemLayoutId, pos);
		bindItem(holder, getItem(pos));
		return holder.getConvertView();
	}
	// 通过ViewHolder找到View，通过List内的数据进行设置
	public abstract void bindItem(ViewHolder holder, T item);
}
