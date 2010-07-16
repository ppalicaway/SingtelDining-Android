package com.insidetip.singtel.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;

import com.insidetip.singtel.info.ImageInfo;
import com.insidetip.singtel.info.MerchantInfo;
import com.insidetip.singtel.map.MapLocationViewer;
import com.insidetip.singtel.screen.DescriptionPage;
import com.insidetip.singtel.screen.MapPage;
import com.insidetip.singtel.screen.R;
import com.insidetip.singtel.screen.SingtelDiningMainPage;
import com.insidetip.singtel.util.Constants;

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
	
	public static int getImage(int currentCardId) {
		int imageDrawable = 0;
		
		switch(currentCardId) {
		case Constants.CB_CLEAR_PLATINUM_VISA:
			imageDrawable = R.drawable.citibank_clear_platinum_visa_label;
			break;
		case Constants.CB_CLEAR_PLATINUM_VISA_LABEL:
			imageDrawable = R.drawable.citibank_clear_platinum_visa;
			break;
		case Constants.CB_DIVIDEND_PLATINUM:
			imageDrawable = R.drawable.citibank_dividend_platinum_mastercard_label;
			break;
		case Constants.CB_DIVIDEND_PLATINUM_LABEL:
			imageDrawable = R.drawable.citibank_dividend_platinum_mastercard;
			break;
		case Constants.CB_PARAGON_MASTERCARD:
			imageDrawable = R.drawable.citibank_paragon_mastercard_label;
			break;
		case Constants.CB_PARAGON_MASTERCARD_LABEL:
			imageDrawable = R.drawable.citibank_paragon_mastercard;
			break;
		case Constants.CB_PARAGON_VISA:
			imageDrawable = R.drawable.citibank_paragon_visa_label;
			break;
		case Constants.CB_PARAGON_VISA_LABEL:
			imageDrawable = R.drawable.citibank_paragon_visa;
			break;
		case Constants.CB_PREMIERE_MILES_VISA:
			imageDrawable = R.drawable.citibank_premiermiles_visa_signature_label;
			break;
		case Constants.CB_PREMIERE_MILES_VISA_LABEL:
			imageDrawable = R.drawable.citibank_premiermiles_visa_signature;
			break;
		case Constants.CB_SMRT_CARD:
			imageDrawable = R.drawable.citibank_smrt_card_label;
			break;
		case Constants.CB_SMRT_CARD_LABEL:
			imageDrawable = R.drawable.citibank_smrt_card;
			break;
		case Constants.DBS_BLACK_AMERICAN_EXPRESS_CARD:
			imageDrawable = R.drawable.dbs_black_american_express_card_label;
			break;
		case Constants.DBS_BLACK_AMERICAN_EXPRESS_CARD_LABEL:
			imageDrawable = R.drawable.dbs_black_american_express_card;
			break;
		case Constants.DBS_LIVE_FRESH_PLATINUM:
			imageDrawable = R.drawable.dbs_live_fresh_platinum_card_label;
			break;
		case Constants.DBS_LIVE_FRESH_PLATINUM_LABEL:
			imageDrawable = R.drawable.dbs_live_fresh_platinum_card;
			break;
		case Constants.DBS_PLATINUM_MASTERCARD:
			imageDrawable = R.drawable.dbs_platinum_mastercard_label;
			break;
		case Constants.DBS_PLATINUM_MASTERCARD_LABEL:
			imageDrawable = R.drawable.dbs_platinum_mastercard;
			break;
		case Constants.OCBC_ARTS_PLATINUM_CARD:
			imageDrawable = R.drawable.ocbc_arts_platinum_card_label;
			break;
		case Constants.OCBC_ARTS_PLATINUM_CARD_LABEL:
			imageDrawable = R.drawable.ocbc_arts_platinum_card;
			break;
		case Constants.OCBC_BEST_DENKI_PLATINUM_CARD:
			imageDrawable = R.drawable.ocbc_best_denki_platinum_card_label;
			break;
		case Constants.OCBC_BEST_DENKI_PLATINUM_CARD_LABEL:
			imageDrawable = R.drawable.ocbc_best_denki_platinum_card;
			break;
		case Constants.OCBC_CLASSIC_VISA_CARD:
			imageDrawable = R.drawable.ocbc_classic_visa_card_label;
			break;
		case Constants.OCBC_CLASSIC_VISA_CARD_LABEL:
			imageDrawable = R.drawable.ocbc_classic_visa_card;
			break;
		case Constants.OCBC_DEBIT_CARD:
			imageDrawable = R.drawable.ocbc_debit_card_label;
			break;
		case Constants.OCBC_DEBIT_CARD_LABEL:
			imageDrawable = R.drawable.ocbc_debit_card;
			break;
		case Constants.OCBC_FAIRPRICEPLUS_VISA_CARD:
			imageDrawable = R.drawable.ocbc_fairpriceplus_visa_card_label;
			break;
		case Constants.OCBC_FAIRPRICEPLUS_VISA_CARD_LABEL:
			imageDrawable = R.drawable.ocbc_fairpriceplus_visa_card;
			break;
		case Constants.OCBC_GOLD_MASTERCARD:
			imageDrawable = R.drawable.ocbc_gold_mastercard_label;
			break;
		case Constants.OCBC_GOLD_MASTERCARD_LABEL:
			imageDrawable = R.drawable.ocbc_gold_mastercard;
			break;
		case Constants.OCBC_IKEA_FRIENDS_VISA_CARD:
			imageDrawable = R.drawable.ocbc_ikea_friends_visacard_label;
			break;
		case Constants.OCBC_IKEA_FRIENDS_VISA_CARD_LABEL:
			imageDrawable = R.drawable.ocbc_ikea_friends_visa_card;
			break;
		case Constants.OCBC_NTU_VISA_GOLD_CARD:
			imageDrawable = R.drawable.ocbc_ntu_visa_gold_card_label;
			break;
		case Constants.OCBC_NTU_VISA_GOLD_CARD_LABEL:
			imageDrawable = R.drawable.ocbc_ntu_visa_gold_card;
			break;
		case Constants.OCBC_NTU_VISA_CLASSIC_CARD:
			imageDrawable = R.drawable.ocbc_ntu_visa_classic_card_label;
			break;
		case Constants.OCBC_NTU_VISA_CLASSIC_CARD_LABEL:
			imageDrawable = R.drawable.ocbc_ntu_visa_classic_card;
			break;
		case Constants.OCBC_PLATINUM_MASTERCARD:
			imageDrawable = R.drawable.ocbc_platinum_mastercard_label;
			break;
		case Constants.OCBC_PLATINUM_MASTERCARD_LABEL:
			imageDrawable = R.drawable.ocbc_platinum_mastercard;
			break;
		case Constants.OCBC_ROBINSONS_PLATINUM_CARD:
			imageDrawable = R.drawable.ocbc_robinsons_platinum_card_label;
			break;
		case Constants.OCBC_ROBINSONS_PLATINUM_CARD_LABEL:
			imageDrawable = R.drawable.ocbc_robinsons_platinum_card;
			break;
		case Constants.OCBC_SMU_DEBIT_CARD:
			imageDrawable = R.drawable.ocbc_smu_debit_cards_label;
			break;
		case Constants.OCBC_SMU_DEBIT_CARD_LABEL:
			imageDrawable = R.drawable.ocbc_smu_debit_cards;
			break;
		case Constants.OCBC_SMU_PLATINUM_MASTERCARD:
			imageDrawable = R.drawable.ocbc_smu_platinum_mastercard_label;
			break;
		case Constants.OCBC_SMU_PLATINUM_MASTERCARD_LABEL:
			imageDrawable = R.drawable.ocbc_smu_platinum_mastercard;
			break;
		case Constants.OCBC_TITANIUM_MASTERCARD:
			imageDrawable = R.drawable.ocbc_titanium_mastercard_label;
			break;
		case Constants.OCBC_TITANIUM_MASTERCARD_LABEL:
			imageDrawable = R.drawable.ocbc_titanium_mastercard;
			break;
		case Constants.OCBC_U_PLUS_VISA_CARD:
			imageDrawable = R.drawable.ocbc_uplus_visa_card_label;
			break;
		case Constants.OCBC_U_PLUS_VISA_CARD_LABEL:
			imageDrawable = R.drawable.ocbc_uplus_visa_card;
			break;
		case Constants.OCBC_UPLUS_PLATINUM_CARD:
			imageDrawable = R.drawable.ocbc_uplus_platinum_card_label;
			break;
		case Constants.OCBC_UPLUS_PLATINUM_CARD_LABEL:
			imageDrawable = R.drawable.ocbc_uplus_platinum_card;
			break;
		case Constants.OCBC_YES_DEBIT_CARD:
			imageDrawable = R.drawable.ocbc_yes_debit_card_label;
			break;
		case Constants.OCBC_YES_DEBIT_CARD_LABEL:
			imageDrawable = R.drawable.ocbc_yes_debit_card;
			break;
		case Constants.UOB_DIRECT_VISA_CARD:
			imageDrawable = R.drawable.uob_direct_visa_card_label;
			break;
		case Constants.UOB_DIRECT_VISA_CARD_LABEL:
			imageDrawable = R.drawable.uob_direct_visa_card;
			break;
		case Constants.UOB_LADYS_CARD:
			imageDrawable = R.drawable.uob_ladys_card_label;
			break;
		case Constants.UOB_LADYS_CARD_LABEL:
			imageDrawable = R.drawable.uob_ladys_card;
			break;
		case Constants.UOB_MASTERCARD_CLASSIC_CARD:
			imageDrawable = R.drawable.uob_mastercard_classic_card_label;
			break;
		case Constants.UOB_MASTERCARD_CLASSIC_CARD_LABEL:
			imageDrawable = R.drawable.uob_mastercard_classic_card;
			break;
		case Constants.UOB_ONE_CARD:
			imageDrawable = R.drawable.uob_one_card_label;
			break;
		case Constants.UOB_ONE_CARD_LABEL:
			imageDrawable = R.drawable.uob_one_card;
			break;
		case Constants.UOB_PREFERRED_WORD:
			imageDrawable = R.drawable.uob_preferred_world_mastercard_label;
			break;
		case Constants.UOB_PREFERRED_WORD_LABEL:
			imageDrawable = R.drawable.uob_preferred_world_mastercard;
			break;
		case Constants.UOB_PRVI_VISA_AMERICAN:
			imageDrawable = R.drawable.uob_prvi_visa_american_express_card_label;
			break;
		case Constants.UOB_PRVI_VISA_AMERICAN_LABEL:
			imageDrawable = R.drawable.uob_prvi_visa_american_express_card;
			break;
		case Constants.UOB_VISA_CLASSIC_CARD:
			imageDrawable = R.drawable.uob_visa_classic_card_label;
			break;
		case Constants.UOB_VISA_CLASSIC_CARD_LABEL:
			imageDrawable = R.drawable.uob_visa_classic_card;
			break;
		case Constants.UOB_VISA_GOLD_CARD:
			imageDrawable = R.drawable.uob_visa_gold_card_label;
			break;
		case Constants.UOB_VISA_GOLD_CARD_LABEL:
			imageDrawable = R.drawable.uob_visa_gold_card;
			break;
		case Constants.UOB_VISA_INFINITE_CARD:
			imageDrawable = R.drawable.uob_visa_infinite_card_label;
			break;
		case Constants.UOB_VISA_INFINITE_CARD_LABEL:
			imageDrawable = R.drawable.uob_visa_infinite_card;
			break;
		case Constants.UOB_VISA_SIGNATURE_CARD:
			imageDrawable = R.drawable.uob_visa_signature_card_label;
			break;
		case Constants.UOB_VISA_SIGNATURE_CARD_LABEL:
			imageDrawable = R.drawable.uob_visa_signature_card;
			break;
		}
		return imageDrawable;
	}
	
	public static ImageInfo getCorrespondingImage(String value) {
		ImageInfo iInfo = null;
		int id = Integer.parseInt(value);
		switch(id) {
			case Constants.CBCLEARPLATINUMVISA:
				iInfo = new ImageInfo(R.drawable.citibank_clear_platinum_visa_label, R.drawable.citibank_clear_platinum_visa, CITIBANK, true);
				break;
			case Constants.CBDIVIDENDPLATINUM:
				iInfo = new ImageInfo(R.drawable.citibank_dividend_platinum_mastercard_label, R.drawable.citibank_dividend_platinum_mastercard, CITIBANK, true);
				break;
			case Constants.CBPARAGONMASTERCARD:
				iInfo = new ImageInfo(R.drawable.citibank_paragon_mastercard_label, R.drawable.citibank_paragon_mastercard, CITIBANK, true);
				break;
			case Constants.CBPARAGONVISA:
				iInfo = new ImageInfo(R.drawable.citibank_paragon_visa_label, R.drawable.citibank_paragon_visa, CITIBANK, true);
				break;
			case Constants.CBPREMIEREMILESVISA:
				iInfo = new ImageInfo(R.drawable.citibank_premiermiles_visa_signature_label, R.drawable.citibank_premiermiles_visa_signature, CITIBANK, true);
				break;
			case Constants.CBSMRTCARD:
				iInfo = new ImageInfo(R.drawable.citibank_smrt_card_label, R.drawable.citibank_smrt_card, CITIBANK, true);
				break;
			case Constants.DBSBLACKAMERICANEXPRESS:
				iInfo = new ImageInfo(R.drawable.dbs_black_american_express_card_label, R.drawable.dbs_black_american_express_card, DBS, true);
				break;
			case Constants.DBSLIVEFRESHPLATINUM:
				iInfo = new ImageInfo(R.drawable.dbs_live_fresh_platinum_card_label, R.drawable.dbs_live_fresh_platinum_card, DBS, true);
				break;
			case Constants.DBSPLATINUMMASTERCARD:
				iInfo = new ImageInfo(R.drawable.dbs_platinum_mastercard_label, R.drawable.dbs_platinum_mastercard, DBS, true);
				break;
			case Constants.OCBCARTSPLATINUMCARD:
				iInfo = new ImageInfo(R.drawable.ocbc_arts_platinum_card_label, R.drawable.ocbc_arts_platinum_card, OCBC, true);
				break;
			case Constants.OCBCBESTDENKIPLATINUMCARD:
				iInfo = new ImageInfo(R.drawable.ocbc_best_denki_platinum_card_label, R.drawable.ocbc_best_denki_platinum_card, OCBC, true);
				break;
			case Constants.OCBCCLASSICVISACARD:
				iInfo = new ImageInfo(R.drawable.ocbc_classic_visa_card_label, R.drawable.ocbc_classic_visa_card, OCBC, true);
				break;
			case Constants.OCBCDEBITCARD:
				iInfo = new ImageInfo(R.drawable.ocbc_debit_card_label, R.drawable.ocbc_debit_card, OCBC, true);
				break;
			case Constants.OCBCFAIRPRICEPLUSVISACARD:
				iInfo = new ImageInfo(R.drawable.ocbc_fairpriceplus_visa_card_label, R.drawable.ocbc_fairpriceplus_visa_card, OCBC, true);
				break;
			case Constants.OCBCGOLDMASTERCARD:
				iInfo = new ImageInfo(R.drawable.ocbc_gold_mastercard_label, R.drawable.ocbc_gold_mastercard, OCBC, true);
				break;
			case Constants.OCBCIKEAFRIENDSVISACARD:
				iInfo = new ImageInfo(R.drawable.ocbc_ikea_friends_visacard_label, R.drawable.ocbc_ikea_friends_visa_card, OCBC, true);
				break;
			case Constants.OCBCNTUVISACLASSICCARD:
				iInfo = new ImageInfo(R.drawable.ocbc_ntu_visa_classic_card_label, R.drawable.ocbc_ntu_visa_classic_card, OCBC, true);
				break;
			case Constants.OCBCNTUVISAGOLDCARD:
				iInfo = new ImageInfo(R.drawable.ocbc_ntu_visa_gold_card_label, R.drawable.ocbc_ntu_visa_gold_card, OCBC, true);
				break;
			case Constants.OCBCPLATINUMMASTERCARD:
				iInfo = new ImageInfo(R.drawable.ocbc_platinum_mastercard_label, R.drawable.ocbc_platinum_mastercard, OCBC, true);
				break;
			case Constants.OCBCROBINSONSPLATINUMCARD:
				iInfo = new ImageInfo(R.drawable.ocbc_robinsons_platinum_card_label, R.drawable.ocbc_robinsons_platinum_card, OCBC, true);
				break;
			case Constants.OCBCSMUDEBITCARD:
				iInfo = new ImageInfo(R.drawable.ocbc_smu_debit_cards_label, R.drawable.ocbc_smu_debit_cards, OCBC, true);
				break;
			case Constants.OCBCSMUPLATINUMMASTERCARD:
				iInfo = new ImageInfo(R.drawable.ocbc_smu_platinum_mastercard_label, R.drawable.ocbc_smu_platinum_mastercard, OCBC, true);
				break;
			case Constants.OCBCTITANIUMMASTERCARD:
				iInfo = new ImageInfo(R.drawable.ocbc_titanium_mastercard_label, R.drawable.ocbc_titanium_mastercard, OCBC, true);
				break;
			case Constants.OCBCUPLUSVISACARD:
				iInfo = new ImageInfo(R.drawable.ocbc_uplus_visa_card_label, R.drawable.ocbc_uplus_visa_card, OCBC, true);
				break;
			case Constants.OCBCUPLUSPLATINUMCARD:
				iInfo = new ImageInfo(R.drawable.ocbc_uplus_platinum_card_label, R.drawable.ocbc_uplus_platinum_card, OCBC, true);
				break;
			case Constants.OCBCYESDEBITCARD:
				iInfo = new ImageInfo(R.drawable.ocbc_yes_debit_card_label, R.drawable.ocbc_yes_debit_card, OCBC, true);
				break;
			case Constants.UOBDIRECTVISACARD:
				iInfo = new ImageInfo(R.drawable.uob_direct_visa_card_label, R.drawable.uob_direct_visa_card, UOB, true);
				break;
			case Constants.UOBLADYSCARD:
				iInfo = new ImageInfo(R.drawable.uob_ladys_card_label, R.drawable.uob_ladys_card, UOB, true);
				break;
			case Constants.UOBMASTERCARDCLASSICCARD:
				iInfo = new ImageInfo(R.drawable.uob_mastercard_classic_card_label, R.drawable.uob_mastercard_classic_card, UOB, true);
				break;
			case Constants.UOBMASTERCARDGOLDCARD:
				iInfo = new ImageInfo(R.drawable.uob_mastercard_gold_card_label, R.drawable.uob_mastercard_gold_card, UOB, true);
				break;
			case Constants.UOBONECARD:
				iInfo = new ImageInfo(R.drawable.uob_one_card_label, R.drawable.uob_one_card, UOB, true);
				break;
			case Constants.UOBPREFERREDWORLDCARD:
				iInfo = new ImageInfo(R.drawable.uob_preferred_world_mastercard_label, R.drawable.uob_preferred_world_mastercard, UOB, true);
				break;
			case Constants.UOBPRVIVISAAMERICANCARD:
				iInfo = new ImageInfo(R.drawable.uob_prvi_visa_american_express_card_label, R.drawable.uob_prvi_visa_american_express_card, UOB, true);
				break;
			case Constants.UOBVISACLASSICCARD:
				iInfo = new ImageInfo(R.drawable.uob_visa_classic_card_label, R.drawable.uob_visa_classic_card, UOB, true);
				break;
			case Constants.UOBVISAGOLDCARD:
				iInfo = new ImageInfo(R.drawable.uob_visa_gold_card_label, R.drawable.uob_visa_gold_card, UOB, true);
				break;
			case Constants.UOBVISAINFINITECARD:
				iInfo = new ImageInfo(R.drawable.uob_visa_infinite_card_label, R.drawable.uob_visa_infinite_card, UOB, true);
				break;
			case Constants.UOBVISASIGNATURECARD:
				iInfo = new ImageInfo(R.drawable.uob_visa_signature_card_label, R.drawable.uob_visa_signature_card, UOB, true);
				break;
		}
		return iInfo;
	}
}
