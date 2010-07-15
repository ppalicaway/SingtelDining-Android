package com.insidetip.singtel.util;

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
	public static final String RESTAURANT_RESTO_PAGE="http://singtel.dc2go.net/singtel/get_restaurant_list.php?resultsPerPage=20&bank=Citibank,DBS,OCBC,UOB&pageNum=";
	public static final String RESTAURANT_CUSINE_PAGE="http://singtel.dc2go.net/singtel/get_restaurant_by_cuisine_type.php?cuisineTypeID=3&resultsPerPage=20&bank=Citibank,DBS,OCBC,UOB&pageNum=";
	public static final String RESTAURANT_LOCATION_LISTING = "http://singtel.dc2go.net/singtel/get_location.php?a=b&bank=Citibank,DBS,OCBC,UOB";
	public static final String RESTAURANT_LOCATION_PLACES = "http://singtel.dc2go.net/singtel/get_restaurant_by_sub_location.php?id=";
	public static final String RESTAURANT_CUISINE_TYPES = "http://singtel.dc2go.net/singtel/get_cuisine.php?a=b&bank=Citibank,DBS,OCBC,UOB";
	public static final String RESTAURANT_CUISINE_LISTING = "http://singtel.dc2go.net/singtel/get_restaurant_by_cuisine_type.php?cuisineTypeID=";
	public static final String RESTAURANT_SEARCH = "http://singtel.dc2go.net/singtel/search.php?keyword=";
	
	public static final int ITEMS_PER_PAGE = 20;
	public static final String DB_NAME = "SINGTELDINING";
	public static final String TABLE_MERCHANT = "MERCHANTINFO";
}
