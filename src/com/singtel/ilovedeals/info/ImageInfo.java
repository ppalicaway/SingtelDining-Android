package com.singtel.ilovedeals.info;

public class ImageInfo {

	private int idLabel;
	private int id;
	private String bankName;
	private int index;
	private boolean checked;
	
	public ImageInfo(int idLabel, int id, String bankName, boolean checked) {
		this.idLabel = idLabel;
		this.id = id;
		this.bankName = bankName;
		this.checked = checked;
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

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isChecked() {
		return checked;
	}
}
