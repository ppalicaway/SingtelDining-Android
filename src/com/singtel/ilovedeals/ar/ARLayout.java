package com.singtel.ilovedeals.ar;

import java.util.Enumeration;
import java.util.Vector;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.singtel.ilovedeals.screen.ARScreen;
import com.singtel.ilovedeals.screen.DescriptionPage;
import com.singtel.ilovedeals.util.Util;

public class ARLayout extends View implements LocationListener, SensorEventListener {

	private Context context;
	private final float xAngleWidth = 29;
	private final float yAngleWidth = 19;
	public float screenWidth = Util.getScreenWidth(getContext());
	public float screenHeight = Util.getScreenHeight(getContext());
	private Location lastLocation;
	public static final int contentWidth = 160;
	public static final int contentHeight = 50;
	volatile Vector<ARSphericalView> arViews = new Vector<ARSphericalView>();
	public SensorManager sensorMan;
	public LocationManager locMan;
	public float direction = (float) 22.4;
	public double inclination;
	public double rollingX = (float)0;
	public double rollingZ = (float)0;
	public float kFilteringFactor = (float)0.40;
	public float one = (float)0;
	public float two = (float)0;
	public float three = (float)0;
	public boolean debug = false;
	float previousLocation = (float) -1000;
	float tolerent = (float) 0.25;
	public static Location curLocation = null;
	public static boolean locationChanged = false;
	
	public ARLayout(Context context) {
		super(context);
		this.context = context;
		
		sensorMan = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		sensorMan.registerListener(this, sensorMan.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_FASTEST);
		sensorMan.registerListener(this, sensorMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_FASTEST);
		locMan = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		
		if(locMan.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			locMan.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10, 200, this);
		}
		else if(locMan.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 200, this);
		}
	}

	@Override
	public void onLocationChanged(Location location) {
		if (curLocation == null) {
			curLocation = location;
			ARSphericalView.deviceLocation = location;
			locationChanged = true;
		}
		else if (curLocation.getLatitude() == location.getLatitude() && curLocation.getLongitude() == location.getLongitude()) {
			locationChanged = false;
		}
		else {
			locationChanged = true;
		}
		
		curLocation = location;
		postInvalidate();
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
	}

	@Override
	public void onSensorChanged(SensorEvent evt) {
		float vals[] = evt.values;
		float localDirection;
		
		if(evt.sensor.getType() == Sensor.TYPE_ORIENTATION) {
			float tmp = vals[0];
			
			if(tmp < 0) {
				tmp = tmp + 360;
			}
			
			direction =(float) ((tmp * kFilteringFactor) + (direction * (1.0 - kFilteringFactor)));
			
			if(direction < 0) {
				localDirection = 360 + direction;
			}
			else {
				localDirection = direction;
			}
		}
		
		if(evt.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			rollingZ = (vals[2] * kFilteringFactor) + (rollingZ * (1.0 - kFilteringFactor));
			rollingX = (vals[0] * kFilteringFactor) + (rollingX * (1.0 - kFilteringFactor));
			
			if (rollingZ != 0.0) {
				inclination = Math.atan(rollingX / rollingZ);
			}
			else if (rollingX < 0) {
				inclination = Math.PI / 2.0;
			}
			else if (rollingX >= 0) {
				inclination = 3 * Math.PI / 2.0;
			}
			
			inclination = inclination * (360 / (2 * Math.PI));
			
			if(inclination < 0) {
				inclination = inclination + 90;
			}
			else {
				inclination = inclination - 90;
			}
		}
		
		if(direction < 0) {
			localDirection = 360 + direction;
		}
		else {
			localDirection = direction;
		}
		
		if(Math.abs(localDirection - previousLocation) > tolerent ) {
			previousLocation = localDirection;
			updateLayouts(localDirection, (float)inclination, curLocation);
		}
		
		postInvalidate();
	}
	
	private void sortARViews() {
	}
	
	public void addARView(ARSphericalView view) {
		arViews.add(view);
	}
	
	public void removeARView(ARSphericalView view) {
		arViews.remove(view);
	}
	
	private boolean isVisibleY(float lowerArm, float upperArm, float inc) {
		return true;
	}
	
	public void clearARViews() {
		arViews.removeAllElements();
	}
	
	private boolean isVisibleX(float leftArm, float rightArm, float az) {
		return true;
	}
	
	private  float calcXvalue(float leftArm, float rightArm, float az) {
		float ret = 0;
		float offset;
		
		if(leftArm > rightArm) {
			if(az >= leftArm) {
				offset = az - leftArm;
			}
			
			if(az <= rightArm) {
				offset =  360 - leftArm + az;
			}
			else {
				offset = az - leftArm;
			}
		}
		else {
			offset = az - leftArm;
		}
		
		return (offset / xAngleWidth) * screenWidth;
	}
	
	private float calcYvalue(float lowerArm, float upperArm, float inc) {
		float offset = ((upperArm - yAngleWidth) - inc) * -1;
		return screenHeight - ((offset / yAngleWidth) * screenHeight);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		Enumeration<ARSphericalView> e = arViews.elements();
		
		if(debug) {
			Paint p = new Paint();
			p.setColor(Color.WHITE);
		}
		
		while(e.hasMoreElements()) {
			ARSphericalView view = e.nextElement();
			view.draw(canvas);
		}
	}
	
	public void updateLayouts(float Azi, float zAngle, Location l) {
		float leftArm = Azi - (xAngleWidth / 2);
		float rightArm = Azi + (xAngleWidth / 2);
		
		if(leftArm < 0) {
			leftArm = leftArm + 360;
		}
		
		if(rightArm > 360) {
			rightArm = rightArm - 360;
		}
		
		float upperArm = zAngle + (yAngleWidth / 2);
		float lowerArm = zAngle - (yAngleWidth / 2);
		Enumeration<ARSphericalView> e = arViews.elements();
		
		if(arViews.size() == 0) {
			return;
		}
		
		int count = 0;
		int countElement = 0;
		int xOffset = 90;
		int yOffset = 40;
		int xValue = 0;
		int maxRow = 4;
		
		while(e.hasMoreElements()) {
			try {
				ARSphericalView view = e.nextElement();
				
				if(l != null && view.location != null) {
					view.azimuth = l.bearingTo(view.location);
					
					if (view.azimuth < 0) {
						view.azimuth = 270 + view.azimuth;
					}
					else {
						view.azimuth = view.azimuth + 265;
					}
					
					if(view.azimuth > 360) {
						view.azimuth = view.azimuth - 360;
					}
					
					if(l.hasAltitude() && view.location.hasAltitude()) {
						view.inclination = (float) Math.atan(((view.location.getAltitude() - l.getAltitude()) / l.distanceTo(view.location)));
					}
				}
				
				int x = (int)calcXvalue(leftArm, rightArm, view.azimuth);
				int y = (int)calcYvalue(lowerArm, upperArm, view.inclination);
				x -= 80;
				y -= 40;
				
				view.visible = true;
				if(countElement < maxRow) {
					xValue = (int)calcXvalue(leftArm, rightArm, view.azimuth);
				}
				else if (countElement < (maxRow * 2)) {
					xValue = (int)calcXvalue(leftArm, rightArm, view.azimuth) + 15 + contentWidth;
				}
				else if (countElement < (maxRow * 3)) {
					xValue = (int)calcXvalue(leftArm, rightArm, view.azimuth) + 30 + (contentWidth * 2);
				}
				else {
					xValue = (int)calcXvalue(leftArm, rightArm, view.azimuth) + 45 + (contentWidth * 3);
				}
				
				view.layout(xValue, yOffset, view.getBottom(), view.getRight());
				count++;
				countElement++;
				
				if( count == maxRow) {
					count = 0;
					yOffset = 40;
				}
				else {
					yOffset += 10 + contentHeight;
				}
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		countElement = 0;
	}
	
	public void close() {
		sensorMan.unregisterListener(this);
		locMan.removeUpdates(this);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		switch(action) {
			case MotionEvent.ACTION_DOWN:
				Enumeration<ARSphericalView> e = arViews.elements();
				
				if(arViews.size() == 0) {
					return true;
				}
				
				while (e.hasMoreElements()) {
					ARSphericalView view = e.nextElement();
					FourSqareVenue fv = (FourSqareVenue)view;
					
					if(view.location != null) {
						int start_x = view.getLeft();
						int start_y = view.getTop();
						RectF hitTestRecr = new RectF();
						
						hitTestRecr.set(0, 0, contentWidth, contentHeight);
						hitTestRecr.offset(start_x - contentWidth / 2, start_y - contentHeight / 2);
						
						if(hitTestRecr.contains(event.getX(), event.getY())) {
							DescriptionPage.merchantInfo = fv.merchantInfo;
							DescriptionPage.catID = fv.merchantInfo.getId();
							Intent intent = new Intent(context, DescriptionPage.class);
							ARScreen.instance.startActivityForResult(intent, 0);
							ARScreen.instance.setResult(ARScreen.RESULT_OK);
							return true;
						}
					}
				}
				break;
		}
		return true;
	}
}
