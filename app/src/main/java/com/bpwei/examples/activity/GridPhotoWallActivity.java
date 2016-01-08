package com.bpwei.examples.activity;

import android.widget.GridView;

import com.bpwei.examples.R;
import com.bpwei.examples.adapter.GridPhotoWallAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by new on 15/8/25.
 */
@EActivity(R.layout.activity_grid_photo_wall)
public class GridPhotoWallActivity extends BaseActivity {
	@ViewById(R.id.grid_view)
	GridView gridView;

	GridPhotoWallAdapter adapter;
	List<Integer> datas;

	@AfterViews
	void afterViews() {
		datas = new ArrayList<Integer>();
		for(int i=0;i<30;i++){
			// 相同的图片，加载速度极快 每个Item都是10ms以内加载完成
			datas.add(R.drawable.couple);
		}
		// 不同的图片，加载会慢，有的需要100ms以上
//		datas.add(R.drawable.couple);
//		datas.add(R.drawable.drown_deep);
//		datas.add(R.drawable.couple);
//		datas.add(R.drawable.four);
//		datas.add(R.drawable.han);
//		datas.add(R.drawable.master);
//		datas.add(R.drawable.nazi_and_ailusa);
//		datas.add(R.drawable.nazi);
//		datas.add(R.drawable.han);
//		datas.add(R.drawable.four);
//		datas.add(R.drawable.couple);
//		datas.add(R.drawable.drown_deep);
//		datas.add(R.drawable.couple);
//		datas.add(R.drawable.four);
//		datas.add(R.drawable.han);
//		datas.add(R.drawable.master);
//		datas.add(R.drawable.nazi_and_ailusa);
//		datas.add(R.drawable.nazi);
//		datas.add(R.drawable.han);
//		datas.add(R.drawable.four);
//		datas.add(R.drawable.couple);
//		datas.add(R.drawable.drown_deep);
//		datas.add(R.drawable.couple);
//		datas.add(R.drawable.four);
//		datas.add(R.drawable.han);
//		datas.add(R.drawable.master);
//		datas.add(R.drawable.nazi_and_ailusa);
//		datas.add(R.drawable.nazi);
//		datas.add(R.drawable.han);
//		datas.add(R.drawable.four);
//		datas.add(R.drawable.couple);
//		datas.add(R.drawable.drown_deep);
//		datas.add(R.drawable.couple);
//		datas.add(R.drawable.four);
//		datas.add(R.drawable.han);
//		datas.add(R.drawable.master);
//		datas.add(R.drawable.nazi_and_ailusa);
//		datas.add(R.drawable.nazi);
//		datas.add(R.drawable.han);
//		datas.add(R.drawable.four);

		adapter = new GridPhotoWallAdapter();
		adapter.init(this, datas);
		gridView.setAdapter(adapter);
	}
}
