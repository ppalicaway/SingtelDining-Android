package com.singtel.ilovedeals.screen;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.maps.MapActivity;
import com.singtel.ilovedeals.map.GPSLocationListener;
import com.singtel.ilovedeals.util.Util;

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
		
		LinearLayout controls = (LinearLayout)findViewById(R.id.controlLayout);
		LinearLayout addressLayout = (LinearLayout)findViewById(R.id.addressTextLayout);
		
		if(SingtelDiningMainPage.isListing) {
			controls.setVisibility(LinearLayout.GONE);
			addressLayout.setVisibility(LinearLayout.GONE);
		}
		
		TextView address = (TextView) findViewById(R.id.addressTextView);
		try {
			address.setText(DescriptionPage.merchantDetails.getAddress());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Button directions = (Button)findViewById(R.id.directionsButton);
		directions.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if((DescriptionPage.merchantInfo.getLatitude() != 0.00 && DescriptionPage.merchantInfo.getLongitude() != 0.00)) {
					Intent navigation = new Intent(Intent.ACTION_VIEW, 
							Uri.parse("http://maps.google.com/maps?saddr="+ Util.latitude+"," + Util.longitude +
									"&daddr="+DescriptionPage.merchantInfo.getLatitude()+","+
									DescriptionPage.merchantInfo.getLongitude()));
					startActivity(navigation);
				}
			}
		});
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
