package com.insidetip.singtel.screen;

import java.util.ArrayList;
import java.util.StringTokenizer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.insidetip.singtel.info.ImageInfo;
import com.insidetip.singtel.util.Constants;
import com.insidetip.singtel.util.Util;

public class SettingsPage extends SingtelDiningActivity {

	public static SettingsPage instance;
	
	public static ArrayList<ImageInfo> images = new ArrayList<ImageInfo>();
	public static String bankQuery = "&bank=Citibank,DBS,OCBC,UOB";
	private static ArrayList<ImageInfo> defaultAll = new ArrayList<ImageInfo>();
	
	private final static String CITIBANK = "Citibank";
	private final static String DBS = "DBS";
	private final static String OCBC = "OCBC";
	private final static String UOB = "UOB";
	
	private CheckBox cbClearPlatinumVisa;
	private CheckBox cbDividendPlatinum;
	private CheckBox cbParagonMasterCard;
	private CheckBox cbParagonVisa;
	private CheckBox cbPremiereMilesVisa;
	private CheckBox cbSMRTCard;

	private CheckBox dbsBlackAmericanExpress;
	private CheckBox dbsLiveFreshPlatinum;
	private CheckBox dbsPlatinumMasterCard;

	private CheckBox ocbcArtsPlatinumCard;
	private CheckBox ocbcBestDenkiPlatinumCard;
	private CheckBox ocbcClassicVisaCard;
	private CheckBox ocbcDebitCard;
	private CheckBox ocbcFairPricePlusVisaCard;
	private CheckBox ocbcGoldMasterCard;
	private CheckBox ocbcIkeaFriendsVisaCard;
	private CheckBox ocbcNTUVisaClassicCard;
	private CheckBox ocbcNTUVisaGoldCard;
	private CheckBox ocbcPlatinumMasterCard;
	private CheckBox ocbcRobinsonsPlatinumCard;
	private CheckBox ocbcSMUDebitCard;
	private CheckBox ocbcSMUPlatinumMasterCard;
	private CheckBox ocbcTitaniumMasterCard;
	private CheckBox ocbcUPlusVisaCard;
	private CheckBox ocbcUPlusPlatinumCard;
	private CheckBox ocbcYesDebitCard;

	private CheckBox uobDirectVisaCard;
	private CheckBox uobLadysCard;
	private CheckBox uobMasterCardClassicCard;
	private CheckBox uobMasterCardGoldCard;
	private CheckBox uobOneCard;
	private CheckBox uobPreferredWorldCard;
	private CheckBox uobPRVIVisaAmericanCard;
	private CheckBox uobVisaClassicCard;
	private CheckBox uobVisaGoldCard;
	private CheckBox uobVisaInfiniteCard;
	private CheckBox uobVisaSignatureCard;
	
	private LinearLayout citiGroup;
	private LinearLayout dbsGroup;
	private LinearLayout ocbcGroup;
	private LinearLayout uobGroup;
	private Button citibank;
	private Button dbs;
	private Button ocbc;
	private Button uob;
	private Button allCreditCards;
	private Button myCreditCards;
	private int count = 0;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(R.style.Theme_Translucent);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.settings);
		
		instance = this;
		initActivity(instance);
		
		citibank = (Button)findViewById(R.id.citibankButton);
		dbs = (Button)findViewById(R.id.dbsButton);
		ocbc = (Button)findViewById(R.id.ocbcButton);
		uob = (Button)findViewById(R.id.uobButton);
		citiGroup = (LinearLayout)findViewById(R.id.citibankGroup);
		dbsGroup = (LinearLayout)findViewById(R.id.dbsGroup);
		ocbcGroup = (LinearLayout)findViewById(R.id.ocbcGroup);
		uobGroup = (LinearLayout)findViewById(R.id.uobGroup);
		
		citibank.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				citibank.setBackgroundResource(R.drawable.citibank_hover);
				dbs.setBackgroundResource(R.drawable.dbs);
				ocbc.setBackgroundResource(R.drawable.ocbc);
				uob.setBackgroundResource(R.drawable.uob);
				citiGroup.setVisibility(LinearLayout.VISIBLE);
				dbsGroup.setVisibility(LinearLayout.GONE);
				ocbcGroup.setVisibility(LinearLayout.GONE);
				uobGroup.setVisibility(LinearLayout.GONE);
			}
		});
		
		dbs.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				citibank.setBackgroundResource(R.drawable.citibank);
				dbs.setBackgroundResource(R.drawable.dbs_hover);
				ocbc.setBackgroundResource(R.drawable.ocbc);
				uob.setBackgroundResource(R.drawable.uob);
				citiGroup.setVisibility(LinearLayout.GONE);
				dbsGroup.setVisibility(LinearLayout.VISIBLE);
				ocbcGroup.setVisibility(LinearLayout.GONE);
				uobGroup.setVisibility(LinearLayout.GONE);
			}
		});
		
		ocbc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				citibank.setBackgroundResource(R.drawable.citibank);
				dbs.setBackgroundResource(R.drawable.dbs);
				ocbc.setBackgroundResource(R.drawable.ocbc_hover);
				uob.setBackgroundResource(R.drawable.uob);
				citiGroup.setVisibility(LinearLayout.GONE);
				dbsGroup.setVisibility(LinearLayout.GONE);
				ocbcGroup.setVisibility(LinearLayout.VISIBLE);
				uobGroup.setVisibility(LinearLayout.GONE);
			}
		});
		
		uob.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				citibank.setBackgroundResource(R.drawable.citibank);
				dbs.setBackgroundResource(R.drawable.dbs);
				ocbc.setBackgroundResource(R.drawable.ocbc);
				uob.setBackgroundResource(R.drawable.uob_hover);
				citiGroup.setVisibility(LinearLayout.GONE);
				dbsGroup.setVisibility(LinearLayout.GONE);
				ocbcGroup.setVisibility(LinearLayout.GONE);
				uobGroup.setVisibility(LinearLayout.VISIBLE);
			}
		});
		
		Button doneButton = (Button)findViewById(R.id.settingsDoneButton);
		doneButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				saveSettings();
				getQuery();
				if(count == 0) {
					Util.showAlert(instance, "ILoveSGDeals", "Select your credit card so that all the deals are customized for your card.", "OK", false);
				}
				else {
					if(SingtelDiningMainPage.instance != null) {
						instance.finish();
					}
					else {
						Intent mainPage = new Intent(instance, SingtelDiningMainPage.class);
						startActivity(mainPage);
						instance.finish();
					}
				}				
			}
		});
		
		settinglayout();
	}

	protected void saveSettings() {
		images.clear();
		ImageInfo iInfo;
		String preference = "";
		if(cbClearPlatinumVisa.isChecked()){
			iInfo = new ImageInfo(R.drawable.citibank_clear_platinum_visa_label, R.drawable.citibank_clear_platinum_visa, CITIBANK, true);
			images.add(iInfo);
			count++;
			preference += "1,";
		}
		if(cbDividendPlatinum.isChecked()){
			iInfo = new ImageInfo(R.drawable.citibank_dividend_platinum_mastercard_label, R.drawable.citibank_dividend_platinum_mastercard, CITIBANK, true);
			images.add(iInfo);
			count++;
			preference += "2,";
		}
		if(cbParagonMasterCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.citibank_paragon_mastercard_label, R.drawable.citibank_paragon_mastercard, CITIBANK, true);
			images.add(iInfo);
			count++;
			preference += "3,";
		}
		if(cbParagonVisa.isChecked()){
			iInfo = new ImageInfo(R.drawable.citibank_paragon_visa_label, R.drawable.citibank_paragon_visa, CITIBANK, true);
			images.add(iInfo);
			count++;
			preference += "4,";
		}
		if(cbPremiereMilesVisa.isChecked()){
			iInfo = new ImageInfo(R.drawable.citibank_premiermiles_visa_signature_label, R.drawable.citibank_premiermiles_visa_signature, CITIBANK, true);
			images.add(iInfo);
			count++;
			preference += "5,";
		}
		if(cbSMRTCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.citibank_smrt_card_label, R.drawable.citibank_smrt_card, CITIBANK, true);
			images.add(iInfo);
			count++;
			preference += "6,";
		}

		if(dbsBlackAmericanExpress.isChecked()){
			iInfo = new ImageInfo(R.drawable.dbs_black_american_express_card_label, R.drawable.dbs_black_american_express_card, DBS, true);
			images.add(iInfo);
			count++;
			preference += "7,";
		}
		if(dbsLiveFreshPlatinum.isChecked()){
			iInfo = new ImageInfo(R.drawable.dbs_live_fresh_platinum_card_label, R.drawable.dbs_live_fresh_platinum_card, DBS, true);
			images.add(iInfo);
			count++;
			preference += "8,";
		}
		if(dbsPlatinumMasterCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.dbs_platinum_mastercard_label, R.drawable.dbs_platinum_mastercard, DBS, true);
			images.add(iInfo);
			count++;
			preference += "9,";
		}
		if(ocbcArtsPlatinumCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.ocbc_arts_platinum_card_label, R.drawable.ocbc_arts_platinum_card, OCBC, true);
			images.add(iInfo);
			count++;
			preference += "10,";
		}
		if(ocbcBestDenkiPlatinumCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.ocbc_best_denki_platinum_card_label, R.drawable.ocbc_best_denki_platinum_card, OCBC, true);
			images.add(iInfo);
			count++;
			preference += "11,";
		}
		if(ocbcClassicVisaCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.ocbc_classic_visa_card_label, R.drawable.ocbc_classic_visa_card, OCBC, true);
			images.add(iInfo);
			count++;
			preference += "12,";
		}
		if(ocbcDebitCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.ocbc_debit_card_label, R.drawable.ocbc_debit_card, OCBC, true);
			images.add(iInfo);
			count++;
			preference += "13,";
		}
		if(ocbcFairPricePlusVisaCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.ocbc_fairpriceplus_visa_card_label, R.drawable.ocbc_fairpriceplus_visa_card, OCBC, true);
			images.add(iInfo);
			count++;
			preference += "14,";
		}
		if(ocbcGoldMasterCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.ocbc_gold_mastercard_label, R.drawable.ocbc_gold_mastercard, OCBC, true);
			images.add(iInfo);
			count++;
			preference += "15,";
		}
		if(ocbcIkeaFriendsVisaCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.ocbc_ikea_friends_visacard_label, R.drawable.ocbc_ikea_friends_visa_card, OCBC, true);
			images.add(iInfo);
			count++;
			preference += "16,";
		}
		
		if(ocbcNTUVisaClassicCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.ocbc_ntu_visa_classic_card_label, R.drawable.ocbc_ntu_visa_classic_card, OCBC, true);
			images.add(iInfo);
			count++;
			preference += "17,";
		}
		if(ocbcNTUVisaGoldCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.ocbc_ntu_visa_gold_card_label, R.drawable.ocbc_ntu_visa_gold_card, OCBC, true);
			images.add(iInfo);
			count++;
			preference += "18,";
		}
		if(ocbcPlatinumMasterCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.ocbc_platinum_mastercard_label, R.drawable.ocbc_platinum_mastercard, OCBC, true);
			images.add(iInfo);
			count++;
			preference += "19,";
		}
		if(ocbcRobinsonsPlatinumCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.ocbc_robinsons_platinum_card_label, R.drawable.ocbc_robinsons_platinum_card, OCBC, true);
			images.add(iInfo);
			count++;
			preference += "20,";
		}
		if(ocbcSMUDebitCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.ocbc_smu_debit_cards_label, R.drawable.ocbc_smu_debit_cards, OCBC, true);
			images.add(iInfo);
			count++;
			preference += "21,";
		}
		if(ocbcSMUPlatinumMasterCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.ocbc_smu_platinum_mastercard_label, R.drawable.ocbc_smu_platinum_mastercard, OCBC, true);
			images.add(iInfo);
			count++;
			preference += "22,";
		}
		if(ocbcTitaniumMasterCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.ocbc_titanium_mastercard_label, R.drawable.ocbc_titanium_mastercard, OCBC, true);
			images.add(iInfo);
			count++;
			preference += "23,";
		}
		if(ocbcUPlusVisaCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.ocbc_uplus_visa_card_label, R.drawable.ocbc_uplus_visa_card, OCBC, true);
			images.add(iInfo);
			count++;
			preference += "24,";
		}
		if(ocbcUPlusPlatinumCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.ocbc_uplus_platinum_card_label, R.drawable.ocbc_uplus_platinum_card, OCBC, true);
			images.add(iInfo);
			count++;
			preference += "25,";
		}
		if(ocbcYesDebitCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.ocbc_yes_debit_card_label, R.drawable.ocbc_yes_debit_card, OCBC, true);
			images.add(iInfo);
			count++;
			preference += "26,";
		}

		if(uobDirectVisaCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.uob_direct_visa_card_label, R.drawable.uob_direct_visa_card, UOB, true);
			images.add(iInfo);
			count++;
			preference += "27,";
		}
		if(uobLadysCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.uob_ladys_card_label, R.drawable.uob_ladys_card, UOB, true);
			images.add(iInfo);
			count++;
			preference += "28,";
		}
		if(uobMasterCardClassicCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.uob_mastercard_classic_card_label, R.drawable.uob_mastercard_classic_card, UOB, true);
			images.add(iInfo);
			count++;
			preference += "29,";
		}
		if(uobMasterCardGoldCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.uob_mastercard_gold_card_label, R.drawable.uob_mastercard_gold_card, UOB, true);
			images.add(iInfo);
			count++;
			preference += "30,";
		}
		if(uobOneCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.uob_one_card_label, R.drawable.uob_one_card, UOB, true);
			images.add(iInfo);
			count++;
			preference += "31,";
		}
		if(uobPreferredWorldCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.uob_preferred_world_mastercard_label, R.drawable.uob_preferred_world_mastercard, UOB, true);
			images.add(iInfo);
			count++;
			preference += "32,";
		}
		if(uobPRVIVisaAmericanCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.uob_prvi_visa_american_express_card_label, R.drawable.uob_prvi_visa_american_express_card, UOB, true);
			images.add(iInfo);
			count++;
			preference += "33,";
		}
		if(uobVisaClassicCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.uob_visa_classic_card_label, R.drawable.uob_visa_classic_card, UOB, true);
			images.add(iInfo);
			count++;
			preference += "34,";
		}
		if(uobVisaGoldCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.uob_visa_gold_card_label, R.drawable.uob_visa_gold_card, UOB, true);
			images.add(iInfo);
			count++;
			preference += "35,";
		}
		if(uobVisaInfiniteCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.uob_visa_infinite_card_label, R.drawable.uob_visa_infinite_card, UOB, true);
			images.add(iInfo);
			count++;
			preference += "36,";
		}
		if(uobVisaSignatureCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.uob_visa_signature_card_label, R.drawable.uob_visa_signature_card, UOB, true);
			images.add(iInfo);
			count++;
			preference += "37,";
		}
		
		SharedPreferences shared = getSharedPreferences(Constants.DEFAULT_SHARE_DATA, 0);
		SharedPreferences.Editor edit = shared.edit();
		edit.putString("cardPref", preference);
		edit.commit();
	}

	private void settinglayout() {
		
		Button infoButton = (Button)findViewById(R.id.infoButton);
		infoButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent webView = new Intent(instance, WebScreen.class);
				startActivity(webView);
			}
		});
		
		allCreditCards = (Button)findViewById(R.id.allCreditCards);
		myCreditCards = (Button)findViewById(R.id.myCreditCards);
		
		allCreditCards.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				myCreditCards.setBackgroundResource(R.drawable.button_right);
				allCreditCards.setBackgroundResource(R.drawable.button_left_hover);
				citiGroup.setVisibility(LinearLayout.VISIBLE);
				dbsGroup.setVisibility(LinearLayout.VISIBLE);
				ocbcGroup.setVisibility(LinearLayout.VISIBLE);
				uobGroup.setVisibility(LinearLayout.VISIBLE);
				checkAll();
			}
		});
		
		myCreditCards.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				myCreditCards.setBackgroundResource(R.drawable.button_right_hover);
				allCreditCards.setBackgroundResource(R.drawable.button_left);
				citiGroup.setVisibility(LinearLayout.VISIBLE);
				dbsGroup.setVisibility(LinearLayout.VISIBLE);
				ocbcGroup.setVisibility(LinearLayout.VISIBLE);
				uobGroup.setVisibility(LinearLayout.VISIBLE);
				SharedPreferences shared = getSharedPreferences(Constants.DEFAULT_SHARE_DATA, 0);
				String cardPref = shared.getString("cardPref", "");
				if(cardPref.equalsIgnoreCase("")) {
					uncheckAll();
				}
				else {
					uncheckAll();
					checkApplicableCards(cardPref);
				}
			}
		});
		
		cbClearPlatinumVisa = (CheckBox)findViewById(R.id.clearplatinumvisa);
		cbClearPlatinumVisa.setOnClickListener(new CheckListener());
		cbDividendPlatinum = (CheckBox)findViewById(R.id.dividendplatinum);
		cbDividendPlatinum.setOnClickListener(new CheckListener());
		cbParagonMasterCard = (CheckBox)findViewById(R.id.paragonmastercard);
		cbParagonMasterCard.setOnClickListener(new CheckListener());
		cbParagonVisa = (CheckBox)findViewById(R.id.paragonvisa);
		cbParagonVisa.setOnClickListener(new CheckListener());
		cbPremiereMilesVisa = (CheckBox)findViewById(R.id.premieremilesvisa);
		cbPremiereMilesVisa.setOnClickListener(new CheckListener());
		cbSMRTCard = (CheckBox)findViewById(R.id.smrtcard);
		cbSMRTCard.setOnClickListener(new CheckListener());
		
		dbsBlackAmericanExpress = (CheckBox)findViewById(R.id.blackamericanexpress);
		dbsBlackAmericanExpress.setOnClickListener(new CheckListener());
		dbsLiveFreshPlatinum = (CheckBox)findViewById(R.id.livefreshplatinum);
		dbsLiveFreshPlatinum.setOnClickListener(new CheckListener());
		dbsPlatinumMasterCard = (CheckBox)findViewById(R.id.dbsplatinummastercard);
		dbsPlatinumMasterCard.setOnClickListener(new CheckListener());
		
		ocbcArtsPlatinumCard = (CheckBox)findViewById(R.id.artsplatinumcard);
		ocbcArtsPlatinumCard.setOnClickListener(new CheckListener());
		ocbcBestDenkiPlatinumCard = (CheckBox)findViewById(R.id.bestdenkiplatinumcard);
		ocbcBestDenkiPlatinumCard.setOnClickListener(new CheckListener());
		ocbcClassicVisaCard = (CheckBox)findViewById(R.id.classicvisacard);
		ocbcClassicVisaCard.setOnClickListener(new CheckListener());
		ocbcDebitCard = (CheckBox)findViewById(R.id.debitcard);
		ocbcDebitCard.setOnClickListener(new CheckListener());
		ocbcFairPricePlusVisaCard = (CheckBox)findViewById(R.id.fairpriceplusvisacard);
		ocbcFairPricePlusVisaCard.setOnClickListener(new CheckListener());
		ocbcGoldMasterCard = (CheckBox)findViewById(R.id.goldmastercard);
		ocbcGoldMasterCard.setOnClickListener(new CheckListener());
		ocbcIkeaFriendsVisaCard = (CheckBox)findViewById(R.id.ikeafriendsvisacard);
		ocbcIkeaFriendsVisaCard.setOnClickListener(new CheckListener());
		ocbcNTUVisaClassicCard = (CheckBox)findViewById(R.id.ntuvisaclassiccard);
		ocbcNTUVisaClassicCard.setOnClickListener(new CheckListener());
		ocbcNTUVisaGoldCard = (CheckBox)findViewById(R.id.ntuvisagoldcard);
		ocbcNTUVisaGoldCard.setOnClickListener(new CheckListener());
		ocbcPlatinumMasterCard = (CheckBox)findViewById(R.id.platinummastercard);
		ocbcPlatinumMasterCard.setOnClickListener(new CheckListener());
		ocbcRobinsonsPlatinumCard = (CheckBox)findViewById(R.id.robinsonsplatinumcard);
		ocbcRobinsonsPlatinumCard.setOnClickListener(new CheckListener());
		ocbcSMUDebitCard = (CheckBox)findViewById(R.id.smudebitcards);
		ocbcSMUDebitCard.setOnClickListener(new CheckListener());
		ocbcSMUPlatinumMasterCard = (CheckBox)findViewById(R.id.smuplatinummastercard);
		ocbcSMUPlatinumMasterCard.setOnClickListener(new CheckListener());
		ocbcTitaniumMasterCard = (CheckBox)findViewById(R.id.titaniummastercard);
		ocbcTitaniumMasterCard.setOnClickListener(new CheckListener());
		ocbcUPlusVisaCard = (CheckBox)findViewById(R.id.uplusvisacard);
		ocbcUPlusVisaCard.setOnClickListener(new CheckListener());
		ocbcUPlusPlatinumCard = (CheckBox)findViewById(R.id.uplusplatinumcard);
		ocbcUPlusPlatinumCard.setOnClickListener(new CheckListener());
		ocbcYesDebitCard = (CheckBox)findViewById(R.id.yesdebitcard);
		ocbcYesDebitCard.setOnClickListener(new CheckListener());
		
		uobDirectVisaCard = (CheckBox)findViewById(R.id.directvisacard);
		uobDirectVisaCard.setOnClickListener(new CheckListener());
		uobLadysCard = (CheckBox)findViewById(R.id.ladyscard);
		uobLadysCard.setOnClickListener(new CheckListener());
		uobMasterCardClassicCard = (CheckBox)findViewById(R.id.mastercardclassiccard);
		uobMasterCardClassicCard.setOnClickListener(new CheckListener());
		uobMasterCardGoldCard = (CheckBox)findViewById(R.id.mastercardgoldcard);
		uobMasterCardGoldCard.setOnClickListener(new CheckListener());
		uobOneCard = (CheckBox)findViewById(R.id.onecard);
		uobOneCard.setOnClickListener(new CheckListener());
		uobPreferredWorldCard = (CheckBox)findViewById(R.id.preferredworld);
		uobPreferredWorldCard.setOnClickListener(new CheckListener());
		uobPRVIVisaAmericanCard = (CheckBox)findViewById(R.id.prvivisaamerican);
		uobPRVIVisaAmericanCard.setOnClickListener(new CheckListener());
		uobVisaClassicCard = (CheckBox)findViewById(R.id.visaclassiccard);
		uobVisaClassicCard.setOnClickListener(new CheckListener());
		uobVisaGoldCard = (CheckBox)findViewById(R.id.visagoldcard);
		uobVisaGoldCard.setOnClickListener(new CheckListener());
		uobVisaInfiniteCard = (CheckBox)findViewById(R.id.visainfinitecard);
		uobVisaInfiniteCard.setOnClickListener(new CheckListener());
		uobVisaSignatureCard = (CheckBox)findViewById(R.id.visasignaturecard);
		uobVisaSignatureCard.setOnClickListener(new CheckListener());
	}
	
	protected void checkApplicableCards(String cardPref) {
		StringTokenizer stringTkn = new StringTokenizer(cardPref, ",");
		int size = stringTkn.countTokens();
		for(int i=0; i < size ; i++) {
			String temp = stringTkn.nextToken();
			setCheckBox(temp);
		}
	}

	private void setCheckBox(String value) {
		int id = Integer.parseInt(value);
		switch(id) {
		case Constants.CBCLEARPLATINUMVISA:
			cbClearPlatinumVisa.setChecked(true);
			break;
		case Constants.CBDIVIDENDPLATINUM:
			cbDividendPlatinum.setChecked(true);
			break;
		case Constants.CBPARAGONMASTERCARD:
			cbParagonMasterCard.setChecked(true);
			break;
		case Constants.CBPARAGONVISA:
			cbParagonVisa.setChecked(true);
			break;
		case Constants.CBPREMIEREMILESVISA:
			cbPremiereMilesVisa.setChecked(true);
			break;
		case Constants.CBSMRTCARD:
			cbSMRTCard.setChecked(true);
			break;
		case Constants.DBSBLACKAMERICANEXPRESS:
			dbsBlackAmericanExpress.setChecked(true);
			break;
		case Constants.DBSLIVEFRESHPLATINUM:
			dbsLiveFreshPlatinum.setChecked(true);
			break;
		case Constants.DBSPLATINUMMASTERCARD:
			dbsPlatinumMasterCard.setChecked(true);
			break;
		case Constants.OCBCARTSPLATINUMCARD:
			ocbcArtsPlatinumCard.setChecked(true);
			break;
		case Constants.OCBCBESTDENKIPLATINUMCARD:
			ocbcBestDenkiPlatinumCard.setChecked(true);
			break;
		case Constants.OCBCCLASSICVISACARD:
			ocbcClassicVisaCard.setChecked(true);
			break;
		case Constants.OCBCDEBITCARD:
			ocbcDebitCard.setChecked(true);
			break;
		case Constants.OCBCFAIRPRICEPLUSVISACARD:
			ocbcFairPricePlusVisaCard.setChecked(true);
			break;
		case Constants.OCBCGOLDMASTERCARD:
			ocbcGoldMasterCard.setChecked(true);
			break;
		case Constants.OCBCIKEAFRIENDSVISACARD:
			ocbcIkeaFriendsVisaCard.setChecked(true);
			break;
		case Constants.OCBCNTUVISACLASSICCARD:
			ocbcNTUVisaClassicCard.setChecked(true);
			break;
		case Constants.OCBCNTUVISAGOLDCARD:
			ocbcNTUVisaGoldCard.setChecked(true);
			break;
		case Constants.OCBCPLATINUMMASTERCARD:
			ocbcPlatinumMasterCard.setChecked(true);
			break;
		case Constants.OCBCROBINSONSPLATINUMCARD:
			ocbcRobinsonsPlatinumCard.setChecked(true);
			break;
		case Constants.OCBCSMUDEBITCARD:
			ocbcSMUDebitCard.setChecked(true);
			break;
		case Constants.OCBCSMUPLATINUMMASTERCARD:
			ocbcSMUPlatinumMasterCard.setChecked(true);
			break;
		case Constants.OCBCTITANIUMMASTERCARD:
			ocbcTitaniumMasterCard.setChecked(true);
			break;
		case Constants.OCBCUPLUSVISACARD:
			ocbcUPlusVisaCard.setChecked(true);
			break;
		case Constants.OCBCUPLUSPLATINUMCARD:
			ocbcUPlusPlatinumCard.setChecked(true);
			break;
		case Constants.OCBCYESDEBITCARD:
			ocbcYesDebitCard.setChecked(true);
			break;
		case Constants.UOBDIRECTVISACARD:
			uobDirectVisaCard.setChecked(true);
			break;
		case Constants.UOBLADYSCARD:
			uobLadysCard.setChecked(true);
			break;
		case Constants.UOBMASTERCARDCLASSICCARD:
			uobMasterCardClassicCard.setChecked(true);
			break;
		case Constants.UOBMASTERCARDGOLDCARD:
			uobMasterCardGoldCard.setChecked(true);
			break;
		case Constants.UOBONECARD:
			uobOneCard.setChecked(true);
			break;
		case Constants.UOBPREFERREDWORLDCARD:
			uobPreferredWorldCard.setChecked(true);
			break;
		case Constants.UOBPRVIVISAAMERICANCARD:
			uobPRVIVisaAmericanCard.setChecked(true);
			break;
		case Constants.UOBVISACLASSICCARD:
			uobVisaClassicCard.setChecked(true);
			break;
		case Constants.UOBVISAGOLDCARD:
			uobVisaGoldCard.setChecked(true);
			break;
		case Constants.UOBVISAINFINITECARD:
			uobVisaInfiniteCard.setChecked(true);
			break;
		case Constants.UOBVISASIGNATURECARD:
			uobVisaSignatureCard.setChecked(true);
			break;
		}
	}

	protected void checkAll() {
		cbClearPlatinumVisa.setChecked(true);
		cbDividendPlatinum.setChecked(true);
		cbParagonMasterCard.setChecked(true);
		cbParagonVisa.setChecked(true);
		cbPremiereMilesVisa.setChecked(true);
		cbSMRTCard.setChecked(true);

		dbsBlackAmericanExpress.setChecked(true);
		dbsLiveFreshPlatinum.setChecked(true);
		dbsPlatinumMasterCard.setChecked(true);

		ocbcArtsPlatinumCard.setChecked(true);
		ocbcBestDenkiPlatinumCard.setChecked(true);
		ocbcClassicVisaCard.setChecked(true);
		ocbcDebitCard.setChecked(true);
		ocbcFairPricePlusVisaCard.setChecked(true);
		ocbcGoldMasterCard.setChecked(true);
		ocbcIkeaFriendsVisaCard.setChecked(true);
		ocbcNTUVisaClassicCard.setChecked(true);
		ocbcNTUVisaGoldCard.setChecked(true);
		ocbcPlatinumMasterCard.setChecked(true);
		ocbcRobinsonsPlatinumCard.setChecked(true);
		ocbcSMUDebitCard.setChecked(true);
		ocbcSMUPlatinumMasterCard.setChecked(true);
		ocbcTitaniumMasterCard.setChecked(true);
		ocbcUPlusVisaCard.setChecked(true);
		ocbcUPlusPlatinumCard.setChecked(true);
		ocbcYesDebitCard.setChecked(true);

		uobDirectVisaCard.setChecked(true);
		uobLadysCard.setChecked(true);
		uobMasterCardClassicCard.setChecked(true);
		uobMasterCardGoldCard.setChecked(true);
		uobOneCard.setChecked(true);
		uobPreferredWorldCard.setChecked(true);
		uobPRVIVisaAmericanCard.setChecked(true);
		uobVisaClassicCard.setChecked(true);
		uobVisaGoldCard.setChecked(true);
		uobVisaInfiniteCard.setChecked(true);
		uobVisaSignatureCard.setChecked(true);
	}
	
	protected void uncheckAll() {
		cbClearPlatinumVisa.setChecked(false);
		cbDividendPlatinum.setChecked(false);
		cbParagonMasterCard.setChecked(false);
		cbParagonVisa.setChecked(false);
		cbPremiereMilesVisa.setChecked(false);
		cbSMRTCard.setChecked(false);

		dbsBlackAmericanExpress.setChecked(false);
		dbsLiveFreshPlatinum.setChecked(false);
		dbsPlatinumMasterCard.setChecked(false);

		ocbcArtsPlatinumCard.setChecked(false);
		ocbcBestDenkiPlatinumCard.setChecked(false);
		ocbcClassicVisaCard.setChecked(false);
		ocbcDebitCard.setChecked(false);
		ocbcFairPricePlusVisaCard.setChecked(false);
		ocbcGoldMasterCard.setChecked(false);
		ocbcIkeaFriendsVisaCard.setChecked(false);
		ocbcNTUVisaClassicCard.setChecked(false);
		ocbcNTUVisaGoldCard.setChecked(false);
		ocbcPlatinumMasterCard.setChecked(false);
		ocbcRobinsonsPlatinumCard.setChecked(false);
		ocbcSMUDebitCard.setChecked(false);
		ocbcSMUPlatinumMasterCard.setChecked(false);
		ocbcTitaniumMasterCard.setChecked(false);
		ocbcUPlusVisaCard.setChecked(false);
		ocbcUPlusPlatinumCard.setChecked(false);
		ocbcYesDebitCard.setChecked(false);

		uobDirectVisaCard.setChecked(false);
		uobLadysCard.setChecked(false);
		uobMasterCardClassicCard.setChecked(false);
		uobMasterCardGoldCard.setChecked(false);
		uobOneCard.setChecked(false);
		uobPreferredWorldCard.setChecked(false);
		uobPRVIVisaAmericanCard.setChecked(false);
		uobVisaClassicCard.setChecked(false);
		uobVisaGoldCard.setChecked(false);
		uobVisaInfiniteCard.setChecked(false);
		uobVisaSignatureCard.setChecked(false);
	}

	private class CheckListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			myCreditCards.setBackgroundResource(R.drawable.button_right_hover);
			allCreditCards.setBackgroundResource(R.drawable.button_left);
		}
	}
	
	public static ArrayList<ImageInfo> getDefaultCards() {
		if(defaultAll.isEmpty()) {
			ImageInfo iInfo;
			
			iInfo = new ImageInfo(R.drawable.citibank_clear_platinum_visa_label, R.drawable.citibank_clear_platinum_visa, CITIBANK, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.citibank_dividend_platinum_mastercard_label, R.drawable.citibank_dividend_platinum_mastercard, CITIBANK, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.citibank_paragon_mastercard_label, R.drawable.citibank_paragon_mastercard, CITIBANK, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.citibank_paragon_visa_label, R.drawable.citibank_paragon_visa, CITIBANK, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.citibank_premiermiles_visa_signature_label, R.drawable.citibank_premiermiles_visa_signature, CITIBANK, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.citibank_smrt_card_label, R.drawable.citibank_smrt_card, CITIBANK, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.dbs_black_american_express_card_label, R.drawable.dbs_black_american_express_card, DBS, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.dbs_live_fresh_platinum_card_label, R.drawable.dbs_live_fresh_platinum_card, DBS, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.dbs_platinum_mastercard_label, R.drawable.dbs_platinum_mastercard, DBS, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbc_arts_platinum_card_label, R.drawable.ocbc_arts_platinum_card, OCBC, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbc_best_denki_platinum_card_label, R.drawable.ocbc_best_denki_platinum_card, OCBC, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbc_classic_visa_card_label, R.drawable.ocbc_classic_visa_card, OCBC, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbc_debit_card_label, R.drawable.ocbc_debit_card, OCBC, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbc_fairpriceplus_visa_card_label, R.drawable.ocbc_fairpriceplus_visa_card, OCBC, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbc_gold_mastercard_label, R.drawable.ocbc_gold_mastercard, OCBC, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbc_ikea_friends_visacard_label, R.drawable.ocbc_ikea_friends_visa_card, OCBC, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbc_ntu_visa_classic_card_label, R.drawable.ocbc_ntu_visa_classic_card, OCBC, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbc_ntu_visa_gold_card_label, R.drawable.ocbc_ntu_visa_gold_card, OCBC, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbc_platinum_mastercard_label, R.drawable.ocbc_platinum_mastercard, OCBC, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbc_robinsons_platinum_card_label, R.drawable.ocbc_robinsons_platinum_card, OCBC, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbc_smu_debit_cards_label, R.drawable.ocbc_smu_debit_cards, OCBC, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbc_smu_platinum_mastercard_label, R.drawable.ocbc_smu_platinum_mastercard, OCBC, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbc_titanium_mastercard_label, R.drawable.ocbc_titanium_mastercard, OCBC, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbc_uplus_visa_card_label, R.drawable.ocbc_uplus_visa_card, OCBC, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbc_uplus_platinum_card_label, R.drawable.ocbc_uplus_platinum_card, OCBC, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbc_yes_debit_card_label, R.drawable.ocbc_yes_debit_card, OCBC, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uob_direct_visa_card_label, R.drawable.uob_direct_visa_card, UOB, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uob_ladys_card_label, R.drawable.uob_ladys_card, UOB, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uob_mastercard_classic_card_label, R.drawable.uob_mastercard_classic_card, UOB, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uob_mastercard_gold_card_label, R.drawable.uob_mastercard_gold_card, UOB, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uob_one_card_label, R.drawable.uob_one_card, UOB, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uob_preferred_world_mastercard_label, R.drawable.uob_preferred_world_mastercard, UOB, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uob_prvi_visa_american_express_card_label, R.drawable.uob_prvi_visa_american_express_card, UOB, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uob_visa_classic_card_label, R.drawable.uob_visa_classic_card, UOB, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uob_visa_gold_card_label, R.drawable.uob_visa_gold_card, UOB, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uob_visa_infinite_card_label, R.drawable.uob_visa_infinite_card, UOB, true);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uob_visa_signature_card_label, R.drawable.uob_visa_signature_card, UOB, true);
			defaultAll.add(iInfo);
			images = defaultAll;
		}
		return defaultAll;
	}
	
	public static void getQuery() {
		
		ArrayList<String> bank = new ArrayList<String>();
		for(int i = 0; i < images.size(); i++) {
			String bankName = images.get(i).getBankName();
			if(!bank.contains(bankName)) {
				bank.add(bankName);
			}
		}
		
		String bankQ = "";
		for(int j = 0; j < bank.size(); j++) {
			bankQ += bank.get(j) + ",";
		}
		
		if(bankQ.charAt(bankQ.length()-1) == ',') {
			bankQ = bankQ.substring(0, bankQ.length()-1);
		}
		
		bankQuery = "&bank=" + bankQ;
	}
	
	private void deleteObjectByIdLabel(int IdLabel) {
		for(int i = 0; i < images.size(); i++) {
			if(images.get(i).getIdLabel() == IdLabel) {
				images.remove(i);
				break;
			}
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK) {
			return false;
		}
		return false;
	}
}