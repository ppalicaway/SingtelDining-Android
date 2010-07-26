package com.singtel.ilovedeals.screen;

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

import com.singtel.ilovedeals.info.ImageInfo;
import com.singtel.ilovedeals.util.Constants;
import com.singtel.ilovedeals.util.Util;

public class SettingsPage extends SingtelDiningActivity {

	public static SettingsPage instance;
	
	public static ArrayList<ImageInfo> images = new ArrayList<ImageInfo>();
	public static ArrayList<ImageInfo> untickedImages = new ArrayList<ImageInfo>();
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

	private CheckBox ocbcArtsPlatinumMasterCard;
	private CheckBox bestOCBCPlatinumMasterCard;
	private CheckBox fairPricePlusCreditDebitCards;
	private CheckBox ocbcIKEAFriedsVisaCard;
	private CheckBox ocbcNTUVisaGoldCard;
	private CheckBox ocbcPlatinumMasterCard;
	private CheckBox ocbcRobinsonsVisaPlatinumCard;
	private CheckBox ocbcSMUDebitCard;
	private CheckBox ocbcSMUPlatinumMasterCard;
	private CheckBox ocbcTitaniumMasterCard;
	private CheckBox uPlusCreditDebitCards;
	private CheckBox ocbcYesCard;

	private CheckBox singtelUOBVisaPlatinumCard;
	private CheckBox uobPRVIAmericanExpressCard;
	private CheckBox uobVisaSignatureCard;
	private CheckBox uobOneCard;
	private CheckBox uobPreferredPlatinumCardVisa;
	private CheckBox uobPreferredPlatinumCardMasterCard;
	private CheckBox uobLadysCard;
	private CheckBox uobLadysPlatinumCard;
	private CheckBox uobLadysSolitaireCard;
	private CheckBox uobVisaInfiniteCard;
	private CheckBox uobPreferredWorldMasterCard;
	private CheckBox uobVisaGoldCard;
	private CheckBox uobVisaClassicCard;
	private CheckBox uobMasterCardGoldCard;
	private CheckBox uobMasterCardClassicCard;
	private CheckBox metroUOBPlatinumCard;
	private CheckBox uobJCBPlatinumCard;
	private CheckBox uobChinaUnionPayPlatinumCard;
	private CheckBox uobDirectVisaCard;
	
	private LinearLayout citiGroup;
	private LinearLayout dbsGroup;
	private LinearLayout ocbcGroup;
	private LinearLayout posbGroup;
	private LinearLayout uobGroup;
	private Button citibank;
	private Button dbs;
	private Button ocbc;
	private Button posb;
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
		posb = (Button)findViewById(R.id.posbButton);
		uob = (Button)findViewById(R.id.uobButton);
		citiGroup = (LinearLayout)findViewById(R.id.citibankGroup);
		dbsGroup = (LinearLayout)findViewById(R.id.dbsGroup);
		ocbcGroup = (LinearLayout)findViewById(R.id.ocbcGroup);
		posbGroup = (LinearLayout)findViewById(R.id.posbGroup);
		uobGroup = (LinearLayout)findViewById(R.id.uobGroup);
		
		citibank.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				citibank.setBackgroundResource(R.drawable.citibank_hover);
				dbs.setBackgroundResource(R.drawable.dbs);
				ocbc.setBackgroundResource(R.drawable.ocbc);
				posb.setBackgroundResource(R.drawable.posb);
				uob.setBackgroundResource(R.drawable.uob);
				citiGroup.setVisibility(LinearLayout.VISIBLE);
				dbsGroup.setVisibility(LinearLayout.GONE);
				ocbcGroup.setVisibility(LinearLayout.GONE);
				posbGroup.setVisibility(LinearLayout.GONE);
				uobGroup.setVisibility(LinearLayout.GONE);
			}
		});
		
		dbs.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				citibank.setBackgroundResource(R.drawable.citibank);
				dbs.setBackgroundResource(R.drawable.dbs_hover);
				ocbc.setBackgroundResource(R.drawable.ocbc);
				posb.setBackgroundResource(R.drawable.posb);
				uob.setBackgroundResource(R.drawable.uob);
				citiGroup.setVisibility(LinearLayout.GONE);
				dbsGroup.setVisibility(LinearLayout.VISIBLE);
				ocbcGroup.setVisibility(LinearLayout.GONE);
				posbGroup.setVisibility(LinearLayout.GONE);
				uobGroup.setVisibility(LinearLayout.GONE);
			}
		});
		
		ocbc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				citibank.setBackgroundResource(R.drawable.citibank);
				dbs.setBackgroundResource(R.drawable.dbs);
				ocbc.setBackgroundResource(R.drawable.ocbc_hover);
				posb.setBackgroundResource(R.drawable.posb);
				uob.setBackgroundResource(R.drawable.uob);
				citiGroup.setVisibility(LinearLayout.GONE);
				dbsGroup.setVisibility(LinearLayout.GONE);
				ocbcGroup.setVisibility(LinearLayout.VISIBLE);
				posbGroup.setVisibility(LinearLayout.GONE);
				uobGroup.setVisibility(LinearLayout.GONE);
			}
		});
		
		posb.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				citibank.setBackgroundResource(R.drawable.citibank);
				dbs.setBackgroundResource(R.drawable.dbs);
				ocbc.setBackgroundResource(R.drawable.ocbc);
				posb.setBackgroundResource(R.drawable.posb_hover);
				uob.setBackgroundResource(R.drawable.uob);
				citiGroup.setVisibility(LinearLayout.GONE);
				dbsGroup.setVisibility(LinearLayout.GONE);
				ocbcGroup.setVisibility(LinearLayout.GONE);
				posbGroup.setVisibility(LinearLayout.VISIBLE);
				uobGroup.setVisibility(LinearLayout.GONE);
			}
		});
		
		uob.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				citibank.setBackgroundResource(R.drawable.citibank);
				dbs.setBackgroundResource(R.drawable.dbs);
				ocbc.setBackgroundResource(R.drawable.ocbc);
				posb.setBackgroundResource(R.drawable.posb);
				uob.setBackgroundResource(R.drawable.uob_hover);
				citiGroup.setVisibility(LinearLayout.GONE);
				dbsGroup.setVisibility(LinearLayout.GONE);
				ocbcGroup.setVisibility(LinearLayout.GONE);
				posbGroup.setVisibility(LinearLayout.GONE);
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
		untickedImages.clear();
		ImageInfo iInfo;
		String preference = "";
		
		// 1st
		if(cbClearPlatinumVisa.isChecked()){
			iInfo = new ImageInfo(R.drawable.citibank_clear_platinum_visa_label, R.drawable.citibank_clear_platinum_visa, CITIBANK, R.drawable.citibank_clear_platinum_visa_nonticked);
			images.add(iInfo);
			count++;
			preference += "1,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.citibank_clear_platinum_visa_label, R.drawable.citibank_clear_platinum_visa, CITIBANK, R.drawable.citibank_clear_platinum_visa_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 2nd
		if(cbDividendPlatinum.isChecked()){
			iInfo = new ImageInfo(R.drawable.citibank_dividend_platinum_mastercard_label, R.drawable.citibank_dividend_platinum_mastercard, CITIBANK, R.drawable.citibank_dividend_platinum_mastercard_nonticked);
			images.add(iInfo);
			count++;
			preference += "2,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.citibank_dividend_platinum_mastercard_label, R.drawable.citibank_dividend_platinum_mastercard, CITIBANK, R.drawable.citibank_dividend_platinum_mastercard_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 3rd
		if(cbParagonMasterCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.citibank_paragon_mastercard_label, R.drawable.citibank_paragon_mastercard, CITIBANK, R.drawable.citibank_paragon_mastercard_nonticked);
			images.add(iInfo);
			count++;
			preference += "3,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.citibank_paragon_mastercard_label, R.drawable.citibank_paragon_mastercard, CITIBANK, R.drawable.citibank_paragon_mastercard_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 4th
		if(cbParagonVisa.isChecked()){
			iInfo = new ImageInfo(R.drawable.citibank_paragon_visa_label, R.drawable.citibank_paragon_visa, CITIBANK, R.drawable.citibank_paragon_visa_nonticked);
			images.add(iInfo);
			count++;
			preference += "4,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.citibank_paragon_visa_label, R.drawable.citibank_paragon_visa, CITIBANK, R.drawable.citibank_paragon_visa_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 5th
		if(cbPremiereMilesVisa.isChecked()){
			iInfo = new ImageInfo(R.drawable.citibank_premiermiles_visa_signature_label, R.drawable.citibank_premiermiles_visa_signature, CITIBANK, R.drawable.citibank_premiermiles_visa_signature_nonticked);
			images.add(iInfo);
			count++;
			preference += "5,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.citibank_premiermiles_visa_signature_label, R.drawable.citibank_premiermiles_visa_signature, CITIBANK, R.drawable.citibank_premiermiles_visa_signature_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 6th
		if(cbSMRTCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.citibank_smrt_card_label, R.drawable.citibank_smrt_card, CITIBANK, R.drawable.citibank_smrt_card_nonticked);
			images.add(iInfo);
			count++;
			preference += "6,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.citibank_smrt_card_label, R.drawable.citibank_smrt_card, CITIBANK, R.drawable.citibank_smrt_card_nonticked);
			untickedImages.add(iInfo);
		}

		// 7th
		if(dbsBlackAmericanExpress.isChecked()){
			iInfo = new ImageInfo(R.drawable.dbs_black_american_express_card_label, R.drawable.dbs_black_american_express_card, DBS, R.drawable.dbs_black_american_express_card_nonticked);
			images.add(iInfo);
			count++;
			preference += "7,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.dbs_black_american_express_card_label, R.drawable.dbs_black_american_express_card, DBS, R.drawable.dbs_black_american_express_card_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 8th
		if(dbsLiveFreshPlatinum.isChecked()){
			iInfo = new ImageInfo(R.drawable.dbs_live_fresh_platinum_card_label, R.drawable.dbs_live_fresh_platinum_card, DBS, R.drawable.dbs_live_fresh_platinum_card_nonticked);
			images.add(iInfo);
			count++;
			preference += "8,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.dbs_live_fresh_platinum_card_label, R.drawable.dbs_live_fresh_platinum_card, DBS, R.drawable.dbs_live_fresh_platinum_card_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 10th
		if(ocbcArtsPlatinumMasterCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.ocbcartsplatinummastercard_label, R.drawable.ocbcartsplatinummastercard, OCBC, R.drawable.ocbcartsplatinummastercard_nonticked);
			images.add(iInfo);
			count++;
			preference += "10,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.ocbcartsplatinummastercard_label, R.drawable.ocbcartsplatinummastercard, OCBC, R.drawable.ocbcartsplatinummastercard_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 11th
		if(bestOCBCPlatinumMasterCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.bestocbcplatinummastercard_label, R.drawable.bestocbcplatinummastercard, OCBC, R.drawable.bestocbcplatinummastercard_nonticked);
			images.add(iInfo);
			count++;
			preference += "11,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.bestocbcplatinummastercard_label, R.drawable.bestocbcplatinummastercard, OCBC, R.drawable.bestocbcplatinummastercard_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 12th
		if(fairPricePlusCreditDebitCards.isChecked()){
			iInfo = new ImageInfo(R.drawable.fairpricepluscreditdebitcards_label, R.drawable.fairpricepluscreditdebitcards, OCBC, R.drawable.fairpricepluscreditdebitcards_nonticked);
			images.add(iInfo);
			count++;
			preference += "12,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.fairpricepluscreditdebitcards_label, R.drawable.fairpricepluscreditdebitcards, OCBC, R.drawable.fairpricepluscreditdebitcards_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 13th
		if(ocbcIKEAFriedsVisaCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.ocbcikeafriedsvisacard_label, R.drawable.ocbcikeafriedsvisacard, OCBC, R.drawable.ocbcikeafriedsvisacard_nonticked);
			images.add(iInfo);
			count++;
			preference += "13,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.ocbcikeafriedsvisacard_label, R.drawable.ocbcikeafriedsvisacard, OCBC, R.drawable.ocbcikeafriedsvisacard_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 14th
		if(ocbcNTUVisaGoldCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.ocbcntuvisagoldcard_label, R.drawable.ocbcntuvisagoldcard, OCBC, R.drawable.ocbcntuvisagoldcard_nonticked);
			images.add(iInfo);
			count++;
			preference += "14,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.ocbcntuvisagoldcard_label, R.drawable.ocbcntuvisagoldcard, OCBC, R.drawable.ocbcntuvisagoldcard_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 15th
		if(ocbcPlatinumMasterCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.ocbcplatinummastercard_label, R.drawable.ocbcplatinummastercard, OCBC, R.drawable.ocbcplatinummastercard_nonticked);
			images.add(iInfo);
			count++;
			preference += "15,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.ocbcplatinummastercard_label, R.drawable.ocbcplatinummastercard, OCBC, R.drawable.ocbcplatinummastercard_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 16th
		if(ocbcRobinsonsVisaPlatinumCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.ocbcrobinsonsvisaplatinumcard_label, R.drawable.ocbcrobinsonsvisaplatinumcard, OCBC, R.drawable.ocbcrobinsonsvisaplatinumcard_nonticked);
			images.add(iInfo);
			count++;
			preference += "16,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.ocbcrobinsonsvisaplatinumcard_label, R.drawable.ocbcrobinsonsvisaplatinumcard, OCBC, R.drawable.ocbcrobinsonsvisaplatinumcard_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 17th
		if(ocbcSMUDebitCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.ocbcsmudebitcard_label, R.drawable.ocbcsmudebitcard, OCBC, R.drawable.ocbcsmudebitcard_nonticked);
			images.add(iInfo);
			count++;
			preference += "17,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.ocbcsmudebitcard_label, R.drawable.ocbcsmudebitcard, OCBC, R.drawable.ocbcsmudebitcard_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 18th
		if(ocbcSMUPlatinumMasterCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.ocbcsmuplatinummastercard_label, R.drawable.ocbcsmuplatinummastercard, OCBC, R.drawable.ocbcsmuplatinummastercard_nonticked);
			images.add(iInfo);
			count++;
			preference += "18,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.ocbcsmuplatinummastercard_label, R.drawable.ocbcsmuplatinummastercard, OCBC, R.drawable.ocbcsmuplatinummastercard_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 19th
		if(ocbcTitaniumMasterCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.ocbctitaniummastercard_label, R.drawable.ocbctitaniummastercard, OCBC, R.drawable.ocbctitaniummastercard_nonticked);
			images.add(iInfo);
			count++;
			preference += "19,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.ocbctitaniummastercard_label, R.drawable.ocbctitaniummastercard, OCBC, R.drawable.ocbctitaniummastercard_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 20th
		if(uPlusCreditDebitCards.isChecked()){
			iInfo = new ImageInfo(R.drawable.upluscreditdebitcards_label, R.drawable.upluscreditdebitcards, OCBC, R.drawable.upluscreditdebitcards_nonticked);
			images.add(iInfo);
			count++;
			preference += "20,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.upluscreditdebitcards_label, R.drawable.upluscreditdebitcards, OCBC, R.drawable.upluscreditdebitcards_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 21st
		if(ocbcYesCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.ocbcyescard_label, R.drawable.ocbcyescard, OCBC, R.drawable.ocbcyescard_nonticked);
			images.add(iInfo);
			count++;
			preference += "21,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.ocbcyescard_label, R.drawable.ocbcyescard, OCBC, R.drawable.ocbcyescard_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 9th
		if(dbsPlatinumMasterCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.dbs_platinum_mastercard_label, R.drawable.dbs_platinum_mastercard, DBS, R.drawable.dbs_platinum_mastercard_nonticked);
			images.add(iInfo);
			count++;
			preference += "9,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.dbs_platinum_mastercard_label, R.drawable.dbs_platinum_mastercard, DBS, R.drawable.dbs_platinum_mastercard_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 27th
		if(singtelUOBVisaPlatinumCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.singteluobvisaplatinumcard_label, R.drawable.singteluobvisaplatinumcard, UOB, R.drawable.singteluobvisaplatinumcard_nonticked);
			images.add(iInfo);
			count++;
			preference += "27,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.singteluobvisaplatinumcard_label, R.drawable.singteluobvisaplatinumcard, UOB, R.drawable.singteluobvisaplatinumcard_nonticked);
			untickedImages.add(iInfo);
		}

		// 28th
		if(uobPRVIAmericanExpressCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.uobprviamericanexpresscard_label, R.drawable.uobprviamericanexpresscard, UOB, R.drawable.uobprviamericanexpresscard_nonticked);
			images.add(iInfo);
			count++;
			preference += "28,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.uobprviamericanexpresscard_label, R.drawable.uobprviamericanexpresscard, UOB, R.drawable.uobprviamericanexpresscard_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 29th
		if(uobVisaSignatureCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.uobvisasignaturecard_label, R.drawable.uobvisasignaturecard, UOB, R.drawable.uobvisasignaturecard_nonticked);
			images.add(iInfo);
			count++;
			preference += "29,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.uobvisasignaturecard_label, R.drawable.uobvisasignaturecard, UOB, R.drawable.uobvisasignaturecard_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 30th
		if(uobOneCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.uobonecard_label, R.drawable.uobonecard, UOB, R.drawable.uobonecard_nonticked);
			images.add(iInfo);
			count++;
			preference += "30,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.uobonecard_label, R.drawable.uobonecard, UOB, R.drawable.uobonecard_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 31st
		if(uobPreferredPlatinumCardVisa.isChecked()){
			iInfo = new ImageInfo(R.drawable.uobpreferredplatinumcardvisa_label, R.drawable.uobpreferredplatinumcardvisa, UOB, R.drawable.uobpreferredplatinumcardvisa_nonticked);
			images.add(iInfo);
			count++;
			preference += "31,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.uobpreferredplatinumcardvisa_label, R.drawable.uobpreferredplatinumcardvisa, UOB, R.drawable.uobpreferredplatinumcardvisa_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 32nd
		if(uobPreferredPlatinumCardMasterCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.uobpreferredplatinumcardmastercard_label, R.drawable.uobpreferredplatinumcardmastercard, UOB, R.drawable.uobpreferredplatinumcardmastercard_nonticked);
			images.add(iInfo);
			count++;
			preference += "32,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.uobpreferredplatinumcardmastercard_label, R.drawable.uobpreferredplatinumcardmastercard, UOB, R.drawable.uobpreferredplatinumcardmastercard_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 33rd
		if(uobLadysCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.uobladyscard_label, R.drawable.uobladyscard, UOB, R.drawable.uobladyscard_nonticked);
			images.add(iInfo);
			count++;
			preference += "33,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.uobladyscard_label, R.drawable.uobladyscard, UOB, R.drawable.uobladyscard_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 34th
		if(uobLadysPlatinumCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.uobladysplatinumcard_label, R.drawable.uobladysplatinumcard, UOB, R.drawable.uobladysplatinumcard_nonticked);
			images.add(iInfo);
			count++;
			preference += "34,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.uobladysplatinumcard_label, R.drawable.uobladysplatinumcard, UOB, R.drawable.uobladysplatinumcard_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 35th
		if(uobLadysSolitaireCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.uobladyssolitairecard_label, R.drawable.uobladyssolitairecard, UOB, R.drawable.uobladyssolitairecard_nonticked);
			images.add(iInfo);
			count++;
			preference += "35,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.uobladyssolitairecard_label, R.drawable.uobladyssolitairecard, UOB, R.drawable.uobladyssolitairecard_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 36th
		if(uobVisaInfiniteCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.uobvisainfinitecard_label, R.drawable.uobvisainfinitecard, UOB, R.drawable.uobvisainfinitecard_nonticked);
			images.add(iInfo);
			count++;
			preference += "36,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.uobvisainfinitecard_label, R.drawable.uobvisainfinitecard, UOB, R.drawable.uobvisainfinitecard_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 37th
		if(uobPreferredWorldMasterCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.uobpreferredworldmastercard_label, R.drawable.uobpreferredworldmastercard, UOB, R.drawable.uobpreferredworldmastercard_nonticked);
			images.add(iInfo);
			count++;
			preference += "37,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.uobpreferredworldmastercard_label, R.drawable.uobpreferredworldmastercard, UOB, R.drawable.uobpreferredworldmastercard_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 38th
		if(uobVisaGoldCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.uobvisagoldcard_label, R.drawable.uobvisagoldcard, UOB, R.drawable.uobvisagoldcard_nonticked);
			images.add(iInfo);
			count++;
			preference += "38,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.uobvisagoldcard_label, R.drawable.uobvisagoldcard, UOB, R.drawable.uobvisagoldcard_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 39th
		if(uobVisaClassicCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.uobvisaclassiccard_label, R.drawable.uobvisaclassiccard, UOB, R.drawable.uobvisaclassiccard_nonticked);
			images.add(iInfo);
			count++;
			preference += "39,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.uobvisaclassiccard_label, R.drawable.uobvisaclassiccard, UOB, R.drawable.uobvisaclassiccard_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 40th
		if(uobMasterCardGoldCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.uobmastercardgoldcard_label, R.drawable.uobmastercardgoldcard, UOB, R.drawable.uobmastercardgoldcard_nonticked);
			images.add(iInfo);
			count++;
			preference += "40,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.uobmastercardgoldcard_label, R.drawable.uobmastercardgoldcard, UOB, R.drawable.uobmastercardgoldcard_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 41st
		if(uobMasterCardClassicCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.uobmastercardclassiccard_label, R.drawable.uobmastercardclassiccard, UOB, R.drawable.uobmastercardclassiccard_nonticked);
			images.add(iInfo);
			count++;
			preference += "41,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.uobmastercardclassiccard_label, R.drawable.uobmastercardclassiccard, UOB, R.drawable.uobmastercardclassiccard_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 42nd
		if(metroUOBPlatinumCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.metrouobplatinumcard_label, R.drawable.metrouobplatinumcard, UOB, R.drawable.metrouobplatinumcard_nonticked);
			images.add(iInfo);
			count++;
			preference += "42,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.metrouobplatinumcard_label, R.drawable.metrouobplatinumcard, UOB, R.drawable.metrouobplatinumcard_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 43rd
		if(uobJCBPlatinumCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.uobjcbplatinumcard_label, R.drawable.uobjcbplatinumcard, UOB, R.drawable.uobjcbplatinumcard_nonticked);
			images.add(iInfo);
			count++;
			preference += "43,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.uobjcbplatinumcard_label, R.drawable.uobjcbplatinumcard, UOB, R.drawable.uobjcbplatinumcard_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 44th
		if(uobChinaUnionPayPlatinumCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.uobchinaunionpayplatinumcard_label, R.drawable.uobchinaunionpayplatinumcard, UOB, R.drawable.uobchinaunionpayplatinumcard_nonticked);
			images.add(iInfo);
			count++;
			preference += "44,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.uobchinaunionpayplatinumcard_label, R.drawable.uobchinaunionpayplatinumcard, UOB, R.drawable.uobchinaunionpayplatinumcard_nonticked);
			untickedImages.add(iInfo);
		}
		
		// 45th
		if(uobDirectVisaCard.isChecked()){
			iInfo = new ImageInfo(R.drawable.uobdirectvisacard_label, R.drawable.uobdirectvisacard, UOB, R.drawable.uobdirectvisacard_nonticked);
			images.add(iInfo);
			count++;
			preference += "45,";
		}
		else {
			iInfo = new ImageInfo(R.drawable.uobdirectvisacard_label, R.drawable.uobdirectvisacard, UOB, R.drawable.uobdirectvisacard_nonticked);
			untickedImages.add(iInfo);
		}
		
		SharedPreferences shared = getSharedPreferences(Constants.DEFAULT_SHARE_DATA, 0);
		SharedPreferences.Editor edit = shared.edit();
		edit.putString("cardPref", preference);
		edit.commit();
	}

	private void settinglayout() {
		
		SharedPreferences shared = getSharedPreferences(Constants.DEFAULT_SHARE_DATA, 0);
		final String cardPref = shared.getString("cardPref", "");
		
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
				posbGroup.setVisibility(LinearLayout.VISIBLE);
				uobGroup.setVisibility(LinearLayout.VISIBLE);
				citibank.setBackgroundResource(R.drawable.citibank);
				dbs.setBackgroundResource(R.drawable.dbs);
				ocbc.setBackgroundResource(R.drawable.ocbc);
				posb.setBackgroundResource(R.drawable.posb);
				uob.setBackgroundResource(R.drawable.uob);
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
				posbGroup.setVisibility(LinearLayout.VISIBLE);
				uobGroup.setVisibility(LinearLayout.VISIBLE);
				citibank.setBackgroundResource(R.drawable.citibank);
				dbs.setBackgroundResource(R.drawable.dbs);
				ocbc.setBackgroundResource(R.drawable.ocbc);
				posb.setBackgroundResource(R.drawable.posb);
				uob.setBackgroundResource(R.drawable.uob);
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
		
		ocbcArtsPlatinumMasterCard = (CheckBox)findViewById(R.id.ocbcartsplatinummastercard);
		ocbcArtsPlatinumMasterCard.setOnClickListener(new CheckListener());
		bestOCBCPlatinumMasterCard = (CheckBox)findViewById(R.id.bestocbcplatinummastercard);
		bestOCBCPlatinumMasterCard.setOnClickListener(new CheckListener());
		fairPricePlusCreditDebitCards = (CheckBox)findViewById(R.id.fairpricepluscreditdebitcards);
		fairPricePlusCreditDebitCards.setOnClickListener(new CheckListener());
		ocbcIKEAFriedsVisaCard = (CheckBox)findViewById(R.id.ocbcikeafriedsvisacard);
		ocbcIKEAFriedsVisaCard.setOnClickListener(new CheckListener());
		ocbcNTUVisaGoldCard = (CheckBox)findViewById(R.id.ocbcntuvisagoldcard);
		ocbcNTUVisaGoldCard.setOnClickListener(new CheckListener());
		ocbcPlatinumMasterCard = (CheckBox)findViewById(R.id.ocbcplatinummastercard);
		ocbcPlatinumMasterCard.setOnClickListener(new CheckListener());
		ocbcRobinsonsVisaPlatinumCard = (CheckBox)findViewById(R.id.ocbcrobinsonsvisaplatinumcard);
		ocbcRobinsonsVisaPlatinumCard.setOnClickListener(new CheckListener());
		ocbcSMUDebitCard = (CheckBox)findViewById(R.id.ocbcsmudebitcard);
		ocbcSMUDebitCard.setOnClickListener(new CheckListener());
		ocbcSMUPlatinumMasterCard = (CheckBox)findViewById(R.id.ocbcsmuplatinummastercard);
		ocbcSMUPlatinumMasterCard.setOnClickListener(new CheckListener());
		ocbcTitaniumMasterCard = (CheckBox)findViewById(R.id.ocbctitaniummastercard);
		ocbcTitaniumMasterCard.setOnClickListener(new CheckListener());
		uPlusCreditDebitCards = (CheckBox)findViewById(R.id.upluscreditdebitcards);
		uPlusCreditDebitCards.setOnClickListener(new CheckListener());
		ocbcYesCard = (CheckBox)findViewById(R.id.ocbcyescard);
		ocbcYesCard.setOnClickListener(new CheckListener());
		
		dbsPlatinumMasterCard = (CheckBox)findViewById(R.id.dbsplatinummastercard);
		dbsPlatinumMasterCard.setOnClickListener(new CheckListener());
		
		//----------------------
		singtelUOBVisaPlatinumCard = (CheckBox)findViewById(R.id.singteluobvisaplatinumcard);
		singtelUOBVisaPlatinumCard.setOnClickListener(new CheckListener());
		uobPRVIAmericanExpressCard = (CheckBox)findViewById(R.id.uobprviamericanexpresscard);
		uobPRVIAmericanExpressCard.setOnClickListener(new CheckListener());
		uobVisaSignatureCard = (CheckBox)findViewById(R.id.uobvisasignaturecard);
		uobVisaSignatureCard.setOnClickListener(new CheckListener());
		uobOneCard = (CheckBox)findViewById(R.id.uobonecard);
		uobOneCard.setOnClickListener(new CheckListener());
		uobPreferredPlatinumCardVisa = (CheckBox)findViewById(R.id.uobpreferredplatinumcardvisa);
		uobPreferredPlatinumCardVisa.setOnClickListener(new CheckListener());
		uobPreferredPlatinumCardMasterCard = (CheckBox)findViewById(R.id.uobpreferredplatinumcardmastercard);
		uobPreferredPlatinumCardMasterCard.setOnClickListener(new CheckListener());
		uobLadysCard = (CheckBox)findViewById(R.id.uobladyscard);
		uobLadysCard.setOnClickListener(new CheckListener());
		uobLadysPlatinumCard = (CheckBox)findViewById(R.id.uobladysplatinumcard);
		uobLadysPlatinumCard.setOnClickListener(new CheckListener());
		uobLadysSolitaireCard = (CheckBox)findViewById(R.id.uobladyssolitairecard);
		uobLadysSolitaireCard.setOnClickListener(new CheckListener());
		uobVisaInfiniteCard = (CheckBox)findViewById(R.id.uobvisainfinitecard);
		uobVisaInfiniteCard.setOnClickListener(new CheckListener());
		uobPreferredWorldMasterCard = (CheckBox)findViewById(R.id.uobpreferredworldmastercard);
		uobPreferredWorldMasterCard.setOnClickListener(new CheckListener());
		uobVisaGoldCard = (CheckBox)findViewById(R.id.uobvisagoldcard);
		uobVisaGoldCard.setOnClickListener(new CheckListener());
		uobVisaClassicCard = (CheckBox)findViewById(R.id.uobvisaclassiccard);
		uobVisaClassicCard.setOnClickListener(new CheckListener());
		uobMasterCardGoldCard = (CheckBox)findViewById(R.id.uobmastercardgoldcard);
		uobMasterCardGoldCard.setOnClickListener(new CheckListener());
		uobMasterCardClassicCard = (CheckBox)findViewById(R.id.uobmastercardclassiccard);
		uobMasterCardClassicCard.setOnClickListener(new CheckListener());
		metroUOBPlatinumCard = (CheckBox)findViewById(R.id.metrouobplatinumcard);
		metroUOBPlatinumCard.setOnClickListener(new CheckListener());
		uobJCBPlatinumCard = (CheckBox)findViewById(R.id.uobjcbplatinumcard);
		uobJCBPlatinumCard.setOnClickListener(new CheckListener());
		uobChinaUnionPayPlatinumCard = (CheckBox)findViewById(R.id.uobchinaunionpayplatinumcard);
		uobChinaUnionPayPlatinumCard.setOnClickListener(new CheckListener());
		uobDirectVisaCard = (CheckBox)findViewById(R.id.uobdirectvisacard);
		uobDirectVisaCard.setOnClickListener(new CheckListener());
		
		if(!cardPref.equalsIgnoreCase("")) {
			myCreditCards.setBackgroundResource(R.drawable.button_right_hover);
			allCreditCards.setBackgroundResource(R.drawable.button_left);
			uncheckAll();
			checkApplicableCards(cardPref);
		}
		else {
			myCreditCards.setBackgroundResource(R.drawable.button_right);
			allCreditCards.setBackgroundResource(R.drawable.button_left_hover);
			checkAll();
		}
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
		case Constants.OCBCARTSPLATINUMMASTERCARD:
			ocbcArtsPlatinumMasterCard.setChecked(true);
			break;
		case Constants.BESTOCBCPLATINUMMASTERCARD:
			bestOCBCPlatinumMasterCard.setChecked(true);
			break;
		case Constants.FAIRPRICEPLUSCREDITDEBITCARDS:
			fairPricePlusCreditDebitCards.setChecked(true);
			break;
		case Constants.OCBCIKEAFRIENDSVISACARD:
			ocbcIKEAFriedsVisaCard.setChecked(true);
			break;
		case Constants.OCBCNTUVISAGOLDCARD:
			ocbcNTUVisaGoldCard.setChecked(true);
			break;
		case Constants.OCBCPLATINUMMASTERCARD:
			ocbcPlatinumMasterCard.setChecked(true);
			break;
		case Constants.OCBCROBINSONSVISAPLATINUMCARD:
			ocbcRobinsonsVisaPlatinumCard.setChecked(true);
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
		case Constants.UPLUSCREDITDEBITCARDS:
			uPlusCreditDebitCards.setChecked(true);
			break;
		case Constants.OCBCYESCARD:
			ocbcYesCard.setChecked(true);
			break;
		case Constants.DBSPLATINUMMASTERCARD:
			dbsPlatinumMasterCard.setChecked(true);
			break;
		case Constants.SINGTELUOBVISAPLATINUMCARD:
			singtelUOBVisaPlatinumCard.setChecked(true);
			break;
		case Constants.UOBPRVIAMERICANEXPRESSCARD:
			uobPRVIAmericanExpressCard.setChecked(true);
			break;
		case Constants.UOBVISASIGNATURECARD:
			uobVisaSignatureCard.setChecked(true);
			break;
		case Constants.UOBONECARD:
			uobOneCard.setChecked(true);
			break;
		case Constants.UOBPREFERREDPLATINUMCARDVISA:
			uobPreferredPlatinumCardVisa.setChecked(true);
			break;
		case Constants.UOBPREFERREDPLATINUMCARDMASTERCARD:
			uobPreferredPlatinumCardMasterCard.setChecked(true);
			break;
		case Constants.UOBLADYSCARD:
			uobLadysCard.setChecked(true);
			break;
		case Constants.UOBLADYSPLATINUMCARD:
			uobLadysPlatinumCard.setChecked(true);
			break;
		case Constants.UOBLADYSSOLITAIRECARD:
			uobLadysSolitaireCard.setChecked(true);
			break;
		case Constants.UOBVISAINFINITECARD:
			uobVisaInfiniteCard.setChecked(true);
			break;
		case Constants.UOBPREFERREDWORLDMASTERCARD:
			uobPreferredWorldMasterCard.setChecked(true);
			break;
		case Constants.UOBVISAGOLDCARD:
			uobVisaGoldCard.setChecked(true);
			break;
		case Constants.UOBVISACLASSICCARD:
			uobVisaClassicCard.setChecked(true);
			break;
		case Constants.UOBMASTERCARDGOLDCARD:
			uobMasterCardGoldCard.setChecked(true);
			break;
		case Constants.UOBMASTERCARDCLASSICCARD:
			uobMasterCardClassicCard.setChecked(true);
			break;
		case Constants.METROUOBPLATINUMCARD:
			metroUOBPlatinumCard.setChecked(true);
			break;
		case Constants.UOBJCBPLATINUMCARD:
			uobJCBPlatinumCard.setChecked(true);
			break;
		case Constants.UOBCHINAUNIONPAYPLATINUMCARD:
			uobChinaUnionPayPlatinumCard.setChecked(true);
			break;
		case Constants.UOBDIRECTVISACARD:
			uobDirectVisaCard.setChecked(true);
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

		ocbcArtsPlatinumMasterCard.setChecked(true);
		bestOCBCPlatinumMasterCard.setChecked(true);
		fairPricePlusCreditDebitCards.setChecked(true);
		ocbcIKEAFriedsVisaCard.setChecked(true);
		ocbcNTUVisaGoldCard.setChecked(true);
		ocbcPlatinumMasterCard.setChecked(true);
		ocbcRobinsonsVisaPlatinumCard.setChecked(true);
		ocbcSMUDebitCard.setChecked(true);
		ocbcSMUPlatinumMasterCard.setChecked(true);
		ocbcTitaniumMasterCard.setChecked(true);
		uPlusCreditDebitCards.setChecked(true);
		ocbcYesCard.setChecked(true);

		singtelUOBVisaPlatinumCard.setChecked(true);
		uobPRVIAmericanExpressCard.setChecked(true);
		uobVisaSignatureCard.setChecked(true);
		uobOneCard.setChecked(true);
		uobPreferredPlatinumCardVisa.setChecked(true);
		uobPreferredPlatinumCardMasterCard.setChecked(true);
		uobLadysCard.setChecked(true);
		uobLadysPlatinumCard.setChecked(true);
		uobLadysSolitaireCard.setChecked(true);
		uobVisaInfiniteCard.setChecked(true);
		uobPreferredWorldMasterCard.setChecked(true);
		uobVisaGoldCard.setChecked(true);
		uobVisaClassicCard.setChecked(true);
		uobMasterCardGoldCard.setChecked(true);
		uobMasterCardClassicCard.setChecked(true);
		metroUOBPlatinumCard.setChecked(true);
		uobJCBPlatinumCard.setChecked(true);
		uobChinaUnionPayPlatinumCard.setChecked(true);
		uobDirectVisaCard.setChecked(true);
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

		ocbcArtsPlatinumMasterCard.setChecked(false);
		bestOCBCPlatinumMasterCard.setChecked(false);
		fairPricePlusCreditDebitCards.setChecked(false);
		ocbcIKEAFriedsVisaCard.setChecked(false);
		ocbcNTUVisaGoldCard.setChecked(false);
		ocbcPlatinumMasterCard.setChecked(false);
		ocbcRobinsonsVisaPlatinumCard.setChecked(false);
		ocbcSMUDebitCard.setChecked(false);
		ocbcSMUPlatinumMasterCard.setChecked(false);
		ocbcTitaniumMasterCard.setChecked(false);
		uPlusCreditDebitCards.setChecked(false);
		ocbcYesCard.setChecked(false);

		dbsPlatinumMasterCard.setChecked(false);
		
		singtelUOBVisaPlatinumCard.setChecked(false);
		uobPRVIAmericanExpressCard.setChecked(false);
		uobVisaSignatureCard.setChecked(false);
		uobOneCard.setChecked(false);
		uobPreferredPlatinumCardVisa.setChecked(false);
		uobPreferredPlatinumCardMasterCard.setChecked(false);
		uobLadysCard.setChecked(false);
		uobLadysPlatinumCard.setChecked(false);
		uobLadysSolitaireCard.setChecked(false);
		uobVisaInfiniteCard.setChecked(false);
		uobPreferredWorldMasterCard.setChecked(false);
		uobVisaGoldCard.setChecked(false);
		uobVisaClassicCard.setChecked(false);
		uobMasterCardGoldCard.setChecked(false);
		uobMasterCardClassicCard.setChecked(false);
		metroUOBPlatinumCard.setChecked(false);
		uobJCBPlatinumCard.setChecked(false);
		uobChinaUnionPayPlatinumCard.setChecked(false);
		uobDirectVisaCard.setChecked(false);
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
			
			iInfo = new ImageInfo(R.drawable.citibank_clear_platinum_visa_label, R.drawable.citibank_clear_platinum_visa, CITIBANK, R.drawable.citibank_clear_platinum_visa_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.citibank_dividend_platinum_mastercard_label, R.drawable.citibank_dividend_platinum_mastercard, CITIBANK, R.drawable.citibank_dividend_platinum_mastercard_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.citibank_paragon_mastercard_label, R.drawable.citibank_paragon_mastercard, CITIBANK, R.drawable.citibank_paragon_mastercard_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.citibank_paragon_visa_label, R.drawable.citibank_paragon_visa, CITIBANK, R.drawable.citibank_paragon_visa_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.citibank_premiermiles_visa_signature_label, R.drawable.citibank_premiermiles_visa_signature, CITIBANK, R.drawable.citibank_premiermiles_visa_signature_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.citibank_smrt_card_label, R.drawable.citibank_smrt_card, CITIBANK, R.drawable.citibank_smrt_card_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.dbs_black_american_express_card_label, R.drawable.dbs_black_american_express_card, DBS, R.drawable.dbs_black_american_express_card_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.dbs_live_fresh_platinum_card_label, R.drawable.dbs_live_fresh_platinum_card, DBS, R.drawable.dbs_live_fresh_platinum_card_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbcartsplatinummastercard_label, R.drawable.ocbcartsplatinummastercard, OCBC, R.drawable.ocbcartsplatinummastercard_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.bestocbcplatinummastercard_label, R.drawable.bestocbcplatinummastercard, OCBC, R.drawable.bestocbcplatinummastercard_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.fairpricepluscreditdebitcards_label, R.drawable.fairpricepluscreditdebitcards, OCBC, R.drawable.fairpricepluscreditdebitcards_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbcikeafriedsvisacard_label, R.drawable.ocbcikeafriedsvisacard, OCBC, R.drawable.ocbcikeafriedsvisacard_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbcntuvisagoldcard_label, R.drawable.ocbcntuvisagoldcard, OCBC, R.drawable.ocbcntuvisagoldcard_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbcplatinummastercard_label, R.drawable.ocbcplatinummastercard, OCBC, R.drawable.ocbcplatinummastercard_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbcrobinsonsvisaplatinumcard_label, R.drawable.ocbcrobinsonsvisaplatinumcard, OCBC, R.drawable.ocbcrobinsonsvisaplatinumcard_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbcsmudebitcard_label, R.drawable.ocbcsmudebitcard, OCBC, R.drawable.ocbcsmudebitcard_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbcsmuplatinummastercard_label, R.drawable.ocbcsmuplatinummastercard, OCBC, R.drawable.ocbcsmuplatinummastercard_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbctitaniummastercard_label, R.drawable.ocbctitaniummastercard, OCBC, R.drawable.ocbctitaniummastercard_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.upluscreditdebitcards_label, R.drawable.upluscreditdebitcards, OCBC, R.drawable.upluscreditdebitcards_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbcyescard_label, R.drawable.ocbcyescard, OCBC, R.drawable.ocbcyescard_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.dbs_platinum_mastercard_label, R.drawable.dbs_platinum_mastercard, DBS, R.drawable.dbs_platinum_mastercard_nonticked);
			defaultAll.add(iInfo);
			
			iInfo = new ImageInfo(R.drawable.singteluobvisaplatinumcard_label, R.drawable.singteluobvisaplatinumcard, UOB, R.drawable.singteluobvisaplatinumcard_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uobprviamericanexpresscard_label, R.drawable.uobprviamericanexpresscard, UOB, R.drawable.uobprviamericanexpresscard_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uobvisasignaturecard_label, R.drawable.uobvisasignaturecard, UOB, R.drawable.uobvisasignaturecard_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uobonecard_label, R.drawable.uobonecard, UOB, R.drawable.uobonecard_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uobpreferredplatinumcardvisa_label, R.drawable.uobpreferredplatinumcardvisa, UOB, R.drawable.uobpreferredplatinumcardvisa_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uobpreferredplatinumcardmastercard_label, R.drawable.uobpreferredplatinumcardmastercard, UOB, R.drawable.uobpreferredplatinumcardmastercard_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uobladyscard_label, R.drawable.uobladyscard, UOB, R.drawable.uobladyscard_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uobladysplatinumcard_label, R.drawable.uobladysplatinumcard, UOB, R.drawable.uobladysplatinumcard_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uobladyssolitairecard_label, R.drawable.uobladyssolitairecard, UOB, R.drawable.uobladyssolitairecard_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uobvisainfinitecard_label, R.drawable.uobvisainfinitecard, UOB, R.drawable.uobvisainfinitecard_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uobpreferredworldmastercard_label, R.drawable.uobpreferredworldmastercard, UOB, R.drawable.uobpreferredworldmastercard_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uobvisagoldcard_label, R.drawable.uobvisagoldcard, UOB, R.drawable.uobvisagoldcard_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uobvisaclassiccard_label, R.drawable.uobvisaclassiccard, UOB, R.drawable.uobvisaclassiccard_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uobmastercardgoldcard_label, R.drawable.uobmastercardgoldcard, UOB, R.drawable.uobmastercardgoldcard_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uobmastercardclassiccard_label, R.drawable.uobmastercardclassiccard, UOB, R.drawable.uobmastercardclassiccard_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.metrouobplatinumcard_label, R.drawable.metrouobplatinumcard, UOB, R.drawable.metrouobplatinumcard_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uobjcbplatinumcard_label, R.drawable.uobjcbplatinumcard, UOB, R.drawable.uobjcbplatinumcard_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uobchinaunionpayplatinumcard_label, R.drawable.uobchinaunionpayplatinumcard, UOB, R.drawable.uobchinaunionpayplatinumcard_nonticked);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uobdirectvisacard_label, R.drawable.uobdirectvisacard, UOB, R.drawable.uobdirectvisacard_nonticked);
			defaultAll.add(iInfo);
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
		
		try {
			if(bankQ.charAt(bankQ.length()-1) == ',') {
				bankQ = bankQ.substring(0, bankQ.length()-1);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
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