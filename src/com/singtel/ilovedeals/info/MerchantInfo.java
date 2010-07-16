package com.singtel.ilovedeals.info;

public class MerchantInfo {

	private int id;
	private String image;
	private String restaurantName;
	private String address;
	private float rating;
	private int reviews;
	private double latitude;
	private double longitude;
	
	public MerchantInfo() {
		
	}
	
	public MerchantInfo(int id, String image, String restaurantName, String address, float rating, int reviews, double latitude, double longitude) {
		this.id = id;
		this.image = image;
		this.restaurantName = restaurantName;
		this.address = address;
		this.rating = rating;
		this.reviews = reviews;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return image;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public float getRating() {
		return rating;
	}

	public void setReviews(int reviews) {
		this.reviews = reviews;
	}

	public int getReviews() {
		return reviews;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLongitude() {
		return longitude;
	}
}
