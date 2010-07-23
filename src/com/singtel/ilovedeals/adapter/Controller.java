package com.singtel.ilovedeals.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;

import com.singtel.ilovedeals.info.ImageInfo;
import com.singtel.ilovedeals.info.MerchantInfo;
import com.singtel.ilovedeals.map.MapLocationViewer;
import com.singtel.ilovedeals.screen.DescriptionPage;
import com.singtel.ilovedeals.screen.MapPage;
import com.singtel.ilovedeals.screen.R;
import com.singtel.ilovedeals.screen.SingtelDiningMainPage;
import com.singtel.ilovedeals.util.Constants;

public class Controller {
	
	private final static String CITIBANK = "Citibank";
	private final static String DBS = "DBS";
	private final static String OCBC = "OCBC";
	private final static String UOB = "UOB";

	public static void displayMapScreen(Context context) {
		if(context instanceof DescriptionPage) {
			ArrayList<MerchantInfo> mapList = new ArrayList<MerchantInfo>();
			mapList.add(DescriptionPage.merchantInfo);
			MapLocationViewer.setMapLocations(mapList, 0, false);
			Intent intent = new Intent(context, MapPage.class);
			context.startActivity(intent);
		}
		else if(context instanceof SingtelDiningMainPage) {
			MapLocationViewer.setMapLocations(SingtelDiningMainPage.merchantList, 0, false);
			Intent intent = new Intent(context, MapPage.class);
			context.startActivity(intent);
		}
	}
	
	public static ImageInfo getCorrespondingImage(String value) {
		ImageInfo iInfo = null;
		int id = Integer.parseInt(value);
		switch(id) {
			case Constants.CBCLEARPLATINUMVISA:
				iInfo = new ImageInfo(R.drawable.citibank_clear_platinum_visa_label, R.drawable.citibank_clear_platinum_visa, CITIBANK, R.drawable.citibank_clear_platinum_visa_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.CBDIVIDENDPLATINUM:
				iInfo = new ImageInfo(R.drawable.citibank_dividend_platinum_mastercard_label, R.drawable.citibank_dividend_platinum_mastercard, CITIBANK, R.drawable.citibank_dividend_platinum_mastercard_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.CBPARAGONMASTERCARD:
				iInfo = new ImageInfo(R.drawable.citibank_paragon_mastercard_label, R.drawable.citibank_paragon_mastercard, CITIBANK, R.drawable.citibank_paragon_mastercard_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.CBPARAGONVISA:
				iInfo = new ImageInfo(R.drawable.citibank_paragon_visa_label, R.drawable.citibank_paragon_visa, CITIBANK, R.drawable.citibank_paragon_visa_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.CBPREMIEREMILESVISA:
				iInfo = new ImageInfo(R.drawable.citibank_premiermiles_visa_signature_label, R.drawable.citibank_premiermiles_visa_signature, CITIBANK, R.drawable.citibank_premiermiles_visa_signature_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.CBSMRTCARD:
				iInfo = new ImageInfo(R.drawable.citibank_smrt_card_label, R.drawable.citibank_smrt_card, CITIBANK, R.drawable.citibank_smrt_card_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.DBSBLACKAMERICANEXPRESS:
				iInfo = new ImageInfo(R.drawable.dbs_black_american_express_card_label, R.drawable.dbs_black_american_express_card, DBS, R.drawable.dbs_black_american_express_card_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.DBSLIVEFRESHPLATINUM:
				iInfo = new ImageInfo(R.drawable.dbs_live_fresh_platinum_card_label, R.drawable.dbs_live_fresh_platinum_card, DBS, R.drawable.dbs_live_fresh_platinum_card_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.DBSPLATINUMMASTERCARD:
				iInfo = new ImageInfo(R.drawable.dbs_platinum_mastercard_label, R.drawable.dbs_platinum_mastercard, DBS, R.drawable.dbs_platinum_mastercard_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.OCBCARTSPLATINUMCARD:
				iInfo = new ImageInfo(R.drawable.ocbc_arts_platinum_card_label, R.drawable.ocbc_arts_platinum_card, OCBC, R.drawable.ocbc_arts_platinum_card_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.OCBCBESTDENKIPLATINUMCARD:
				iInfo = new ImageInfo(R.drawable.ocbc_best_denki_platinum_card_label, R.drawable.ocbc_best_denki_platinum_card, OCBC, R.drawable.ocbc_best_denki_platinum_card_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.OCBCCLASSICVISACARD:
				iInfo = new ImageInfo(R.drawable.ocbc_classic_visa_card_label, R.drawable.ocbc_classic_visa_card, OCBC, R.drawable.ocbc_classic_visa_card_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.OCBCDEBITCARD:
				iInfo = new ImageInfo(R.drawable.ocbc_debit_card_label, R.drawable.ocbc_debit_card, OCBC, R.drawable.ocbc_debit_card_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.OCBCFAIRPRICEPLUSVISACARD:
				iInfo = new ImageInfo(R.drawable.ocbc_fairpriceplus_visa_card_label, R.drawable.ocbc_fairpriceplus_visa_card, OCBC, R.drawable.ocbc_fairpriceplus_visa_card_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.OCBCGOLDMASTERCARD:
				iInfo = new ImageInfo(R.drawable.ocbc_gold_mastercard_label, R.drawable.ocbc_gold_mastercard, OCBC, R.drawable.ocbc_gold_mastercard_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.OCBCIKEAFRIENDSVISACARD:
				iInfo = new ImageInfo(R.drawable.ocbc_ikea_friends_visacard_label, R.drawable.ocbc_ikea_friends_visa_card, OCBC, R.drawable.ocbc_ikea_friends_visa_card_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.OCBCNTUVISACLASSICCARD:
				iInfo = new ImageInfo(R.drawable.ocbc_ntu_visa_classic_card_label, R.drawable.ocbc_ntu_visa_classic_card, OCBC, R.drawable.ocbc_ntu_visa_classic_card_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.OCBCNTUVISAGOLDCARD:
				iInfo = new ImageInfo(R.drawable.ocbc_ntu_visa_gold_card_label, R.drawable.ocbc_ntu_visa_gold_card, OCBC, R.drawable.ocbc_ntu_visa_gold_card_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.OCBCPLATINUMMASTERCARD:
				iInfo = new ImageInfo(R.drawable.ocbc_platinum_mastercard_label, R.drawable.ocbc_platinum_mastercard, OCBC, R.drawable.ocbc_platinum_mastercard_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.OCBCROBINSONSPLATINUMCARD:
				iInfo = new ImageInfo(R.drawable.ocbc_robinsons_platinum_card_label, R.drawable.ocbc_robinsons_platinum_card, OCBC, R.drawable.ocbc_robinsons_platinum_card_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.OCBCSMUDEBITCARD:
				iInfo = new ImageInfo(R.drawable.ocbc_smu_debit_cards_label, R.drawable.ocbc_smu_debit_cards, OCBC, R.drawable.ocbc_smu_debit_cards_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.OCBCSMUPLATINUMMASTERCARD:
				iInfo = new ImageInfo(R.drawable.ocbc_smu_platinum_mastercard_label, R.drawable.ocbc_smu_platinum_mastercard, OCBC, R.drawable.ocbc_smu_platinum_mastercard_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.OCBCTITANIUMMASTERCARD:
				iInfo = new ImageInfo(R.drawable.ocbc_titanium_mastercard_label, R.drawable.ocbc_titanium_mastercard, OCBC, R.drawable.ocbc_titanium_mastercard_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.OCBCUPLUSVISACARD:
				iInfo = new ImageInfo(R.drawable.ocbc_uplus_visa_card_label, R.drawable.ocbc_uplus_visa_card, OCBC, R.drawable.ocbc_uplus_visa_card_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.OCBCUPLUSPLATINUMCARD:
				iInfo = new ImageInfo(R.drawable.ocbc_uplus_platinum_card_label, R.drawable.ocbc_uplus_platinum_card, OCBC, R.drawable.ocbc_uplus_platinum_card_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.OCBCYESDEBITCARD:
				iInfo = new ImageInfo(R.drawable.ocbc_yes_debit_card_label, R.drawable.ocbc_yes_debit_card, OCBC, R.drawable.ocbc_yes_debit_card_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.SINGTELUOBVISAPLATINUMCARD:
				iInfo = new ImageInfo(R.drawable.singteluobvisaplatinumcard_label, R.drawable.singteluobvisaplatinumcard, UOB, R.drawable.singteluobvisaplatinumcard_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.UOBPRVIAMERICANEXPRESSCARD:
				iInfo = new ImageInfo(R.drawable.uobprviamericanexpresscard_label, R.drawable.uobprviamericanexpresscard, UOB, R.drawable.uobprviamericanexpresscard_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.UOBVISASIGNATURECARD:
				iInfo = new ImageInfo(R.drawable.uobvisasignaturecard_label, R.drawable.uobvisasignaturecard, UOB, R.drawable.uobvisasignaturecard_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.UOBONECARD:
				iInfo = new ImageInfo(R.drawable.uobonecard_label, R.drawable.uobonecard, UOB, R.drawable.uobonecard_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.UOBPREFERREDPLATINUMCARDVISA:
				iInfo = new ImageInfo(R.drawable.uobpreferredplatinumcardvisa_label, R.drawable.uobpreferredplatinumcardvisa, UOB, R.drawable.uobpreferredplatinumcardvisa_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.UOBPREFERREDPLATINUMCARDMASTERCARD:
				iInfo = new ImageInfo(R.drawable.uobpreferredplatinumcardmastercard_label, R.drawable.uobpreferredplatinumcardmastercard, UOB, R.drawable.uobpreferredplatinumcardmastercard_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.UOBLADYSCARD:
				iInfo = new ImageInfo(R.drawable.uobladyscard_label, R.drawable.uobladyscard, UOB, R.drawable.uobladyscard_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.UOBLADYSPLATINUMCARD:
				iInfo = new ImageInfo(R.drawable.uobladysplatinumcard_label, R.drawable.uobladysplatinumcard, UOB, R.drawable.uobladysplatinumcard_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.UOBLADYSSOLITAIRECARD:
				iInfo = new ImageInfo(R.drawable.uobladyssolitairecard_label, R.drawable.uobladyssolitairecard, UOB, R.drawable.uobladyssolitairecard_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.UOBVISAINFINITECARD:
				iInfo = new ImageInfo(R.drawable.uobvisainfinitecard_label, R.drawable.uobvisainfinitecard, UOB, R.drawable.uobvisainfinitecard_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.UOBPREFERREDWORLDMASTERCARD:
				iInfo = new ImageInfo(R.drawable.uobpreferredworldmastercard_label, R.drawable.uobpreferredworldmastercard, UOB, R.drawable.uobpreferredworldmastercard_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.UOBVISAGOLDCARD:
				iInfo = new ImageInfo(R.drawable.uobvisagoldcard_label, R.drawable.uobvisagoldcard, UOB, R.drawable.uobvisagoldcard_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.UOBVISACLASSICCARD:
				iInfo = new ImageInfo(R.drawable.uobvisaclassiccard_label, R.drawable.uobvisaclassiccard, UOB, R.drawable.uobvisaclassiccard_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.UOBMASTERCARDGOLDCARD:
				iInfo = new ImageInfo(R.drawable.uobmastercardgoldcard_label, R.drawable.uobmastercardgoldcard, UOB, R.drawable.uobmastercardgoldcard_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.UOBMASTERCARDCLASSICCARD:
				iInfo = new ImageInfo(R.drawable.uobmastercardclassiccard_label, R.drawable.uobmastercardclassiccard, UOB, R.drawable.uobmastercardclassiccard_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.METROUOBPLATINUMCARD:
				iInfo = new ImageInfo(R.drawable.metrouobplatinumcard_label, R.drawable.metrouobplatinumcard, UOB, R.drawable.metrouobplatinumcard_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.UOBJCBPLATINUMCARD:
				iInfo = new ImageInfo(R.drawable.uobjcbplatinumcard_label, R.drawable.uobjcbplatinumcard, UOB, R.drawable.uobjcbplatinumcard_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.UOBCHINAUNIONPAYPLATINUMCARD:
				iInfo = new ImageInfo(R.drawable.uobchinaunionpayplatinumcard_label, R.drawable.uobchinaunionpayplatinumcard, UOB, R.drawable.uobchinaunionpayplatinumcard_nonticked);
				iInfo.setSelected(true);
				break;
			case Constants.UOBDIRECTVISACARD:
				iInfo = new ImageInfo(R.drawable.uobdirectvisacard_label, R.drawable.uobdirectvisacard, UOB, R.drawable.uobdirectvisacard_nonticked);
				iInfo.setSelected(true);
				break;
		}
		return iInfo;
	}
}
