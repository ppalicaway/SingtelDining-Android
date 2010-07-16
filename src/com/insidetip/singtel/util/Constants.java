package com.insidetip.singtel.util;

import com.insidetip.singtel.screen.SettingsPage;

public interface Constants {

	public static final int SPLASH_WAIT_MILLISEC = 2000;
	
	public static final String FACEBOOK_API_KEY = "8a710cdf7a8f707fe3c4043428c00619";
	public static final String FACEBOOK_API_SECRETKEY = "f687d73dbc545562fbf8d3ee893a28c4";
	
	public static final String RESTAURANT_LINK = "http://uob.dc2go.net/singtel/get_restaurant_list.php";
	public static final String RESTAURANT_DETAIL = "http://singtel.dc2go.net/singtel/get_detail.php?id=";
	
	public static final String ERROR_CODE_UNKNOW_HOST = "404";
	public static final String ERROR_CODE_TIME_OUT = "408";
	
	public static final String GOOGLE_MAP_API = "http://www.google.com/glm/mmap";
	
	public static final String FACEBOOK_NAME = "\"name\":\"";
	public static final String FACEBOOK_HREF = "\"href\":\"";
	public static final String FACEBOOK_CAPTION = "\"caption\":\"";
	public static final String FACEBOOK_DESCRIPTION = "\"description\":\"";
	public static final String FACEBOOK_MEDIA = "\"media\":[{\"type\":\"image\",\"src\":\"";
	public static final String FACEBOOK_PROPERTIES = "\"properties\":{\"Powered By\":{\"text\":\"SingTel\",\"href\":\"http://www.singtel.com\"}}}";
	
	public static final String KEY_MAP_DEBUG = "0o_1qZVZbZZh-5KqNtCjZdddrt5sJKMQZqMxZYQ";
	//public static final String KEY_MAP_DEBUG = "0o_1qZVZbZZhi5SfyLSW5kbDrnnNElk7slgjQnw";
	public static final String KEY_MAP_RELEASE = "0o_1qZVZbZZg30kaIdQsjQytD7UqxyyWDZPyIlg";
	
	public static final String DEFAULT_SHARE_DATA = "DEFAULT_SHARE_DATA";
	
	public static final String RESTAURANT_LOCATION_PAGE="http://singtel.dc2go.net/singtel/get_restaurant_by_location.php?latitude=";
	public static final String RESTAURANT_RESTO_PAGE="http://singtel.dc2go.net/singtel/get_restaurant_list.php?resultsPerPage=20";
	public static final String RESTAURANT_CUSINE_PAGE="http://singtel.dc2go.net/singtel/get_restaurant_by_cuisine_type.php?cuisineTypeID=2&resultsPerPage=20";
	public static final String RESTAURANT_LOCATION_LISTING = "http://singtel.dc2go.net/singtel/get_location.php?a=b";
	public static final String RESTAURANT_LOCATION_PLACES = "http://singtel.dc2go.net/singtel/get_restaurant_by_sub_location.php?id=";
	public static final String RESTAURANT_CUISINE_TYPES = "http://singtel.dc2go.net/singtel/get_cuisine.php?a=b";
	public static final String RESTAURANT_CUISINE_LISTING = "http://singtel.dc2go.net/singtel/get_restaurant_by_cuisine_type.php?cuisineTypeID=";
	public static final String RESTAURANT_SEARCH = "http://singtel.dc2go.net/singtel/search.php?keyword=";
	
	public static final int ITEMS_PER_PAGE = 20;
	public static final String DB_NAME = "SINGTELDINING";
	public static final String TABLE_MERCHANT = "MERCHANTINFO";
	
	public static final String SITE_TNC = "http://www.appzone.singtel.com/singtel/content_tAndC.do";
	
	public static final int CB_CLEAR_PLATINUM_VISA = 0;
	public static final int CB_CLEAR_PLATINUM_VISA_LABEL = 1;
	public static final int CB_DIVIDEND_PLATINUM = 2;
	public static final int CB_DIVIDEND_PLATINUM_LABEL = 3;
	public static final int CB_PARAGON_MASTERCARD = 4;
	public static final int CB_PARAGON_MASTERCARD_LABEL = 5;
	public static final int CB_PARAGON_VISA = 6;
	public static final int CB_PARAGON_VISA_LABEL = 7;
	public static final int CB_PREMIERE_MILES_VISA = 8;
	public static final int CB_PREMIERE_MILES_VISA_LABEL = 9;
	public static final int CB_SMRT_CARD = 10;
	public static final int CB_SMRT_CARD_LABEL = 11;
	
	public static final int DBS_BLACK_AMERICAN_EXPRESS_CARD = 12;
	public static final int DBS_BLACK_AMERICAN_EXPRESS_CARD_LABEL = 13;
	public static final int DBS_LIVE_FRESH_PLATINUM = 14;
	public static final int DBS_LIVE_FRESH_PLATINUM_LABEL = 15;
	public static final int DBS_PLATINUM_MASTERCARD = 16;
	public static final int DBS_PLATINUM_MASTERCARD_LABEL = 17;
	
	public static final int OCBC_ARTS_PLATINUM_CARD = 18;
	public static final int OCBC_ARTS_PLATINUM_CARD_LABEL = 19;
	public static final int OCBC_BEST_DENKI_PLATINUM_CARD = 20;
	public static final int OCBC_BEST_DENKI_PLATINUM_CARD_LABEL = 21;
	public static final int OCBC_CLASSIC_VISA_CARD = 22;
	public static final int OCBC_CLASSIC_VISA_CARD_LABEL = 23;
	public static final int OCBC_DEBIT_CARD = 24;
	public static final int OCBC_DEBIT_CARD_LABEL = 25;
	public static final int OCBC_FAIRPRICEPLUS_VISA_CARD = 26;
	public static final int OCBC_FAIRPRICEPLUS_VISA_CARD_LABEL = 27;
	public static final int OCBC_GOLD_MASTERCARD = 28;
	public static final int OCBC_GOLD_MASTERCARD_LABEL = 29;
	public static final int OCBC_IKEA_FRIENDS_VISA_CARD = 30;
	public static final int OCBC_IKEA_FRIENDS_VISA_CARD_LABEL = 31;
	public static final int OCBC_NTU_VISA_GOLD_CARD = 32;
	public static final int OCBC_NTU_VISA_GOLD_CARD_LABEL = 33;
	public static final int OCBC_NTU_VISA_CLASSIC_CARD = 34;
	public static final int OCBC_NTU_VISA_CLASSIC_CARD_LABEL = 35;
	public static final int OCBC_PLATINUM_MASTERCARD = 36;
	public static final int OCBC_PLATINUM_MASTERCARD_LABEL = 37;
	public static final int OCBC_ROBINSONS_PLATINUM_CARD = 38;
	public static final int OCBC_ROBINSONS_PLATINUM_CARD_LABEL = 39;
	public static final int OCBC_SMU_DEBIT_CARD = 40;
	public static final int OCBC_SMU_DEBIT_CARD_LABEL = 41;
	public static final int OCBC_SMU_PLATINUM_MASTERCARD = 42;
	public static final int OCBC_SMU_PLATINUM_MASTERCARD_LABEL = 43;
	public static final int OCBC_TITANIUM_MASTERCARD = 44;
	public static final int OCBC_TITANIUM_MASTERCARD_LABEL = 45;
	public static final int OCBC_U_PLUS_VISA_CARD = 46;
	public static final int OCBC_U_PLUS_VISA_CARD_LABEL = 47;
	public static final int OCBC_UPLUS_PLATINUM_CARD = 48;
	public static final int OCBC_UPLUS_PLATINUM_CARD_LABEL = 49;
	public static final int OCBC_YES_DEBIT_CARD = 50;
	public static final int OCBC_YES_DEBIT_CARD_LABEL = 51;
	
	public static final int UOB_DIRECT_VISA_CARD = 52;
	public static final int UOB_DIRECT_VISA_CARD_LABEL = 53;
	public static final int UOB_LADYS_CARD = 54;
	public static final int UOB_LADYS_CARD_LABEL = 55;
	public static final int UOB_MASTERCARD_CLASSIC_CARD = 56;
	public static final int UOB_MASTERCARD_CLASSIC_CARD_LABEL = 57;
	public static final int UOB_ONE_CARD = 58;
	public static final int UOB_ONE_CARD_LABEL = 59;
	public static final int UOB_PREFERRED_WORD = 60;
	public static final int UOB_PREFERRED_WORD_LABEL = 61;
	public static final int UOB_PRVI_VISA_AMERICAN = 62;
	public static final int UOB_PRVI_VISA_AMERICAN_LABEL = 63;
	public static final int UOB_VISA_CLASSIC_CARD = 64;
	public static final int UOB_VISA_CLASSIC_CARD_LABEL = 65;
	public static final int UOB_VISA_GOLD_CARD = 66;
	public static final int UOB_VISA_GOLD_CARD_LABEL = 67;
	public static final int UOB_VISA_INFINITE_CARD = 68;
	public static final int UOB_VISA_INFINITE_CARD_LABEL = 69;
	public static final int UOB_VISA_SIGNATURE_CARD = 70;
	public static final int UOB_VISA_SIGNATURE_CARD_LABEL = 71;
	
}
