package com.insidetip.singtel.map;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ZoomControls;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Projection;
import com.insidetip.singtel.info.MerchantInfo;
import com.insidetip.singtel.screen.R;
import com.insidetip.singtel.util.Constants;

public class MapLocationViewer extends LinearLayout {

	private MapLocationOverlay overlay;
	private boolean isShowRoute = false;
	private static Context context;
	private static int paddingTop = 0;
	public static boolean useCurrentGPS = false;
	private static List<MapLocationInfo> mapLocations;
	public static MapView mapView;
	
	public MapLocationViewer(Context context) {
		super(context);
		this.context = context;
		init();
	}
	
	public MapLocationViewer(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}

	@SuppressWarnings("deprecation")
	private void init() {
		setOrientation(VERTICAL);
		setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.FILL_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
		mapView = new MapView(getContext(), Constants.KEY_MAP_RELEASE);
		mapView.setEnabled(true);
		mapView.setClickable(true);
		addView(mapView);
		
		if(isShowRoute) {
			drawPath(getMapLocations().get(0).getPoint(), getMapLocations().get(1).getPoint(), Color.RED);
		}
		overlay = new MapLocationOverlay(context, this, false, paddingTop);
		mapView.getOverlays().add(overlay);
		
		ZoomControls zoomControls = (ZoomControls) mapView.getZoomControls();
		zoomControls.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		zoomControls.setGravity(Gravity.CENTER_HORIZONTAL);
		mapView.addView(zoomControls);
		mapView.displayZoomControls(true);
		
		mapView.getController().setZoom(16);
		mapView.getController().setCenter(getMapLocations().get(0).getPoint());
	}

	public List<MapLocationInfo> getMapLocations() {
		return mapLocations;
	}
	
	public static void setMapLocation(MapLocationInfo mInfo, int padding, boolean gps){
		mapLocations = new ArrayList<MapLocationInfo>();
		mapLocations.add(mInfo);
		paddingTop = padding;
		useCurrentGPS = gps;
	}
	
	public static void setMapLocations(ArrayList<MerchantInfo> merchantList, int padding, boolean gps) {
		mapLocations = new ArrayList<MapLocationInfo>();
		for(int i = 0; i < merchantList.size(); i++) {
			MerchantInfo merchantInfo = merchantList.get(i);
			
			MapLocationInfo mapLocationInfo = new MapLocationInfo(merchantInfo.getRestaurantName(), merchantInfo.getAddress(), merchantInfo.getLatitude(), merchantInfo.getLongitude(), R.drawable.pin_red, merchantInfo);
			mapLocations.add(mapLocationInfo);

			paddingTop = padding;
			useCurrentGPS = gps;
		}
	}
	
	public MapView getMapView() {
		return mapView;
	}
	
	private void drawPath(GeoPoint src, GeoPoint dest, int color) {
		StringBuilder urlString = new StringBuilder();
		urlString.append("http://maps.google.com/maps?f=d&hl=en");
		urlString.append("&saddr=");
		urlString.append(Double.toString((double) src.getLatitudeE6() / 1.0E6));
		urlString.append(",");
		urlString.append(Double.toString((double) src.getLongitudeE6() / 1.0E6));
		urlString.append("&daddr=");
		urlString.append(Double.toString((double) dest.getLatitudeE6() / 1.0E6));
		urlString.append(",");
		urlString.append(Double.toString((double) dest.getLongitudeE6() / 1.0E6));
		urlString.append("&ie=UTF8&0&om=0&output=kml");
		
		Document doc = null;
		HttpURLConnection urlConnection = null;
		URL url = null;
		try {
			url = new URL(urlString.toString());
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setDoOutput(true);
			urlConnection.setDoInput(true);
			urlConnection.connect();
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(urlConnection.getInputStream());
			
			if (doc.getElementsByTagName("GeometryCollection").getLength() > 0) {
				String path = doc.getElementsByTagName("GeometryCollection").item(0).getFirstChild().getFirstChild().getFirstChild().getNodeValue();
				String[] pairs = path.split(" ");
				String[] lngLat = pairs[0].split(","); 
				GeoPoint startGP = new GeoPoint((int) (Double.parseDouble(lngLat[1]) * 1E6), (int) (Double.parseDouble(lngLat[0]) * 1E6));
				
				Path pathRoute = makePath(pairs, startGP);
				MapLocationOverlay overlayRoute = new MapLocationOverlay(pathRoute, color, true);
				mapView.getOverlays().add(overlayRoute);
				
				GeoPoint gp1;
				GeoPoint gp2 = startGP;
				for (int i = 1; i < pairs.length; i++) {
					lngLat = pairs[i].split(",");
					gp1 = gp2;
					gp2 = new GeoPoint((int) (Double.parseDouble(lngLat[1]) * 1E6), (int) (Double.parseDouble(lngLat[0]) * 1E6));
					MapLocationOverlay overlayRoute_ = new MapLocationOverlay(gp1, gp2, 2, color, true);
					mapView.getOverlays().add(overlayRoute_);
				}
			}
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		catch (ParserConfigurationException e) {
			e.printStackTrace();
		} 
		catch (SAXException e) {
			e.printStackTrace();
		}
	}
	
	private Path makePath(String[] pairs, GeoPoint startGP) {
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
}
