package com.example.wbp.practice.activity;

import android.app.Activity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wbp.practice.R;
import com.example.wbp.practice.bean.UniversalAdapter;
import com.example.wbp.practice.bean.UniversalItem;
import com.example.wbp.practice.bean.ViewHolder;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by new on 15/8/28.
 */
@EActivity(R.layout.universal_adapter_activity)
public class UniversalAdapterActivity extends Activity {
	@ViewById(R.id.list)
	ListView listView;

	UniversalAdapter<UniversalItem> adapter;
	@AfterViews
	void afterViews() {

		List<UniversalItem> listDatas = new UniversalItem()
				.getUniversalItems(20);

		adapter = new UniversalAdapter<UniversalItem>(this,
				R.layout.item_complex, listDatas) {
			@Override
			public void bindItem(ViewHolder holder, UniversalItem item) {
				holder.setText(R.id.tv_title, item.getTitle());
				holder.setText(R.id.tv_describe, item.getDesc());
				holder.setText(R.id.tv_time, item.getTime());
				holder.setText(R.id.tv_phone, item.getPhone());
			}
		};
		listView.setAdapter(adapter);
	}
}
