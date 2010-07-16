package com.singtel.ilovedeals.info;

public class SubLocation {

	private int id;
	private String name;
	
	public SubLocation(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
