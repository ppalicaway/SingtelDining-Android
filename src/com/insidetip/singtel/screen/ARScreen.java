package com.insidetip.singtel.screen;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.insidetip.singtel.ar.ARLayout;
import com.insidetip.singtel.ar.CustomCameraView;
import com.insidetip.singtel.ar.FourSqareVenue;
import com.insidetip.singtel.ar.GetJSON;
import com.insidetip.singtel.ar.ReturnRes;
import com.insidetip.singtel.ar.SeekBarLayout;
import com.insidetip.singtel.info.MerchantInfo;
import com.insidetip.singtel.util.Util;


public class ARScreen extends SingtelDiningActivity {

	public static ARScreen instance;
	private CustomCameraView cv;
	public static volatile Context ctx;
	private static ARLayout ar;
	private SeekBarLayout seekLayout;
    private LocationManager curLocation;
    public static Location myLocation;
    private Location location;
    public static String cats = "1,2,3";
    public static ArrayList<MerchantInfo> merchantList = null;
	public static ProgressDialog progressDialog = null;
	public static int page = 1;
	public static float radius = (float) 0.8;
	public static String query;
	public static boolean isMerchantList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		instance = this;
		initActivity(this);
		
		ctx = this.getApplicationContext();
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		init();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		init();
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	private void init() {
		ar = new ARLayout(getApplicationContext());
        cv = new CustomCameraView(this.getApplicationContext());
        WindowManager w = getWindowManager();
        Display d = w.getDefaultDisplay();
     	int width = d.getWidth();
     	int height = d.getHeight(); 
        ar.screenHeight = height;
        ar.screenWidth = width;
        FrameLayout rl = new FrameLayout(getApplicationContext());
        rl.addView(cv,width, height);
        ar.debug = true;
        rl.addView(ar, width, height);
        seekLayout = new SeekBarLayout(this);
        rl.addView(seekLayout, width, height);
        setContentView(rl);
        
        curLocation = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        
        if(curLocation.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
        	myLocation = curLocation.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            curLocation.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10, 200, gpsListener);
        }
        else if(curLocation.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
        	myLocation = curLocation.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            curLocation.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 200, gpsListener);
        }
        
        String ipAddress = null;
        try {
        	ipAddress = Util.getLocalIpAddress();
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
        if(ipAddress == null) {
        	Util.showAlert(instance, "AR", "Please make sure Internet connection is available.", getString(R.string.ok), true);
			return;
        }
        
        if(myLocation == null) {
        	Util.showAlert(instance, "AR", "Please enable your GPS or Network Service.", getString(R.string.ok), true);
			return;
        }
        
        ARLayout.curLocation = myLocation;
		ARLayout.locationChanged = true;
		
		if(isMerchantList) {
			merchantList = new ArrayList<MerchantInfo>();
			merchantList = SingtelDiningMainPage.merchantList;
			Thread t = new Thread(new ReturnRes());
			t.start();
		}
		else {
			progressDialog = ProgressDialog.show(this, "", getString(R.string.retrieving), true);
			Thread thread = new Thread(null, new GetJSON(), "initR");
			thread.start();
		}
	}
	
	public static void addMerchant() {
		ar.clearARViews();
		Vector<FourSqareVenue> v = new Vector<FourSqareVenue>();
		FourSqareVenue curVen = null;
		float degree = 0.0f;
		float beringTo = 0.0f;
		float diff = 0.0f;
		float height = 0;
		
		for(int i = 0; i < merchantList.size(); i++) {
			MerchantInfo mInfo = merchantList.get(i);
			
			if(mInfo.getLatitude() != 0.0 && mInfo.getLongitude() != 0.0) {
				curVen = new FourSqareVenue(instance);
				curVen.merchantInfo = mInfo;
				
				curVen.location = new Location("FourSqareApi");
				curVen.location.setLatitude(mInfo.getLatitude());
				curVen.location.setLongitude(mInfo.getLongitude());
				curVen.distance = myLocation.distanceTo(curVen.location);
				curVen.inclination = -8 + height;
				height += (4.3f);
				v.add(curVen);
			}
		}
		
		if (v != null && v.size() > 0) {
			Enumeration<FourSqareVenue> e = v.elements();
			
			while (e.hasMoreElements()) {
				FourSqareVenue fq = (FourSqareVenue) e.nextElement();
				System.out.println("Merchant is: " + fq.merchantInfo.getRestaurantName());
				ar.addARView(fq);
			}
		}
	}
	
	@Override
	protected void onStart() {
		super.onStart();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ARScreen.radius = (float) 0.8;
		cv.closeCamera();
		ar.close();
	}
	
	private LocationListener gpsListener = new LocationListener() {

		@Override
		public void onLocationChanged(Location location) {
			myLocation = location;
		}

		@Override
		public void onProviderDisabled(String arg0) {
		}

		@Override
		public void onProviderEnabled(String arg0) {
		}

		@Override
		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {	
		}
		
	};
	
	protected void onResume() {
		if(curLocation.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
        	curLocation.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10, 200, gpsListener);
        	myLocation = curLocation.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        else if(curLocation.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
        	curLocation.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 200, gpsListener);
        	myLocation = curLocation.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
		super.onResume();
	};
	
	@Override
	protected void onPause() {
		curLocation.removeUpdates(gpsListener);
		cv.closeCamera();
		ar.close();
		super.onPause();
	}
}
