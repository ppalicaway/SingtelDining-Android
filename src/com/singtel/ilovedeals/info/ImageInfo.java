package com.singtel.ilovedeals.info;

public class ImageInfo {

	private int idLabel;
	private int id;
	private String bankName;
	private int nonticked;
	private boolean selected = false;
	
	public ImageInfo(int idLabel, int id, String bankName, int nonticked) {
		this.idLabel = idLabel;
		this.id = id;
		this.bankName = bankName;
		this.setNonticked(nonticked);
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

	public void setNonticked(int nonticked) {
		this.nonticked = nonticked;
	}

	public int getNonticked() {
		return nonticked;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isSelected() {
		return selected;
	}
}
