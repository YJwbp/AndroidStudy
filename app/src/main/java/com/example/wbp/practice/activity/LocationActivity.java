package com.example.wbp.practice.activity;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Network;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.wbp.practice.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.ViewById;

/**
 * Created by new on 15/8/27.
 */
@EActivity(R.layout.activity_location)
public class LocationActivity extends Activity {
	@ViewById(R.id.tv_lat)
	TextView tvLat;
	@ViewById(R.id.tv_lont)
	TextView tvLont;
	@ViewById(R.id.tv_others)
	TextView tvOthers;
	@ViewById(R.id.btn_update)
	Button btnUpdate;
	@ViewById(R.id.tv_status)
	TextView tvStatus;
	@SystemService
	LocationManager locationManager;

	LocationListener locationListener;
	Location location;
	String tagLatitude;
	String tagLongtitude;
	String tagOthers;
	@AfterViews
	void afterViews() {
		tagLatitude = getString(R.string.latitude);
		tagLongtitude = getString(R.string.longtitude);
		tagOthers = getString(R.string.others);
		locationListener = new LocationListener() {
			@Override
			public void onLocationChanged(Location location) {
				updateUI(location);
			}

			@Override
			public void onStatusChanged(String s, int i, Bundle bundle) {

			}

			@Override
			public void onProviderEnabled(String provider) {
				tvStatus.setText("Location onProvider Enabled : " + provider);
			}

			@Override
			public void onProviderDisabled(String provider) {
				tvStatus.setText("Location onProvider Disabled : " + provider);
			}
		};
		updateLocation();

	}

	@Click(R.id.btn_update)
	void clickUpdate() {
		// updateLocation();
	}

	private void updateUI(Location location) {
		if (location == null) {
			return;
		}
		tvLat.setText(tagLatitude + location.getLatitude());
		tvLont.setText(tagLongtitude + location.getLongitude());
		tvOthers.setText(tagOthers + "\n 高度：" + location.getAltitude()
				+ "\n 精度：" + location.getAccuracy() + "\n 速度："
				+ location.getSpeed() + "\n 时间：" + location.getTime()
				+ "\n 提供者：" + location.getProvider() + "\n Bearing："
				+ location.getBearing());
	}

	private void updateLocation() {
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
				0, locationListener);
		locationManager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
		locationManager.requestLocationUpdates(
				LocationManager.PASSIVE_PROVIDER, 0, 0, locationListener);
		location = locationManager
				.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
		updateUI(location);
	}
	@Override
	protected void onDestroy() {
		locationManager.removeUpdates(locationListener);
		super.onDestroy();
	}
}
