package com.bpwei.examples.activity;

import android.widget.ListView;

import com.bpwei.examples.R;
import com.bpwei.examples.bean.UniversalAdapter;
import com.bpwei.examples.bean.UniversalItem;
import com.bpwei.examples.bean.ViewHolder;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * Created by new on 15/8/28.
 */
@EActivity(R.layout.universal_adapter_activity)
public class UniversalAdapterActivity extends BaseActivity {
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
