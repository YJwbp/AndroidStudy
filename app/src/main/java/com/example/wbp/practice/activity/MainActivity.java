package com.example.wbp.practice.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.wbp.practice.adapter.LauncherAdapter;
import com.example.wbp.practice.R;
import com.example.wbp.practice.activity.ShapeSelectorActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {
	@ViewById(R.id.list_apps)
	ListView listApps;
	@Bean
	LauncherAdapter adapter;

	List<String> applications;
	private final String PKG_NAME = "com.example.wbp.practice.activity.";

	// 以后添加新的Acitivity只需要在这里加入完整的类名即可
	String [] activities = {"com.example.wbp.practice.activity.VideoPlayAcitivity_", "com.example.wbp.practice.activity.ShapeSelectorActivity_"};

	@AfterViews
	void afterViews() {
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
	}
}
