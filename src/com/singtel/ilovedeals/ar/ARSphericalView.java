package com.singtel.ilovedeals.ar;

import android.content.Context;
import android.graphics.Paint;
import android.location.Location;
import android.view.View;

public class ARSphericalView extends View {
	
	public volatile float azimuth;
	public volatile float distance;
	public volatile float inclination = -1;
	public volatile Location location;
	
	public volatile int x;
	public volatile int y;
	public volatile boolean visible = false;
	
	public static Location deviceLocation;
	public static float currentAltitude = 0;
	protected Paint paint = new Paint();

	public ARSphericalView(Context context) {
		super(context);
	}
}
