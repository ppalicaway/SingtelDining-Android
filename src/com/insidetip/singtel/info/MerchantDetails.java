package com.insidetip.singtel.info;

import java.util.ArrayList;

public class MerchantDetails {

	private int id;
	private String image;
	private String thumbnail;
	private String title;
	private String type;
	private double rating;
	private int reviews;
	private String address;
	private String phone;
	private double latitude;
	private double longitude;
	private String description;
	private ArrayList<BankOffer> bankOffers = new ArrayList<BankOffer>();
	
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

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public double getRating() {
		return rating;
	}

	public void setReviews(int reviews) {
		this.reviews = reviews;
	}

	public int getReviews() {
		return reviews;
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

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setBankOffers(ArrayList<BankOffer> bankOffers) {
		this.bankOffers = bankOffers;
	}

	public ArrayList<BankOffer> getBankOffers() {
		return bankOffers;
	}
}
