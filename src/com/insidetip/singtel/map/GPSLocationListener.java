package com.insidetip.singtel.map;

import java.text.DecimalFormat;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.insidetip.singtel.screen.R;
import com.insidetip.singtel.screen.SingtelDiningMainPage;
import com.insidetip.singtel.util.Util;

public class GPSLocationListener implements LocationListener {
	
	private double latitude;
	private double longitude;

	@Override
	public void onLocationChanged(Location location) {
		latitude = location.getLatitude();
		longitude = location.getLongitude();
		
		if(MapLocationViewer.useCurrentGPS) {
			DecimalFormat twoDForm = new DecimalFormat("#.####");
			String latStr = Double.valueOf(twoDForm.format(latitude)).toString();
			String lngStr = Double.valueOf(twoDForm.format(longitude)).toString();
			
			Activity activity = (Activity) SingtelDiningMainPage.instance;
			MapLocationInfo mLocation = new MapLocationInfo(Util.getGPSAddress(activity, latitude, longitude), "Latitude: " + latStr + " Longitude: " + lngStr, latitude, longitude, R.drawable.pin_red, null);
			MapLocationViewer.setMapLocation(mLocation, 0, MapLocationViewer.useCurrentGPS);
			GeoPoint point = new GeoPoint((int) (latitude * 1e6), (int) (longitude * 1e6));
			MapLocationViewer.mapView.getController().setCenter(point);
			
		}
		
		System.out.println("Latitude Has Changed: " + location.getLatitude());
		System.out.println("Longitude Has Changed: " + location.getLongitude());
	}

	@Override
	public void onProviderDisabled(String provider) {
		System.out.println("Service " + provider + " is Disabled");
	}

	@Override
	public void onProviderEnabled(String provider) {
		System.out.println("Service " + provider + " is Enabled");
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle bundle) {
		if(status == 0) {
			System.out.println(provider + " is Out of Service");
		}
		else if(status == 1) {
			System.out.println(provider + " is Temporarily Unavailable");
		}
		else if (status == 2) {
			System.out.println(provider + " is Available");
		}
	}

}
