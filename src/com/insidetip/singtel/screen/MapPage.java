package com.insidetip.singtel.screen;

import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Window;

import com.google.android.maps.MapActivity;
import com.insidetip.singtel.map.GPSLocationListener;

public class MapPage extends MapActivity {
	
	public static MapPage instance;
	private LocationManager locationManager;
	private GPSLocationListener gpsLocationListener;
	
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setTheme(R.style.Theme_Translucent);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.map);
		instance = this;
		init();
	}

	private void init() {
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		if(gpsLocationListener == null) {
			gpsLocationListener = new GPSLocationListener();
		}
		
		if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100, 200, gpsLocationListener);
		}
		else if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 200, gpsLocationListener);
		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	@Override
	protected void onResume() {
		if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100, 200, gpsLocationListener);
		}
		else if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 200, gpsLocationListener);
		}
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		locationManager.removeUpdates(gpsLocationListener);
		super.onPause();
	}
}
