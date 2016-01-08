package com.bpwei.examples.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.bpwei.examples.R;
import com.bpwei.examples.adapter.LauncherAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {
	@ViewById(R.id.list_apps)
	ListView listApps;
	@ViewById(R.id.tv_maxMemo)
	TextView tvMaxMemo;
	@Bean
	LauncherAdapter adapter;

	List<String> applications;
	private final String PKG_NAME = "com.bpwei.examples.activity.";

	// 以后添加新的Acitivity只需要在这里加入完整的类名即可
	String [] activities = {"com.bpwei.examples.activity.VideoPlayAcitivity_", "com.bpwei.examples.activity.ShapeSelectorActivity_",
	"com.bpwei.examples.activity.CustomProgressBarActivity_","com.bpwei.examples.activity.CircularImageViewActivity_",
			"com.bpwei.examples.activity.MemoryLeakTestActivity_", "com.bpwei.examples.activity.TestActivity_"
			, "com.bpwei.examples.activity.BezierActivity_", "com.bpwei.examples.activity.CardViewActivity_"
			, "com.bpwei.examples.activity.RecyclerViewActivity_", "com.bpwei.examples.activity.GridPhotoWallActivity_"
			, "com.bpwei.examples.activity.LocationActivity_", "com.bpwei.examples.activity.UniversalAdapterActivity_"
			, "com.bpwei.examples.activity.TextStyleActivity_", "com.bpwei.examples.activity.FallPhotosActivity_"};

	@AfterViews
	void afterViews() {
		int maxMemory = (int)Runtime.getRuntime().maxMemory() / 1024;
		String maxMemo = String.format(getString(R.string.max_memo),maxMemory+"");
		tvMaxMemo.setText(maxMemo);
		initLancher();
		adapter.init();
		adapter.setDatas(applications);

		listApps.setAdapter(adapter);
		listApps.setFooterDividersEnabled(true);
		listApps.setHeaderDividersEnabled(true);
		listApps.setOnItemClickListener(new AdapterView.OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				start(i);
			}
		});
	}

	private void initLancher() {
		applications = new ArrayList<String>();
		for(int i=0;i<activities.length;i++){
			String str = activities[i].substring(PKG_NAME.length());
			applications.add(str);
		}

	}

	private void start(int pos){

		Class classToStart;
		try{
			classToStart = Class.forName(activities[pos]);
		}
		catch (Exception e){
			e.printStackTrace();
			classToStart = ShapeSelectorActivity_.class;
		}

		Intent intent = new Intent(this, classToStart);
		startActivity(intent);
		overridePendingTransition(android.R.anim.fade_in,
				android.R.anim.fade_out);
	}
}
