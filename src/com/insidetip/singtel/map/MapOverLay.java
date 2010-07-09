package com.insidetip.singtel.map;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class MapOverLay extends Overlay {

	private GeoPoint geopoint1;
	private GeoPoint geopoint2;
	private int radius = 6;
	private int mode = 0;
	private int defaultColor;
	
	public MapOverLay(GeoPoint geopoint1, GeoPoint geopoint2, int mode) {
		this.geopoint1 = geopoint1;
		this.geopoint2 = geopoint2;
		this.mode = mode;
		defaultColor = 999;
	}
	
	public MapOverLay(GeoPoint geopoint1, GeoPoint geopoint2, int mode, int defaultColor) {
		this.geopoint1 = geopoint1;
		this.geopoint2 = geopoint2;
		this.mode = mode;
		this.defaultColor = defaultColor;
	}
	
	public int getMode() {
		return this.mode;
	}
	
	@Override
	public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
		Projection projection = mapView.getProjection();
		if (shadow == false) {
			Paint paint = new Paint();
			paint.setAntiAlias(true);
			Point point = new Point();
			projection.toPixels(geopoint1, point);
			
			if(mode == 1) {
				if(defaultColor == 999) {
					paint.setColor(Color.BLUE);
				}
				else {
					paint.setColor(defaultColor);
				}
				
				RectF oval = new RectF(point.x - radius, point.y - radius, point.x + radius, point.y + radius);
				canvas.drawOval(oval, paint);
			}
			else if(mode == 2) {
				if(defaultColor == 999) {
					paint.setColor(Color.RED);
				}
				else {
					paint.setColor(defaultColor);
				}
				Point point2 = new Point();
				projection.toPixels(geopoint2, point2);
				paint.setStrokeWidth(5);
				paint.setAlpha(120);
				canvas.drawLine(point.x, point.y, point2.x, point2.y, paint);
			}
			else if(mode == 3) {
				if (defaultColor == 999) {
					paint.setColor(Color.GREEN);
				}
				else {
					paint.setColor(defaultColor);
				}
				Point point2 = new Point();
				projection.toPixels(geopoint2, point2);
				paint.setStrokeWidth(5);
				paint.setAlpha(120);
				canvas.drawLine(point.x, point.y, point2.x, point2.y, paint);
				RectF oval = new RectF(point2.x - radius, point2.y - radius, point2.x + radius, point2.y + radius);
				paint.setAlpha(255);
				canvas.drawOval(oval, paint);
			}
		}
		return super.draw(canvas, mapView, shadow, when);
	}
}
