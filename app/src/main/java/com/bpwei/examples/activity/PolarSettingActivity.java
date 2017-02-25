package com.bpwei.examples.activity;

import android.content.Intent;
import android.widget.ListView;
import android.widget.TextView;

import com.bpwei.examples.R;
import com.bpwei.examples.bean.UniversalAdapter;
import com.bpwei.examples.bean.UniversalItem;
import com.bpwei.examples.bean.ViewHolder;
import com.bpwei.examples.constant.ExtraInfo;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by new on 16/1/11.
 */
@EActivity(R.layout.activity_polar_setting)
public class PolarSettingActivity extends BaseActivity {

	@ViewById(R.id.lv_circle)
	ListView lvCircle;
	@ViewById(R.id.lv_ray)
	ListView lvRay;
	@ViewById(R.id.tv_title)
	TextView tvTitle;

	private final int CIRCLE_MAX_NUM = 30;
	private final int RAY_MAX_NUM = 120;
	UniversalAdapter<UniversalItem> circleAdapter;
	List<UniversalItem> circleListDatas = new ArrayList<>();

	UniversalAdapter<UniversalItem> rayAdapter;
	List<UniversalItem> rayListDatas = new ArrayList<>();

	@AfterViews
	void afterViews() {
		initAdapter();
		tvTitle.setText(R.string.params_setting);
	}

	@ItemClick(R.id.lv_circle)
	void circleItemClick(int pos) {
		Intent intent = new Intent();
		intent.putExtra(ExtraInfo.CIRCLE_NUMBER,
				Integer.valueOf(circleListDatas.get(pos).getTitle()));
		setResult(RESULT_OK, intent);
		finish();
	}
	@ItemClick(R.id.lv_ray)
	void rayItemClick(int pos) {
		Intent intent = new Intent();
		intent.putExtra(ExtraInfo.RAY_NUMBER,
				Integer.valueOf(rayListDatas.get(pos).getTitle()));
		setResult(RESULT_OK, intent);
		finish();
	}

	@Click(R.id.title_back)
	void backClick(){
		onBackPressed();
	}
	private void initAdapter() {
		for (int i = 1; i < RAY_MAX_NUM / 3+1; i++) {
			UniversalItem item = new UniversalItem();
			item.setTitle(3 * i + "");
			rayListDatas.add(item);
		}

		rayAdapter = new UniversalAdapter<UniversalItem>(this,
				R.layout.item_number, rayListDatas) {
			@Override
			public void bindItem(ViewHolder holder, UniversalItem item) {
				holder.setText(R.id.tv_title, item.getTitle());
			}
		};
		lvRay.setAdapter(rayAdapter);

		for (int i = 1; i < CIRCLE_MAX_NUM+1; i++) {
			if (CIRCLE_MAX_NUM % i != 0) {
				continue;
			}
			UniversalItem item = new UniversalItem();
			item.setTitle(i + "");
			circleListDatas.add(item);
		}

		circleAdapter = new UniversalAdapter<UniversalItem>(this,
				R.layout.item_number, circleListDatas) {
			@Override
			public void bindItem(ViewHolder holder, UniversalItem item) {
				holder.setText(R.id.tv_title, item.getTitle());
			}
		};
		lvCircle.setAdapter(circleAdapter);
	}
}
