package com.bpwei.examples.activity;

import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.bpwei.examples.MainApp;
import com.bpwei.examples.MainApp_;
import com.bpwei.examples.R;
import com.bpwei.examples.bean.UniversalAdapter;
import com.bpwei.examples.bean.UniversalItem;
import com.bpwei.examples.bean.ViewHolder;
import com.bpwei.examples.entites.PolarPoint;
import com.bpwei.examples.sp.ScreenSP_;
import com.bpwei.examples.views.PolarCoordinateView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by new on 16/1/10.
 */
@EActivity(R.layout.layout_polar_coordinate)
public class PolarCoordinateActivity extends BaseActivity {

	@ViewById(R.id.v_polar_coordinate)
	PolarCoordinateView polarCoordinateView;
	@ViewById(R.id.lv_coordinates)
	ListView lvCoordinates;

	@App
	MainApp app;
	@Pref
	ScreenSP_ screenSP;

	UniversalAdapter<UniversalItem> adapter;
	List<UniversalItem> listDatas = new ArrayList<>();

	@AfterViews
	void afterViews() {
		RelativeLayout.LayoutParams rlParams = (RelativeLayout.LayoutParams) polarCoordinateView.getLayoutParams();
		rlParams.height = screenSP.width().get();

		polarCoordinateView.setLayoutParams(rlParams);
	}

	@Click(R.id.tv_clear)
	void clearClick() {
		polarCoordinateView.clearPolarMap();
		app.toast(R.string.clear);
	}

	@Click(R.id.tv_check)
	void checkClick() {
		listDatas.clear();
		List<PolarPoint> polarPoints = polarCoordinateView.getSelecetedPoints();
		for (int i = 0; i < polarPoints.size(); i++) {
			UniversalItem item = new UniversalItem();
			item.setTitle(i + ": ");
			item.setDesc("(" + polarPoints.get(i).getRadius() + ", " + polarPoints.get(i).getAngle() + ")");
			listDatas.add(item);
		}

		adapter = new UniversalAdapter<UniversalItem>(this,
				R.layout.item_coordinate, listDatas) {
			@Override
			public void bindItem(ViewHolder holder, UniversalItem item) {
				holder.setText(R.id.tv_title, item.getTitle());
				holder.setText(R.id.tv_describe, item.getDesc());
			}
		};

		lvCoordinates.setAdapter(adapter);

		showHideCoordinateList(true);
	}

	@Click(R.id.title_back)
	void backClick() {
		showHideCoordinateList(false);
	}

	@Click(R.id.tv_send)
	void sendClick() {
		List<PolarPoint> polarPoints = polarCoordinateView.getSelecetedPoints();
		app.toast(polarPoints.toString());
	}

	private void showHideCoordinateList(boolean show) {
		polarCoordinateView.setVisibility(show ? View.GONE : View.VISIBLE);
		lvCoordinates.setVisibility(show ? View.VISIBLE : View.GONE);
	}
}
