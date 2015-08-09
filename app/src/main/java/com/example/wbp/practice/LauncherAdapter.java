package com.example.wbp.practice;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by new on 15/8/9.
 */
@EBean
public class LauncherAdapter extends BaseAdapter {
	@RootContext
	Context context;

	private List<String> applications;
	public void init(){
		applications = new ArrayList<String>();
	}

	public void setDatas(List<String> datas) {
		applications = datas;
	}
	public List<String> getDatas() {
		return applications;
	}
	@Override
	public int getCount() {
		return applications != null ? applications.size() : 0;
	}

	@Override
	public long getItemId(int pos) {
		return pos;
	}

	@Override
	public Object getItem(int pos) {
		return applications.get(pos);
	}

	@Override
	public View getView(int pos, View view, ViewGroup viewGroup) {
		LauncherAdapterItem item;
		if (null == view) {
			item = LauncherAdapterItem_.build(context);
		} else {
			item = (LauncherAdapterItem) view;
		}
		item.bind(applications.get(pos));

		return item;
	}
}
