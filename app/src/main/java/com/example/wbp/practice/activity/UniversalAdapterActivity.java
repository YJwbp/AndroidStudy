package com.example.wbp.practice.activity;

import android.app.Activity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wbp.practice.R;
import com.example.wbp.practice.bean.UniversalAdapter;
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

	List<String> listDatas = new ArrayList<String>();
	UniversalAdapter<String> adapter;
	@AfterViews
	void afterViews() {
		for (int i = 0; i < 20; i++) {
			listDatas.add(i, "" + i * 2);
		}
		adapter = new UniversalAdapter<String>(this, R.layout.item_text,
				listDatas) {
			@Override
			public void bindItem(ViewHolder holder, String item) {
				TextView textView = holder.getView(R.id.text);
				textView.setText(item);
			}
		};
		listView.setAdapter(adapter);
	}
}
