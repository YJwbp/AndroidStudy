package com.example.wbp.practice;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
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
	private final String PKG_NAME = "com.example.wbp.practice.";

	// 以后添加新的Acitivity只需要在这里加入完整的类名即可
	String [] activities = {"com.example.wbp.practice.VideoPlayAcitivity_", "com.example.wbp.practice.ShapeSelectorActivity_"};

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
