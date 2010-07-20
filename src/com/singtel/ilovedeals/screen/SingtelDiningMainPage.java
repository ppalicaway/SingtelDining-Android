package com.singtel.ilovedeals.screen;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

import com.singtel.ilovedeals.adapter.Controller;
import com.singtel.ilovedeals.db.DBManager;
import com.singtel.ilovedeals.info.ImageInfo;
import com.singtel.ilovedeals.info.MerchantInfo;
import com.singtel.ilovedeals.map.GPSLocationListener;
import com.singtel.ilovedeals.util.Constants;
import com.singtel.ilovedeals.util.Util;

public class SingtelDiningMainPage extends SingtelDiningListActivity {
	
	public static SingtelDiningMainPage instance;
	public static ArrayList<MerchantInfo> merchantList;
	private ListViewAdapter m_adapter;
	private Runnable queryThread;
	public static ProgressDialog progressDialog = null;
	private LocationManager myLocationManager;
	private GPSLocationListener locationListener;
	private Location location;
	public static boolean isListing = true;
	public static String URL;
	private static double latitude;
	private static double longitude;
	private EditText searchEditText;
	private Button editButton;
	private Button doneButton;
	public static String searchText = "";
	private final int LOCATION_REQUEST = 1;
	private final int CUISINE_REQUEST = 2;
	private final int RESTAURANT_REQUEST = 3;
	private final int BANK_REQUEST = 4;
	private final int DESCRIPTION_REQUEST = 5;
	private boolean isLocation = true;
	private boolean isRestaurants = false;
	private boolean isCuisines = false;
	private boolean isEdit = false;
	private boolean isFavorite = false;
	public static int page = 1;
	public static int totalPage = 1;
	public static int totalItems = 1;
	private ListView listView;
	private View view;
	private Button mapButton;
	private Button favoriteButton;
	private Button cuisineButton;
	private Button restaurantButton;
	private Button locationButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(R.style.Theme_Translucent);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mainscreen);
		
		instance = this;
		initActivity(instance);
		
		settingLayout();
	}
	
	private void settingLayout() {
		
		myLocationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		locationListener = new GPSLocationListener();
		
		if(myLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			location = myLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		}
		else if(myLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			location = myLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		}
		
		try {
			latitude = location.getLatitude();
			longitude = location.getLongitude();
		}
		catch(Exception e) {
			double[] latLong = Util.queryLatLong(instance);
			latitude = latLong[0];
			longitude = latLong[1];
			//latitude = 1.4415068;
			//longitude = 103.7953423;
		}
		
		SharedPreferences shared = getSharedPreferences(Constants.DEFAULT_SHARE_DATA, 0);
		URL = shared.getString("locationLastURLQuery", "");
		
		if(URL.equalsIgnoreCase("")) {
			URL = Constants.RESTAURANT_LOCATION_PAGE + latitude +
		      "&longitude=" + longitude +
		      "&resultsPerPage=20" + SettingsPage.bankQuery + "&pageNum=";
		}
		
		locationButton = (Button)findViewById(R.id.locationButton);
		locationButton.setOnClickListener(new MenuListener());
		locationButton.setBackgroundResource(R.drawable.location_hover);
		
		restaurantButton = (Button)findViewById(R.id.restaurantButton);
		restaurantButton.setOnClickListener(new MenuListener());
		
		cuisineButton = (Button)findViewById(R.id.cuisineButton);
		cuisineButton.setOnClickListener(new MenuListener());
		
		favoriteButton = (Button)findViewById(R.id.favoriteButton);
		favoriteButton.setOnClickListener(new MenuListener());
		
		Button searchButton = (Button)findViewById(R.id.searchButton);
		searchButton.setOnClickListener(new MenuListener());
		searchButton.setVisibility(Button.GONE);
		
		mapButton = (Button)findViewById(R.id.mapButton);
		mapButton.setOnClickListener(new MenuListener());
		
		Button arButton = (Button)findViewById(R.id.arButton);
		arButton.setOnClickListener(new MenuListener());
		arButton.setVisibility(Button.GONE);
		
		searchEditText = (EditText)findViewById(R.id.searchEditText);
		searchEditText.setOnClickListener(new MenuListener());
		
		searchText = shared.getString("locationLastQueryPlace", "");
		
		if(searchText.equalsIgnoreCase("")) {
			searchText = "Around Me - All";
		}
		
		searchEditText.setText(searchText);
		
		editButton = (Button)findViewById(R.id.editButton);
		editButton.setOnClickListener(new MenuListener());
		
		doneButton = (Button)findViewById(R.id.doneButton);
		doneButton.setOnClickListener(new MenuListener());
		
		SingtelCardListener cListener = new SingtelCardListener();
		
		Button settingsCardButton = (Button)findViewById(R.id.settingsCard);
		settingsCardButton.setOnClickListener(new MenuListener());
				
		listView = (ListView)findViewById(android.R.id.list);
		LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = layoutInflater.inflate(R.layout.loadmore, null);
		listView.addFooterView(view);
		
		reloadData();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		final MerchantInfo mInfo = merchantList.get(position);
		if(!isEdit) {
			DescriptionPage.merchantInfo = mInfo;
			DescriptionPage.catID = mInfo.getId();
			DescriptionPage.banks.clear();
			Intent details = new Intent(instance, DescriptionPage.class);
			startActivityForResult(details, DESCRIPTION_REQUEST);
		}
		else {
			new AlertDialog.Builder(instance)
            .setTitle("ILoveDeals")
            .setMessage("Are you sure you want to remove this item?")
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                	try {
                		DBManager dbMgr = new DBManager(DescriptionPage.instance, Constants.DB_NAME);
        				dbMgr.deleteMerchant(mInfo);
        				dbMgr.close();
        			}
        			catch(Exception e) {
        				e.printStackTrace();
        			}
        			finally {
        				reloadDataFromDB();
        			}
                }
            })
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                	
                }
            })
            .create().show();
		}
	}
	
	private void reloadData() {
		refreshBitmap();
		
		merchantList = new ArrayList<MerchantInfo>();
		m_adapter = new ListViewAdapter(instance, R.layout.merchant_list, merchantList);
		setListAdapter(m_adapter);
		
		progressDialog = ProgressDialog.show(this, "", getString(R.string.retrieving), true);
		
		Thread thread = new Thread(null, new QueryThread(), "QueryData");
		thread.start();
	}
	
	private void reloadDataWithoutBitmap() {
		merchantList = new ArrayList<MerchantInfo>();
		m_adapter = new ListViewAdapter(instance, R.layout.merchant_list, merchantList);
		setListAdapter(m_adapter);
		
		progressDialog = ProgressDialog.show(this, "", getString(R.string.retrieving), true);
		
		Thread thread = new Thread(null, new QueryThread(), "QueryData");
		thread.start();
	}
	
	private class MenuListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			totalItems = 1;
			totalPage = 1;
			page = 1;
			LinearLayout cardLayoutView = (LinearLayout)findViewById(R.id.cardLayoutView);
			cardLayoutView.setVisibility(LinearLayout.VISIBLE);
			ImageView myFave = (ImageView)findViewById(R.id.myFaveImage);
			myFave.setVisibility(ImageView.GONE);
			SharedPreferences shared = getSharedPreferences(Constants.DEFAULT_SHARE_DATA, 0);
			
			switch(v.getId()) {
				case R.id.settingsCard:
					Intent settings = new Intent(instance, SettingsPage.class);
					startActivityForResult(settings, BANK_REQUEST);
					break;
				case R.id.locationButton:
					locationButton.setBackgroundResource(R.drawable.location_hover);
					restaurantButton.setBackgroundResource(R.drawable.restaurants);
					cuisineButton.setBackgroundResource(R.drawable.cuisines);
					favoriteButton.setBackgroundResource(R.drawable.favorites);
					isFavorite = false;
					isEdit = false;
					editButton.setVisibility(Button.GONE);
					doneButton.setVisibility(Button.GONE);
					searchEditText.setFocusableInTouchMode(false);
					searchEditText.setFocusable(false);
					isLocation = true;
					isRestaurants = false;
					isCuisines = false;
					
					searchText = shared.getString("locationLastQueryPlace", "");
					if(searchText.equalsIgnoreCase("")) {
						searchText = "Around Me - All";
					}
					
					searchEditText.setText(searchText);
					searchEditText.setVisibility(EditText.VISIBLE);
					
					mapButton.setVisibility(Button.VISIBLE);
					myFave.setVisibility(ImageView.GONE);
					
					URL = shared.getString("locationLastURLQuery", "");
					
					if(URL.equalsIgnoreCase("")) {
						SingtelDiningMainPage.URL = 
							Constants.RESTAURANT_LOCATION_PAGE + Util.latitude +
							"&longitude=" + Util.longitude +
							"&resultsPerPage=20" + SettingsPage.bankQuery + "&pageNum=";
					}
					
					reloadDataWithoutBitmap();
					break;
				case R.id.restaurantButton:
					locationButton.setBackgroundResource(R.drawable.location);
					restaurantButton.setBackgroundResource(R.drawable.restaurants_hover);
					cuisineButton.setBackgroundResource(R.drawable.cuisines);
					favoriteButton.setBackgroundResource(R.drawable.favorites);
					isFavorite = false;
					isEdit = false;
					editButton.setVisibility(Button.GONE);
					doneButton.setVisibility(Button.GONE);
					searchEditText.setFocusableInTouchMode(false);
					searchEditText.setFocusable(false);
					isLocation = false;
					isRestaurants = true;
					isCuisines = false;
					searchEditText.setText("Tap to search");
					searchEditText.setVisibility(EditText.VISIBLE);
					mapButton.setVisibility(Button.VISIBLE);
					myFave.setVisibility(ImageView.GONE);
					SingtelDiningMainPage.URL = Constants.RESTAURANT_RESTO_PAGE + SettingsPage.bankQuery + "&pageNum=";
					reloadDataWithoutBitmap();
					break;
				case R.id.cuisineButton:
					locationButton.setBackgroundResource(R.drawable.location);
					restaurantButton.setBackgroundResource(R.drawable.restaurants);
					cuisineButton.setBackgroundResource(R.drawable.cuisines_hover);
					favoriteButton.setBackgroundResource(R.drawable.favorites);
					isEdit = false;
					isFavorite = false;
					editButton.setVisibility(Button.GONE);
					doneButton.setVisibility(Button.GONE);
					
					searchText = shared.getString("cuisineLastPicked", "");
					
					if(searchText.equalsIgnoreCase("")) {
						searchText = "Asian";
					}
					
					searchEditText.setFocusableInTouchMode(false);
					searchEditText.setFocusable(false);
					searchEditText.setText(searchText);
					
					isLocation = false;
					isRestaurants = false;
					isCuisines = true;
					searchEditText.setVisibility(EditText.VISIBLE);
					mapButton.setVisibility(Button.VISIBLE);
					myFave.setVisibility(ImageView.GONE);
					
					URL = shared.getString("cuisineLastURLQuery", "");
					
					if(URL.equalsIgnoreCase("")) {
						SingtelDiningMainPage.URL = Constants.RESTAURANT_CUSINE_PAGE + SettingsPage.bankQuery + "&pageNum=";
					}
					
					reloadDataWithoutBitmap();
					break;
				case R.id.favoriteButton:
					locationButton.setBackgroundResource(R.drawable.location);
					restaurantButton.setBackgroundResource(R.drawable.restaurants);
					cuisineButton.setBackgroundResource(R.drawable.cuisines);
					favoriteButton.setBackgroundResource(R.drawable.favorites_hover);
					isFavorite = true;
					isEdit = false;
					editButton.setVisibility(Button.VISIBLE);
					doneButton.setVisibility(Button.GONE);
					isLocation = false;
					isRestaurants = false;
					isCuisines = false;
					cardLayoutView.setVisibility(LinearLayout.GONE);
					searchEditText.setVisibility(EditText.GONE);
					mapButton.setVisibility(Button.GONE);
					myFave.setVisibility(ImageView.VISIBLE);
					Button loadMore = (Button)findViewById(R.id.loadMore);
					loadMore.setVisibility(Button.GONE);
					reloadDataFromDB();
					break;
				case R.id.searchButton:
					break;
				case R.id.mapButton:
					Controller.displayMapScreen(instance);
					break;
				case R.id.searchEditText:
					isFavorite = false;
					isEdit = false;
					Intent category = null;
					if(isLocation) {
						category = new Intent(instance, CategoryListingPage.class);
						startActivityForResult(category, LOCATION_REQUEST);
					}
					else if(isCuisines) {
						category = new Intent(instance, CuisineListingPage.class);
						startActivityForResult(category, CUISINE_REQUEST);
					}
					else if(isRestaurants) {
						category = new Intent(instance, SearchPage.class);
						startActivityForResult(category, RESTAURANT_REQUEST);
					}
					break;
				case R.id.editButton:
					isEdit = true;
					editButton.setVisibility(Button.GONE);
					doneButton.setVisibility(Button.VISIBLE);
					myFave.setVisibility(ImageView.VISIBLE);
					cardLayoutView.setVisibility(LinearLayout.GONE);
					Util.showAlert(instance, "ILoveDeals", "Please tap on item to edit.", "OK", false);
					break;
				case R.id.doneButton:
					isEdit = false;
					editButton.setVisibility(Button.VISIBLE);
					doneButton.setVisibility(Button.GONE);
					myFave.setVisibility(ImageView.VISIBLE);
					cardLayoutView.setVisibility(LinearLayout.GONE);
					break;
			}
		}		
	}
	
	public void disableKeyboard(){
		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(searchEditText.getWindowToken(), 0);
	}
	
	public void reloadDataFromDB() {
		merchantList = new ArrayList<MerchantInfo>();
		m_adapter = new ListViewAdapter(instance, R.layout.merchant_list, merchantList);
		setListAdapter(m_adapter);
		
		progressDialog = ProgressDialog.show(this, "", getString(R.string.retrieving), true);
		
		Thread thread = new Thread(null, new QueryThreadFromDB(), "QueryDataFromDB");
		thread.start();
	}
	
	private class QueryThreadFromDB implements Runnable {

		@Override
		public void run() {
			merchantList = new ArrayList<MerchantInfo>();
			
			try {
				DBManager dbManager = new DBManager(instance, Constants.DB_NAME);
				merchantList = dbManager.getMerchantList();
				dbManager.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			runOnUiThread(new AddToMerchantList());
		}
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		searchEditText.setText(searchText);
		if(requestCode == BANK_REQUEST) {
			URL = Constants.RESTAURANT_LOCATION_PAGE + latitude +
		      "&longitude=" + longitude +
		      "&resultsPerPage=20" + SettingsPage.bankQuery + "&pageNum=";
			reloadData();
		}
		else if(requestCode == DESCRIPTION_REQUEST) {
			if(isFavorite) {
				reloadDataFromDB();
			}
		}
		else {
			reloadDataWithoutBitmap();
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	private void refreshBitmap() {
		SharedPreferences shared = getSharedPreferences(Constants.DEFAULT_SHARE_DATA, 0);
		String pref = shared.getString("cardPref", "");
		ArrayList<ImageInfo> imageArray = new ArrayList<ImageInfo>();
		if(!pref.equalsIgnoreCase("")) {
			pref = pref.substring(0, pref.length()-1);
			StringTokenizer stringTkn = new StringTokenizer(pref, ",");
			int size = stringTkn.countTokens();
			for (int x = 0; x < size; x++) {
				imageArray.add(Controller.getCorrespondingImage(stringTkn.nextToken()));
			}
			
			SingtelCardListener cardListener = new SingtelCardListener();
			TableRow tableRow = (TableRow)findViewById(R.id.tableRow);
			LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			tableRow.removeAllViews();
			if(!imageArray.isEmpty()) {
				for(int i = 0; i < SettingsPage.images.size(); i++) {
					CustomImageView view = (CustomImageView) inflater.inflate(R.layout.row_cell, null);
					view.setImageResource(SettingsPage.images.get(i).getId());
					view.setImageInfo(SettingsPage.images.get(i));
					view.setOnClickListener(cardListener);
					tableRow.addView(view);
				}
			}
		}
	}

	private class ListViewAdapter extends ArrayAdapter<MerchantInfo> {
		private ArrayList<MerchantInfo> merchants;
		
		public ListViewAdapter(Context context, int resourceLayoutId, ArrayList<MerchantInfo> merchants) {
			super(context, resourceLayoutId, merchants);
			this.merchants = merchants;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			
			if(view == null) {
				LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = layoutInflater.inflate(R.layout.merchant_list, null);
			}
			
			final MerchantInfo merchant = merchants.get(position);
			if(merchant != null) {
				
				TextView distance = (TextView)view.findViewById(R.id.distance);
				try {					
					DecimalFormat df = new DecimalFormat("#.##");
					String distanceText = df.format(Util.distanceTo(latitude, longitude, merchant.getLatitude(), merchant.getLongitude()));
					distance.setText(distanceText + " km");
					
					if(merchant.getLatitude() == 0.0 && merchant.getLongitude() == 0.0) {
						distance.setText("");
					}
				}
				catch(Exception e) {
					e.printStackTrace();
					distance.setText("");
				}
				
				TextView merchantName = (TextView)view.findViewById(R.id.merchantName);
				merchantName.setText(merchant.getRestaurantName());
				
				TextView merchantAddress = (TextView)view.findViewById(R.id.merchantAddress);
				merchantAddress.setText(merchant.getAddress());
				
				ImageView merchantPic = (ImageView)view.findViewById(R.id.merchantPic);
				Bitmap bitmap;
				
				//if(!merchant.getImage().equals(null) || !merchant.getImage().equalsIgnoreCase("")) {
				//	bitmap = Util.getBitmap(merchant.getImage());
				//	if(bitmap != null) {
				//		bitmap = Util.resizeImage(bitmap, 55, 55);
				//		merchantPic.setImageBitmap(bitmap);
				//	}
				//	else {
				//		merchantPic.setImageResource(R.drawable.default_icon);
				//	}
				//}
				//else {
					merchantPic.setImageResource(R.drawable.default_icon);
				//}
			}
			
			return view;
		}
	}
	
	private class QueryThread implements Runnable {

		@Override
		public void run() {
			String result = "";
			
			result = Util.getHttpData(SingtelDiningMainPage.URL + page);
			
			if(result == null || result.equalsIgnoreCase("408") || result.equalsIgnoreCase("404")) {
				Util.showAlert(SingtelDiningMainPage.instance, "ILoveDeals", "Please make sure Internet connection is available.", "OK", false);
				try {
					if (progressDialog.isShowing()) {
						progressDialog.dismiss();
					}					
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			else {
				result = Util.toJSONString(result);
				SingtelDiningMainPage.merchantList = new ArrayList<MerchantInfo>();
				
				try {
					JSONObject jsonObject1 = new JSONObject(result);
					JSONArray nameArray = jsonObject1.getJSONArray("data");
					
					try {
						for(int i = 0; i < nameArray.length(); i++) {
							JSONObject jsonObject2 = nameArray.getJSONObject(i);
							
							int id = 0;
							String image = "";
							String restaurantName = "";
							String address = "";
							float rating = 0;
							int reviews = 0;
							double latitude = 0;
							double longitude = 0;
							
							id = Integer.parseInt(jsonObject2.getString("ID"));
							image = jsonObject2.getString("Image");
							restaurantName = jsonObject2.getString("RestaurantName");
							address = jsonObject2.getString("Address");
							rating = Float.parseFloat(jsonObject2.getString("Rating"));
							reviews = Integer.parseInt(jsonObject2.getString("Reviews"));
							latitude = Double.parseDouble(jsonObject2.getString("Latitude"));
							longitude = Double.parseDouble(jsonObject2.getString("Longitude"));
							
							MerchantInfo mInfo = new MerchantInfo(id, image, restaurantName, address, rating, reviews, latitude, longitude);
							SingtelDiningMainPage.merchantList.add(mInfo);
						}
						
						totalItems = jsonObject1.getInt("totalResults");
						totalPage = totalItems / Constants.ITEMS_PER_PAGE;
						if (totalItems % Constants.ITEMS_PER_PAGE != 0) {
							totalPage += 1;
						}						
						runOnUiThread(new AddToMerchantList());
					}
					catch(Exception e) {
						try {
							if (progressDialog.isShowing()) {
								progressDialog.dismiss();
							}					
						}
						catch (Exception ex) {
							ex.printStackTrace();
						}
						Util.showAlert(SingtelDiningMainPage.instance, "ILoveDeals", "No deals found.", "OK", false);
						e.printStackTrace();
					}
					
					///totalItems = jsonObject1.getInt("totalResults");
					//totalPage = totalItems / Constants.ITEMS_PER_PAGE;
					//if (totalItems % Constants.ITEMS_PER_PAGE != 0) {
					//	totalPage += 1;
					//}
					
					//settingLoadMore();
				} 
				catch (Exception e) {
					try {
						if (progressDialog.isShowing()) {
							progressDialog.dismiss();
						}					
					}
					catch (Exception ex) {
						ex.printStackTrace();
					}
					Util.showAlert(SingtelDiningMainPage.instance, "ILoveDeals", "No deals found.", "OK", false);
					e.printStackTrace();
				}
			}
		}
	}
	
	private class AddToMerchantList implements Runnable {

		@Override
		public void run() {
			if (merchantList != null && merchantList.size() > 0) {
				m_adapter.notifyDataSetChanged();
				for (int i = 0; i < merchantList.size(); i++) {
					m_adapter.add(merchantList.get(i));
				}
			}
			else {
				
			}
			try {
				if (progressDialog.isShowing()) {
					progressDialog.dismiss();
				}					
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			settingLoadMore();
		}
		
	}
	
	@Override
	protected void onResume() {
		isListing = true;
		if(myLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			myLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10, 200, locationListener);
			location = myLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		}
		else if(myLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			myLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 200, locationListener);
			location = myLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		}
		super.onResume();
	}
	
	public void settingLoadMore() {
		TableRow tableRow = (TableRow) findViewById(R.id.tableRow);
		
		Button loadMore = (Button)view.findViewById(R.id.loadMore);
		
		if(totalItems < Constants.ITEMS_PER_PAGE || page == totalPage || isFavorite) {
			loadMore.setVisibility(Button.GONE);
		}
		else {
			loadMore.setVisibility(Button.VISIBLE);
		}
				
		loadMore.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				page++;
				reloadData();
			}
		});
	}

	@Override
	protected void onPause() {
		myLocationManager.removeUpdates(locationListener);
		super.onPause();
	}
	
	@Override
	protected void onDestroy() {
		formNewExitQuery();
		super.onDestroy();
	}
	
	private class SingtelCardListener implements OnClickListener {
		
		@Override
		public void onClick(View v) {
			CustomImageView civ = (CustomImageView)v;
			
			if(!civ.getIsPressed()) {
				civ.setImageResource(civ.getImageInfo().getIdLabel());
				civ.setIsPressed(true);
			}
			else {
				civ.setImageResource(civ.getImageInfo().getId());
				civ.setIsPressed(false);
			}
			
			formNewQuery();
			reloadDataWithoutBitmap();
		}
	}

	public void formNewQuery() {
		
		ArrayList<String> newQuery = new ArrayList<String>();
		
		TableRow tableRow = (TableRow)findViewById(R.id.tableRow);
		int childrenCount = tableRow.getChildCount();
		
		for(int i = 0; i < childrenCount; i++) {
			CustomImageView civ = (CustomImageView) tableRow.getChildAt(i);
			if(!civ.getIsPressed()) {
				String name = civ.getImageInfo().getBankName();
				if(!newQuery.contains(name)) {
					newQuery.add(name);
				}
			}
		}
		
		String query = "&bank=";
		for(int j = 0; j < newQuery.size(); j++) {
			query += newQuery.get(j) + ",";
		}
		
		if(query.charAt(query.length()-1) == ',') {
			query = query.substring(0, query.length()-1);
		}
		
		String locQuery = SettingsPage.bankQuery;
		String locURL = URL;
		locURL = locURL.replaceAll(locQuery, query);
		
		URL = locURL;
		SettingsPage.bankQuery = query;
	}
	
	public void formNewExitQuery() {
		
		ArrayList<String> newQuery = new ArrayList<String>();
		
		TableRow tableRow = (TableRow)findViewById(R.id.tableRow);
		int childrenCount = tableRow.getChildCount();
		
		for(int i = 0; i < childrenCount; i++) {
			CustomImageView civ = (CustomImageView) tableRow.getChildAt(i);
			String name = civ.getImageInfo().getBankName();
			if(!newQuery.contains(name)) {
				newQuery.add(name);
			}
		}
		
		String query = "&bank=";
		for(int j = 0; j < newQuery.size(); j++) {
			query += newQuery.get(j) + ",";
		}
		
		if(query.charAt(query.length()-1) == ',') {
			query = query.substring(0, query.length()-1);
		}
		
		SettingsPage.bankQuery = query;
	}
}