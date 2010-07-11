package com.insidetip.singtel.ar;

public class SphericalPoint {

	public float azimuth;
	public float distance;
	public float inclination;
	public static float currentAltitude = 0;
	
	public SphericalPoint(float angleFromNorth, float distance, float altitude) {
		this.distance = distance;
		float opposite;
		boolean negative = false;
		
		if(altitude > currentAltitude) {
			opposite = altitude - currentAltitude;
		}
		else {
			opposite = currentAltitude - altitude;
			negative = true;
		}
		
		inclination = (float) Math.atan(((double)opposite/distance));
		
		if(negative) {
			opposite = opposite * -1;
		}
	}
}
