package com.insidetip.singtel.info;

public class ImageInfo {

	private int idLabel;
	private int id;
	private String bankName;
	
	public ImageInfo(int idLabel, int id, String bankName) {
		this.idLabel = idLabel;
		this.id = id;
		this.bankName = bankName;
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

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankName() {
		return bankName;
	}
}
