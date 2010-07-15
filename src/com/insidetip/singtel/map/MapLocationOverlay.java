package com.insidetip.singtel.map;

import java.util.Iterator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.Paint.Style;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;
import com.insidetip.singtel.screen.DescriptionPage;
import com.insidetip.singtel.screen.R;
import com.insidetip.singtel.screen.SingtelDiningMainPage;
import com.insidetip.singtel.util.Util;

public class MapLocationOverlay extends Overlay {

	private Context context;
	private Bitmap defaultPin, currentPin;
	private MapLocationViewer mapLocationViewer;
	private Paint innerPaint, borderPaint, textPaint;
	private MapLocationInfo selectedMapLocation;
	private GeoPoint gp1;
	private GeoPoint gp2;
	private int mRadius = 6;
	private int mode = 0;
	private int defaultColor;
	private boolean isShowRoute;
	private Path routePath;
    private int elemWidth, elemHeight;
    private int paddingTop;
    private Canvas canvas = new Canvas();
    private int infoWindowOffsetX;
    int infoWindowOffsetY;
    
    public MapLocationOverlay(Context context, MapLocationViewer mapLocationViewer, boolean isShowRoute, int paddingTop) {
    	
    	this.context = context;
		this.mapLocationViewer = mapLocationViewer;
		this.paddingTop = paddingTop;
		
		currentPin = BitmapFactory.decodeResource(mapLocationViewer.getResources(),R.drawable.pin_red);
		defaultPin = BitmapFactory.decodeResource(mapLocationViewer.getResources(),R.drawable.pin_green);
		
		elemWidth = Util.getScreenWidth(context);
		elemHeight = 20*4;
    }
    
    public MapLocationOverlay(GeoPoint gp1, GeoPoint gp2, int mode, int defaultColor, boolean isShowRoute) {
    	this.gp1 = gp1;
		this.gp2 = gp2;
		this.mode = mode;
		this.defaultColor = defaultColor;
		this.isShowRoute = isShowRoute;
    }
    
    public MapLocationOverlay(Path routePath, int defaultColor, boolean isShowRoute) {
    	this.routePath = routePath;
		this.defaultColor = defaultColor;
		this.isShowRoute = isShowRoute;
    }
    
    private void nextScreen() {
		DescriptionPage.merchantInfo = selectedMapLocation.getMerchantInfo();
		DescriptionPage.catID = selectedMapLocation.getMerchantInfo().getId();
		Intent intent = new Intent(context, DescriptionPage.class);
	    ((Activity) context).startActivityForResult(intent, 0);
	}
    
    @Override
    public boolean onTap(GeoPoint p, MapView mapView) {
    	boolean isRemovePriorPopup = selectedMapLocation != null;
    	if(selectedMapLocation != null) {
    		if(getHitDescription(mapView, p) && selectedMapLocation.getMerchantInfo() != null) {
    			nextScreen();
    		}
    	}
    	selectedMapLocation = getHitMapLocation(mapView,p);
    	if (isRemovePriorPopup || selectedMapLocation != null) {
			mapView.invalidate();
		}
    	
    	return selectedMapLocation != null;
    }
    
    @Override
    public void draw(Canvas canvas, MapView mapView, boolean shadow) {
    	this.canvas = canvas;
    	if(isShowRoute) {
    		drawMapRoute(canvas, mapView, shadow);
    	}
    	else {
    		drawMapPointLocations(canvas, mapView, shadow);
   	   		drawMapPointDescription(canvas, mapView, shadow);
    	}
    }
    
    private boolean getHitDescription(MapView mapView, GeoPoint	tapPoint) {
    	RectF hitTestRecr = new RectF();
    	Point screenCoords = new Point();
    	int start_y = Util.getScreenHeight(SingtelDiningMainPage.instance) - elemHeight-80 - paddingTop;
    	
    	hitTestRecr.set(0, 0, elemWidth, elemHeight);
    	hitTestRecr.offset(infoWindowOffsetX-34, infoWindowOffsetY-20);
    	mapView.getProjection().toPixels(tapPoint, screenCoords);
    	
    	if (hitTestRecr.contains(screenCoords.x, screenCoords.y)) {
    		return true;
    	}
    	
    	return false;
    }
    
    private MapLocationInfo getHitMapLocation(MapView mapView, GeoPoint	tapPoint) {
    	MapLocationInfo hitMapLocation = null;
    	
    	RectF hitTestRecr = new RectF();
    	Point screenCoords = new Point();
    	Iterator<MapLocationInfo> iterator = null;
    	
    	try {
    		iterator = mapLocationViewer.getMapLocations().iterator();
    	}
    	catch(Exception e) {
    		return null;
    	}
    	
    	while(iterator.hasNext()) {
    		MapLocationInfo testLocation = iterator.next();
    		mapView.getProjection().toPixels(testLocation.getPoint(), screenCoords);
    		hitTestRecr.set(-defaultPin.getWidth()/2,-defaultPin.getHeight(),defaultPin.getWidth()/2,0);
    		hitTestRecr.offset(screenCoords.x, screenCoords.y);
    		mapView.getProjection().toPixels(tapPoint, screenCoords);
    		
    		if (hitTestRecr.contains(screenCoords.x, screenCoords.y)) {
    			hitMapLocation = testLocation;
    			break;
    		}
    	}
    	
    	tapPoint = null;
    	return hitMapLocation;
    }
    
    private void drawMapPointLocations(Canvas canvas, MapView mapView, boolean shadow) {
    	Iterator<MapLocationInfo> iterator = mapLocationViewer.getMapLocations().iterator();
    	Point screenCoords = new Point();
    	
    	while(iterator.hasNext()) {
    		MapLocationInfo location = iterator.next();
    		mapView.getProjection().toPixels(location.getPoint(), screenCoords);
    		
    		if (shadow) {
    		}
    		else {
    			if(location.isCurrentPost()) {
    				canvas.drawBitmap(currentPin, screenCoords.x - currentPin.getWidth()/2, screenCoords.y - currentPin.getHeight(),null);
    			}
    			else {
    				Bitmap pin = BitmapFactory.decodeResource(mapLocationViewer.getResources(), location.getrId());
    				canvas.drawBitmap(pin, screenCoords.x - defaultPin.getWidth()/2, screenCoords.y - defaultPin.getHeight(),null);
    			}
    		}
    	}
    }
    
    private void drawMapPointDescription(Canvas canvas, MapView	mapView, boolean shadow) {
    	if ( selectedMapLocation != null) {
    		if ( shadow) {
    		}
    		else {
    			Point selDestinationOffset = new Point();
    			mapView.getProjection().toPixels(selectedMapLocation.getPoint(), selDestinationOffset);
    			int INFO_WINDOW_WIDTH = 150;
				int INFO_WINDOW_HEIGHT = 30;
				RectF infoWindowRect = new RectF(0,0,INFO_WINDOW_WIDTH,INFO_WINDOW_HEIGHT);
				infoWindowOffsetX = selDestinationOffset.x-INFO_WINDOW_WIDTH/2;
				infoWindowOffsetY = selDestinationOffset.y-INFO_WINDOW_HEIGHT-defaultPin.getHeight();
				int start_y = Util.getScreenHeight(SingtelDiningMainPage.instance) - elemHeight-80 - paddingTop;
				infoWindowRect.offset(infoWindowOffsetX, infoWindowOffsetY);
				
				int TEXT_OFFSET_X = -19;
				int TEXT_OFFSET_Y = 2;
				Bitmap bm = BitmapFactory.decodeResource(SingtelDiningMainPage.instance.getResources(), R.drawable.bubble);
				canvas.drawBitmap(bm, infoWindowOffsetX-34, infoWindowOffsetY-20, null);
				
				String title = selectedMapLocation.getTitle();
				
				if(title.length() > 23) {
					title = title.substring(0, 23) + "...";
				}
				
				canvas.drawText(title, infoWindowOffsetX+TEXT_OFFSET_X, infoWindowOffsetY+TEXT_OFFSET_Y, getTextPaint());
    		}
    	}
    }
    
    public void drawMapRoute(Canvas canvas, MapView mapView, boolean shadow) {
    	Projection projection = mapView.getProjection();
    	if (shadow == false) {
    		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
			PathEffect pEffect =  new CornerPathEffect(10);
			paint.setStyle(Paint.Style.STROKE);
			paint.setStrokeWidth(5);
			paint.setPathEffect(pEffect);
			
			try {
				Point point = new Point();
				projection.toPixels(gp1, point);
				Point point2 = new Point();
				projection.toPixels(gp2, point2);
				paint.setColor(defaultColor);
				canvas.drawLine(point.x, point.y, point2.x, point2.y, paint);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
    	}
    }
    
    private Path makePath(MapView mapView, String[] pairs, GeoPoint startGP) {
    	Path path = new Path();
    	Projection projection = mapView.getProjection();
		GeoPoint gp1;
		GeoPoint gp2 = startGP;
		String[] lngLat;
		
		for (int i = 1; i < pairs.length; i++) {
			lngLat = pairs[i].split(",");
			gp1 = gp2;
			gp2 = new GeoPoint((int) (Double.parseDouble(lngLat[1]) * 1E6), (int) (Double.parseDouble(lngLat[0]) * 1E6));
			Point point1 = new Point();
			projection.toPixels(gp1, point1);
			Point point2 = new Point();
			projection.toPixels(gp2, point2);
			path.lineTo(point1.x, point1.y);
		}
		
		return path;
    }
    
    public Paint getInnerPaint() {
		if (innerPaint == null) {
			innerPaint = new Paint();
			innerPaint.setARGB(225, 75, 75, 75);
			innerPaint.setAntiAlias(true);
		}
		
		return innerPaint;
	}

	public Paint getBorderPaint() {
		if (borderPaint == null) {
			borderPaint = new Paint();
			borderPaint.setARGB(255, 255, 255, 255);
			borderPaint.setAntiAlias(true);
			borderPaint.setStyle(Style.STROKE);
			borderPaint.setStrokeWidth(2);
		}
		
		return borderPaint;
	}

	public Paint getTextPaint() {
		if (textPaint == null) {
			textPaint = new Paint();
			textPaint.setARGB(255, 255, 255, 255);
			textPaint.setAntiAlias(true);
		}
		
		return textPaint;
	}
	
	public static Paint FontDefault(int size, int color, boolean shadow) {
		Paint textPaint = new Paint();
		textPaint.setTextSize(size);
		textPaint.setColor(color);
		textPaint.setTypeface(Typeface.DEFAULT);
		textPaint.setTextAlign(Paint.Align.LEFT);
		textPaint.setAntiAlias(true);
		
		if(shadow) {
			textPaint.setShadowLayer(2, 2, 2, color+15);
		}
		
		return textPaint;
	}
}