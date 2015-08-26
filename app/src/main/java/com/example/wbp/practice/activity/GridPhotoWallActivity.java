package com.example.wbp.practice.activity;

import android.app.Activity;
import android.widget.GridView;

import com.example.wbp.practice.R;
import com.example.wbp.practice.adapter.GridPhotoWallAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by new on 15/8/25.
 */
@EActivity(R.layout.activity_grid_photo_wall)
public class GridPhotoWallActivity extends Activity {
	@ViewById(R.id.grid_view)
	GridView gridView;

	GridPhotoWallAdapter adapter;
	List<Integer> datas;

	@AfterViews
	void afterViews() {
		datas = new ArrayList<Integer>();
		datas.add(R.drawable.couple);
		datas.add(R.drawable.drown_deep);
		datas.add(R.drawable.couple);
		datas.add(R.drawable.four);
		datas.add(R.drawable.han);
		datas.add(R.drawable.master);
		datas.add(R.drawable.nazi_and_ailusa);
		datas.add(R.drawable.nazi);
		datas.add(R.drawable.han);
		datas.add(R.drawable.four);
		datas.add(R.drawable.couple);
		datas.add(R.drawable.drown_deep);
		datas.add(R.drawable.couple);
		datas.add(R.drawable.four);
		datas.add(R.drawable.han);
		datas.add(R.drawable.master);
		datas.add(R.drawable.nazi_and_ailusa);
		datas.add(R.drawable.nazi);
		datas.add(R.drawable.han);
		datas.add(R.drawable.four);
		datas.add(R.drawable.couple);
		datas.add(R.drawable.drown_deep);
		datas.add(R.drawable.couple);
		datas.add(R.drawable.four);
		datas.add(R.drawable.han);
		datas.add(R.drawable.master);
		datas.add(R.drawable.nazi_and_ailusa);
		datas.add(R.drawable.nazi);
		datas.add(R.drawable.han);
		datas.add(R.drawable.four);
		datas.add(R.drawable.couple);
		datas.add(R.drawable.drown_deep);
		datas.add(R.drawable.couple);
		datas.add(R.drawable.four);
		datas.add(R.drawable.han);
		datas.add(R.drawable.master);
		datas.add(R.drawable.nazi_and_ailusa);
		datas.add(R.drawable.nazi);
		datas.add(R.drawable.han);
		datas.add(R.drawable.four);

		adapter = new GridPhotoWallAdapter();
		adapter.init(this, datas);
		gridView.setAdapter(adapter);
	}
}
