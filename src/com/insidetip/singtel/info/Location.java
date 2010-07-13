package com.insidetip.singtel.info;

import java.util.ArrayList;

public class Location {

	private int id;
	private String name;
	private ArrayList<SubLocation> subLocation = new ArrayList<SubLocation>();
	
	public Location(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setSubLocation(ArrayList<SubLocation> subLocation) {
		this.subLocation = subLocation;
	}

	public ArrayList<SubLocation> getSubLocation() {
		return subLocation;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
