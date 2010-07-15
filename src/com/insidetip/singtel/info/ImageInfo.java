package com.insidetip.singtel.info;

public class ImageInfo {

	private int idLabel;
	private int id;
	
	public ImageInfo(int idLabel, int id) {
		this.idLabel = idLabel;
		this.id = id;
	}
	
	public void setIdLabel(int source) {
		this.idLabel = source;
	}
	
	public int getIdLabel() {
		return idLabel;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}
