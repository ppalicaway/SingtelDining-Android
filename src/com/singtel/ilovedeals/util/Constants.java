package com.singtel.ilovedeals.util;


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
	
	//ILoveDeals
	public static final String KEY_MAP_RELEASE = "0o_1qZVZbZZiFrQEZtK56_fQa6dmBIXOBDxaAvQ";
	//ILoveDeals2
	//public static final String KEY_MAP_RELEASE = "0o_1qZVZbZZj6uRF_p6MuNHxmyCUks9ZpA5UZ-w";
	
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
	
	// Citibank Cards
	public static final int CITIPLATINUMVISAMASTERCARD = 1;
	public static final int CITIDIVIDENDCARD = 2;
	public static final int CITICLEARPLATINUMCARD = 3;
	public static final int CITISMRTPLATINUMVISACARD = 4;
	public static final int CITITANGSPLATINUMVISACARD = 5;
	public static final int CITIPARAGONPLATINUMMASTERCARD = 6;
	public static final int CITIPREMIERMILESCARD = 7;
	public static final int CITIBANKVISADEBITCARD = 8;
	public static final int CITIBUSINESSCARD = 9;
	public static final int CITIBANKBUSINESSGOLDCARD = 10;
	
	// DBS Cards
	public static final int DBSBLACKAMERICANEXPRESS = 11;
	public static final int DBSLIVEFRESHPLATINUM = 12;
	
	// OCBC Cards
	public static final int OCBCARTSPLATINUMMASTERCARD = 13;
	public static final int BESTOCBCPLATINUMMASTERCARD = 14;
	public static final int FAIRPRICEPLUSCREDITDEBITCARDS = 15;
	public static final int OCBCIKEAFRIENDSVISACARD = 16;
	public static final int OCBCNTUVISAGOLDCARD = 17;
	public static final int OCBCPLATINUMMASTERCARD = 18;
	public static final int OCBCROBINSONSVISAPLATINUMCARD = 19;
	public static final int OCBCSMUDEBITCARD = 20;
	public static final int OCBCSMUPLATINUMMASTERCARD = 21;
	public static final int OCBCTITANIUMMASTERCARD = 22;
	public static final int UPLUSCREDITDEBITCARDS = 23;
	public static final int OCBCYESCARD = 24;
	
	// POSB Card
	public static final int DBSPLATINUMMASTERCARD = 25;
	
	// UOB Cards
	public static final int SINGTELUOBVISAPLATINUMCARD = 26;
	public static final int UOBPRVIAMERICANEXPRESSCARD = 27;
	public static final int UOBVISASIGNATURECARD = 28;
	public static final int UOBONECARD = 29;
	public static final int UOBPREFERREDPLATINUMCARDVISA = 30;
	public static final int UOBPREFERREDPLATINUMCARDMASTERCARD = 31;
	public static final int UOBLADYSCARD = 32;
	public static final int UOBLADYSPLATINUMCARD = 33;
	public static final int UOBLADYSSOLITAIRECARD = 34;
	public static final int UOBVISAINFINITECARD = 35;
	public static final int UOBPREFERREDWORLDMASTERCARD = 36;
	public static final int UOBVISAGOLDCARD = 37;
	public static final int UOBVISACLASSICCARD = 38;
	public static final int UOBMASTERCARDGOLDCARD = 39;
	public static final int UOBMASTERCARDCLASSICCARD = 40;
	public static final int METROUOBPLATINUMCARD = 41;
	public static final int UOBJCBPLATINUMCARD = 42;
	public static final int UOBCHINAUNIONPAYPLATINUMCARD = 43;
	public static final int UOBDIRECTVISACARD = 44;
}
