package com.insidetip.singtel.map;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public class GPSLocationListener implements LocationListener {
	
	private double latitude;
	private double longitude;

	@Override
	public void onLocationChanged(Location location) {
		latitude = location.getLatitude();
		longitude = location.getLongitude();
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
