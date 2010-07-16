package com.singtel.ilovedeals.map;

import android.graphics.Bitmap;

import com.google.android.maps.GeoPoint;
import com.singtel.ilovedeals.info.MerchantInfo;

public class MapLocationInfo {

	private GeoPoint point;
	private String title;
	private String address;
	private String phone;
	private boolean isCurrentPost;
	private int rId;
	private Bitmap bitmap;
	private MerchantInfo merchantInfo;
	
	public MapLocationInfo(String title, String address, double latitude, double longitude, int rId, MerchantInfo merchantInfo) {
		this.title = title;
		this.address = address;
		this.point = new GeoPoint((int) (latitude * 1e6), (int) (longitude * 1e6));
		this.rId = rId;
		this.merchantInfo = merchantInfo;
	}
	
	public void setPoint(GeoPoint point) {
		this.point = point;
	}
	
	public GeoPoint getPoint() {
		return point;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setCurrentPost(boolean isCurrentPost) {
		this.isCurrentPost = isCurrentPost;
	}

	public boolean isCurrentPost() {
		return isCurrentPost;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}

	public int getrId() {
		return rId;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setMerchantInfo(MerchantInfo merchantInfo) {
		this.merchantInfo = merchantInfo;
	}

	public MerchantInfo getMerchantInfo() {
		return merchantInfo;
	}
}
