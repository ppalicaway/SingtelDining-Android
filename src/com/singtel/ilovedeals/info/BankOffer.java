package com.singtel.ilovedeals.info;

public class BankOffer {

	private String bank;
	private String card;
	private String offer;
	private String tnc;
	
	public BankOffer(String bank, String card, String offer, String tnc) {
		this.bank = bank;
		this.card = card;
		this.offer = offer;
		this.tnc = tnc;
	}
	
	public void setBank(String bank) {
		this.bank = bank;
	}
	
	public String getBank() {
		return bank;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getCard() {
		return card;
	}

	public void setOffer(String offer) {
		this.offer = offer;
	}

	public String getOffer() {
		return offer;
	}

	public void setTnc(String tnc) {
		this.tnc = tnc;
	}

	public String getTnc() {
		return tnc;
	}
}
