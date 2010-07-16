package com.insidetip.singtel.screen;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

import com.insidetip.singtel.info.ImageInfo;

public class SettingsPage extends SingtelDiningActivity {

	public static SettingsPage instance;
	
	public static ArrayList<ImageInfo> images = new ArrayList<ImageInfo>();
	public static String bankQuery = "Citibank,DBS,OCBC,UOB";
	private static ArrayList<ImageInfo> defaultAll = new ArrayList<ImageInfo>();
	
	private final static String CITIBANK = "Citibank";
	private final static String DBS = "DBS";
	private final static String OCBC = "OCBC";
	private final static String UOB = "UOB";
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(R.style.Theme_Translucent);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.settings);
		
		instance = this;
		initActivity(instance);
		
		final Button citibank = (Button)findViewById(R.id.citibankButton);
		final Button dbs = (Button)findViewById(R.id.dbsButton);
		final Button ocbc = (Button)findViewById(R.id.ocbcButton);
		final Button uob = (Button)findViewById(R.id.uobButton);
		
		citibank.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				citibank.setBackgroundResource(R.drawable.citibank_hover);
				dbs.setBackgroundResource(R.drawable.dbs);
				ocbc.setBackgroundResource(R.drawable.ocbc);
				uob.setBackgroundResource(R.drawable.uob);
			}
		});
		
		dbs.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				citibank.setBackgroundResource(R.drawable.citibank);
				dbs.setBackgroundResource(R.drawable.dbs_hover);
				ocbc.setBackgroundResource(R.drawable.ocbc);
				uob.setBackgroundResource(R.drawable.uob);
			}
		});
		
		ocbc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				citibank.setBackgroundResource(R.drawable.citibank);
				dbs.setBackgroundResource(R.drawable.dbs);
				ocbc.setBackgroundResource(R.drawable.ocbc_hover);
				uob.setBackgroundResource(R.drawable.uob);
			}
		});
		
		uob.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				citibank.setBackgroundResource(R.drawable.citibank);
				dbs.setBackgroundResource(R.drawable.dbs);
				ocbc.setBackgroundResource(R.drawable.ocbc);
				uob.setBackgroundResource(R.drawable.uob_hover);
			}
		});
		
		Button doneButton = (Button)findViewById(R.id.settingsDoneButton);
		doneButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				instance.finish();
			}
		});
		
		settinglayout();
	}

	private void settinglayout() {
		
		CheckBox cbClearPlatinumVisa = (CheckBox)findViewById(R.id.clearplatinumvisa);
		cbClearPlatinumVisa.setOnClickListener(new CheckListener());
		CheckBox cbDividendPlatinum = (CheckBox)findViewById(R.id.dividendplatinum);
		cbDividendPlatinum.setOnClickListener(new CheckListener());
		CheckBox cbParagonMasterCard = (CheckBox)findViewById(R.id.paragonmastercard);
		cbParagonMasterCard.setOnClickListener(new CheckListener());
		CheckBox cbParagonVisa = (CheckBox)findViewById(R.id.paragonvisa);
		cbParagonVisa.setOnClickListener(new CheckListener());
		CheckBox cbPremiereMilesVisa = (CheckBox)findViewById(R.id.premieremilesvisa);
		cbPremiereMilesVisa.setOnClickListener(new CheckListener());
		CheckBox cbSMRTCard = (CheckBox)findViewById(R.id.smrtcard);
		cbSMRTCard.setOnClickListener(new CheckListener());
		
		CheckBox dbsBlackAmericanExpress = (CheckBox)findViewById(R.id.blackamericanexpress);
		dbsBlackAmericanExpress.setOnClickListener(new CheckListener());
		CheckBox dbsLiveFreshPlatinum = (CheckBox)findViewById(R.id.livefreshplatinum);
		dbsLiveFreshPlatinum.setOnClickListener(new CheckListener());
		CheckBox dbsPlatinumMasterCard = (CheckBox)findViewById(R.id.dbsplatinummastercard);
		dbsPlatinumMasterCard.setOnClickListener(new CheckListener());
		
		CheckBox ocbcArtsPlatinumCard = (CheckBox)findViewById(R.id.artsplatinumcard);
		ocbcArtsPlatinumCard.setOnClickListener(new CheckListener());
		CheckBox ocbcBestDenkiPlatinumCard = (CheckBox)findViewById(R.id.bestdenkiplatinumcard);
		ocbcBestDenkiPlatinumCard.setOnClickListener(new CheckListener());
		CheckBox ocbcClassicVisaCard = (CheckBox)findViewById(R.id.classicvisacard);
		ocbcClassicVisaCard.setOnClickListener(new CheckListener());
		CheckBox ocbcDebitCard = (CheckBox)findViewById(R.id.debitcard);
		ocbcDebitCard.setOnClickListener(new CheckListener());
		CheckBox ocbcFairPricePlusVisaCard = (CheckBox)findViewById(R.id.fairpriceplusvisacard);
		ocbcFairPricePlusVisaCard.setOnClickListener(new CheckListener());
		CheckBox ocbcGoldMasterCard = (CheckBox)findViewById(R.id.goldmastercard);
		ocbcGoldMasterCard.setOnClickListener(new CheckListener());
		CheckBox ocbcIkeaFriendsVisaCard = (CheckBox)findViewById(R.id.ikeafriendsvisacard);
		ocbcIkeaFriendsVisaCard.setOnClickListener(new CheckListener());
		CheckBox ocbcNTUVisaClassicCard = (CheckBox)findViewById(R.id.ntuvisaclassiccard);
		ocbcNTUVisaClassicCard.setOnClickListener(new CheckListener());
		CheckBox ocbcNTUVisaGoldCard = (CheckBox)findViewById(R.id.ntuvisagoldcard);
		ocbcNTUVisaGoldCard.setOnClickListener(new CheckListener());
		CheckBox ocbcPlatinumMasterCard = (CheckBox)findViewById(R.id.platinummastercard);
		ocbcPlatinumMasterCard.setOnClickListener(new CheckListener());
		CheckBox ocbcRobinsonsPlatinumCard = (CheckBox)findViewById(R.id.robinsonsplatinumcard);
		ocbcRobinsonsPlatinumCard.setOnClickListener(new CheckListener());
		CheckBox ocbcSMUDebitCard = (CheckBox)findViewById(R.id.smudebitcards);
		ocbcSMUDebitCard.setOnClickListener(new CheckListener());
		CheckBox ocbcSMUPlatinumMasterCard = (CheckBox)findViewById(R.id.smuplatinummastercard);
		ocbcSMUPlatinumMasterCard.setOnClickListener(new CheckListener());
		CheckBox ocbcTitaniumMasterCard = (CheckBox)findViewById(R.id.titaniummastercard);
		ocbcTitaniumMasterCard.setOnClickListener(new CheckListener());
		CheckBox ocbcUPlusVisaCard = (CheckBox)findViewById(R.id.uplusvisacard);
		ocbcUPlusVisaCard.setOnClickListener(new CheckListener());
		CheckBox ocbcUPlusPlatinumCard = (CheckBox)findViewById(R.id.uplusplatinumcard);
		ocbcUPlusPlatinumCard.setOnClickListener(new CheckListener());
		CheckBox ocbcYesDebitCard = (CheckBox)findViewById(R.id.yesdebitcard);
		ocbcYesDebitCard.setOnClickListener(new CheckListener());
		CheckBox uobDirectVisaCard = (CheckBox)findViewById(R.id.directvisacard);
		uobDirectVisaCard.setOnClickListener(new CheckListener());
		CheckBox uobLadysCard = (CheckBox)findViewById(R.id.ladyscard);
		uobLadysCard.setOnClickListener(new CheckListener());
		CheckBox uobMasterCardClassicCard = (CheckBox)findViewById(R.id.mastercardclassiccard);
		uobMasterCardClassicCard.setOnClickListener(new CheckListener());
		CheckBox uobMasterCardGoldCard = (CheckBox)findViewById(R.id.mastercardgoldcard);
		uobMasterCardGoldCard.setOnClickListener(new CheckListener());
		CheckBox uobOneCard = (CheckBox)findViewById(R.id.onecard);
		uobOneCard.setOnClickListener(new CheckListener());
		CheckBox uobPreferredWorldCard = (CheckBox)findViewById(R.id.preferredworld);
		uobPreferredWorldCard.setOnClickListener(new CheckListener());
		CheckBox uobPRVIVisaAmericanCard = (CheckBox)findViewById(R.id.prvivisaamerican);
		uobPRVIVisaAmericanCard.setOnClickListener(new CheckListener());
		CheckBox uobVisaClassicCard = (CheckBox)findViewById(R.id.visaclassiccard);
		uobVisaClassicCard.setOnClickListener(new CheckListener());
		CheckBox uobVisaGoldCard = (CheckBox)findViewById(R.id.visagoldcard);
		uobVisaGoldCard.setOnClickListener(new CheckListener());
		CheckBox uobVisaInfiniteCard = (CheckBox)findViewById(R.id.visainfinitecard);
		uobVisaInfiniteCard.setOnClickListener(new CheckListener());
		CheckBox uobVisaSignatureCard = (CheckBox)findViewById(R.id.visasignaturecard);
		uobVisaSignatureCard.setOnClickListener(new CheckListener());
	}
	
	private class CheckListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			ImageInfo iInfo;
			switch(v.getId()) {
			case R.id.clearplatinumvisa:
				iInfo = new ImageInfo(R.drawable.citibank_clear_platinum_visa_label, R.drawable.citibank_clear_platinum_visa, CITIBANK);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
					System.out.println("Not Remove");
				}
				else {
					System.out.println("Remove");
					images.remove(iInfo);
				}
				break;
			case R.id.dividendplatinum:
				iInfo = new ImageInfo(R.drawable.citibank_dividend_platinum_mastercard_label, R.drawable.citibank_dividend_platinum_mastercard, CITIBANK);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.paragonmastercard:
				iInfo = new ImageInfo(R.drawable.citibank_paragon_mastercard_label, R.drawable.citibank_paragon_mastercard, CITIBANK);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.paragonvisa:
				iInfo = new ImageInfo(R.drawable.citibank_paragon_visa_label, R.drawable.citibank_paragon_visa, CITIBANK);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.premieremilesvisa:
				iInfo = new ImageInfo(R.drawable.citibank_premiermiles_visa_signature_label, R.drawable.citibank_premiermiles_visa_signature, CITIBANK);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.smrtcard:
				iInfo = new ImageInfo(R.drawable.citibank_smrt_card_label, R.drawable.citibank_smrt_card, CITIBANK);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.blackamericanexpress:
				iInfo = new ImageInfo(R.drawable.dbs_black_american_express_card_label, R.drawable.dbs_black_american_express_card, DBS);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.livefreshplatinum:
				iInfo = new ImageInfo(R.drawable.dbs_live_fresh_platinum_card_label, R.drawable.dbs_live_fresh_platinum_card, DBS);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.dbsplatinummastercard:
				iInfo = new ImageInfo(R.drawable.dbs_platinum_mastercard_label, R.drawable.dbs_platinum_mastercard, DBS);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.artsplatinumcard:
				iInfo = new ImageInfo(R.drawable.ocbc_arts_platinum_card_label, R.drawable.ocbc_arts_platinum_card, OCBC);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.bestdenkiplatinumcard:
				iInfo = new ImageInfo(R.drawable.ocbc_best_denki_platinum_card_label, R.drawable.ocbc_best_denki_platinum_card, OCBC);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.classicvisacard:
				iInfo = new ImageInfo(R.drawable.ocbc_classic_visa_card_label, R.drawable.ocbc_classic_visa_card, OCBC);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.debitcard:
				iInfo = new ImageInfo(R.drawable.ocbc_debit_card_label, R.drawable.ocbc_debit_card, OCBC);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.fairpriceplusvisacard:
				iInfo = new ImageInfo(R.drawable.ocbc_fairpriceplus_visa_card_label, R.drawable.ocbc_fairpriceplus_visa_card, OCBC);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.goldmastercard:
				iInfo = new ImageInfo(R.drawable.ocbc_gold_mastercard_label, R.drawable.ocbc_gold_mastercard, OCBC);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.ikeafriendsvisacard:
				iInfo = new ImageInfo(R.drawable.ocbc_ikea_friends_visacard_label, R.drawable.ocbc_ikea_friends_visa_card, OCBC);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.ntuvisaclassiccard:
				iInfo = new ImageInfo(R.drawable.ocbc_ntu_visa_classic_card_label, R.drawable.ocbc_ntu_visa_classic_card, OCBC);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.ntuvisagoldcard:
				iInfo = new ImageInfo(R.drawable.ocbc_ntu_visa_gold_card_label, R.drawable.ocbc_ntu_visa_gold_card, OCBC);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.platinummastercard:
				iInfo = new ImageInfo(R.drawable.ocbc_platinum_mastercard_label, R.drawable.ocbc_platinum_mastercard, OCBC);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.robinsonsplatinumcard:
				iInfo = new ImageInfo(R.drawable.ocbc_robinsons_platinum_card_label, R.drawable.ocbc_robinsons_platinum_card, OCBC);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.smudebitcards:
				iInfo = new ImageInfo(R.drawable.ocbc_smu_debit_cards_label, R.drawable.ocbc_smu_debit_cards, OCBC);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.smuplatinummastercard:
				iInfo = new ImageInfo(R.drawable.ocbc_smu_platinum_mastercard_label, R.drawable.ocbc_smu_platinum_mastercard, OCBC);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.titaniummastercard:
				iInfo = new ImageInfo(R.drawable.ocbc_titanium_mastercard_label, R.drawable.ocbc_titanium_mastercard, OCBC);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.uplusvisacard:
				iInfo = new ImageInfo(R.drawable.ocbc_uplus_visa_card_label, R.drawable.ocbc_uplus_visa_card, OCBC);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.uplusplatinumcard:
				iInfo = new ImageInfo(R.drawable.ocbc_uplus_platinum_card_label, R.drawable.ocbc_uplus_platinum_card, OCBC);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.yesdebitcard:
				iInfo = new ImageInfo(R.drawable.ocbc_yes_debit_card_label, R.drawable.ocbc_yes_debit_card, OCBC);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.directvisacard:
				iInfo = new ImageInfo(R.drawable.uob_direct_visa_card_label, R.drawable.uob_direct_visa_card, UOB);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.ladyscard:
				iInfo = new ImageInfo(R.drawable.uob_ladys_card_label, R.drawable.uob_ladys_card, UOB);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.mastercardclassiccard:
				iInfo = new ImageInfo(R.drawable.uob_mastercard_classic_card_label, R.drawable.uob_mastercard_classic_card, UOB);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.mastercardgoldcard:
				iInfo = new ImageInfo(R.drawable.uob_mastercard_gold_card_label, R.drawable.uob_mastercard_gold_card, UOB);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.onecard:
				iInfo = new ImageInfo(R.drawable.uob_one_card_label, R.drawable.uob_one_card, UOB);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.preferredworld:
				iInfo = new ImageInfo(R.drawable.uob_preferred_world_mastercard_label, R.drawable.uob_preferred_world_mastercard, UOB);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.prvivisaamerican:
				iInfo = new ImageInfo(R.drawable.uob_prvi_visa_american_express_card_label, R.drawable.uob_prvi_visa_american_express_card, UOB);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.visaclassiccard:
				iInfo = new ImageInfo(R.drawable.uob_visa_classic_card_label, R.drawable.uob_visa_classic_card, UOB);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.visagoldcard:
				iInfo = new ImageInfo(R.drawable.uob_visa_gold_card_label, R.drawable.uob_visa_gold_card, UOB);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.visainfinitecard:
				iInfo = new ImageInfo(R.drawable.uob_visa_infinite_card_label, R.drawable.uob_visa_infinite_card, UOB);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;
			case R.id.visasignaturecard:
				iInfo = new ImageInfo(R.drawable.uob_visa_signature_card_label, R.drawable.uob_visa_signature_card, UOB);
				if(((CheckBox)v).isChecked()) {
					images.add(iInfo);
				}
				else {
					images.remove(iInfo);
				}
				break;				
			}
		}
	}
	
	public static ArrayList<ImageInfo> getDefaultCards() {
		if(defaultAll.isEmpty()) {
			ImageInfo iInfo;
			
			iInfo = new ImageInfo(R.drawable.citibank_clear_platinum_visa_label, R.drawable.citibank_clear_platinum_visa, CITIBANK);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.citibank_dividend_platinum_mastercard_label, R.drawable.citibank_dividend_platinum_mastercard, CITIBANK);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.citibank_paragon_mastercard_label, R.drawable.citibank_paragon_mastercard, CITIBANK);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.citibank_paragon_visa_label, R.drawable.citibank_paragon_visa, CITIBANK);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.citibank_premiermiles_visa_signature_label, R.drawable.citibank_premiermiles_visa_signature, CITIBANK);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.citibank_smrt_card_label, R.drawable.citibank_smrt_card, CITIBANK);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.dbs_black_american_express_card_label, R.drawable.dbs_black_american_express_card, DBS);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.dbs_live_fresh_platinum_card_label, R.drawable.dbs_live_fresh_platinum_card, DBS);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.dbs_platinum_mastercard_label, R.drawable.dbs_platinum_mastercard, DBS);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbc_arts_platinum_card_label, R.drawable.ocbc_arts_platinum_card, OCBC);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbc_best_denki_platinum_card_label, R.drawable.ocbc_best_denki_platinum_card, OCBC);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbc_classic_visa_card_label, R.drawable.ocbc_classic_visa_card, OCBC);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbc_debit_card_label, R.drawable.ocbc_debit_card, OCBC);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbc_fairpriceplus_visa_card_label, R.drawable.ocbc_fairpriceplus_visa_card, OCBC);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbc_gold_mastercard_label, R.drawable.ocbc_gold_mastercard, OCBC);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbc_ikea_friends_visacard_label, R.drawable.ocbc_ikea_friends_visa_card, OCBC);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbc_ntu_visa_classic_card_label, R.drawable.ocbc_ntu_visa_classic_card, OCBC);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbc_ntu_visa_gold_card_label, R.drawable.ocbc_ntu_visa_gold_card, OCBC);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbc_platinum_mastercard_label, R.drawable.ocbc_platinum_mastercard, OCBC);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbc_robinsons_platinum_card_label, R.drawable.ocbc_robinsons_platinum_card, OCBC);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbc_smu_debit_cards_label, R.drawable.ocbc_smu_debit_cards, OCBC);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbc_smu_platinum_mastercard_label, R.drawable.ocbc_smu_platinum_mastercard, OCBC);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbc_titanium_mastercard_label, R.drawable.ocbc_titanium_mastercard, OCBC);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbc_uplus_visa_card_label, R.drawable.ocbc_uplus_visa_card, OCBC);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbc_uplus_platinum_card_label, R.drawable.ocbc_uplus_platinum_card, OCBC);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.ocbc_yes_debit_card_label, R.drawable.ocbc_yes_debit_card, OCBC);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uob_direct_visa_card_label, R.drawable.uob_direct_visa_card, UOB);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uob_ladys_card_label, R.drawable.uob_ladys_card, UOB);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uob_mastercard_classic_card_label, R.drawable.uob_mastercard_classic_card, UOB);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uob_mastercard_gold_card_label, R.drawable.uob_mastercard_gold_card, UOB);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uob_one_card_label, R.drawable.uob_one_card, UOB);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uob_preferred_world_mastercard_label, R.drawable.uob_preferred_world_mastercard, UOB);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uob_prvi_visa_american_express_card_label, R.drawable.uob_prvi_visa_american_express_card, UOB);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uob_visa_classic_card_label, R.drawable.uob_visa_classic_card, UOB);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uob_visa_gold_card_label, R.drawable.uob_visa_gold_card, UOB);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uob_visa_infinite_card_label, R.drawable.uob_visa_infinite_card, UOB);
			defaultAll.add(iInfo);
			iInfo = new ImageInfo(R.drawable.uob_visa_signature_card_label, R.drawable.uob_visa_signature_card, UOB);
			defaultAll.add(iInfo);
			images = defaultAll;
		}
		return defaultAll;
	}
}