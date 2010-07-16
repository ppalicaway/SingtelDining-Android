package com.insidetip.singtel.screen;

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

import com.insidetip.singtel.adapter.Controller;
import com.insidetip.singtel.db.DBManager;
import com.insidetip.singtel.info.MerchantInfo;
import com.insidetip.singtel.map.GPSLocationListener;
import com.insidetip.singtel.util.Constants;
import com.insidetip.singtel.util.Util;

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
	private TextView deleteTextView;
	public static String searchText = "";
	private final int LOCATION_REQUEST = 1;
	private final int CUISINE_REQUEST = 2;
	private final int RESTAURANT_REQUEST = 3;
	private final int BANK_REQUEST = 4;
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
		
		URL = Constants.RESTAURANT_LOCATION_PAGE + latitude +
	      "&longitude=" + longitude +
	      "&resultsPerPage=20" + SettingsPage.bankQuery + "&pageNum=";
		
		Button locationButton = (Button)findViewById(R.id.locationButton);
		locationButton.setOnClickListener(new MenuListener());
		
		Button restaurantButton = (Button)findViewById(R.id.restaurantButton);
		restaurantButton.setOnClickListener(new MenuListener());
		
		Button cuisineButton = (Button)findViewById(R.id.cuisineButton);
		cuisineButton.setOnClickListener(new MenuListener());
		
		Button favoriteButton = (Button)findViewById(R.id.favoriteButton);
		favoriteButton.setOnClickListener(new MenuListener());
		
		Button searchButton = (Button)findViewById(R.id.searchButton);
		searchButton.setOnClickListener(new MenuListener());
		searchButton.setVisibility(Button.GONE);
		
		Button mapButton = (Button)findViewById(R.id.mapButton);
		mapButton.setOnClickListener(new MenuListener());
		
		Button arButton = (Button)findViewById(R.id.arButton);
		arButton.setOnClickListener(new MenuListener());
		arButton.setVisibility(Button.GONE);
		
		searchEditText = (EditText)findViewById(R.id.searchEditText);
		searchEditText.setOnClickListener(new MenuListener());
		
		editButton = (Button)findViewById(R.id.editButton);
		editButton.setOnClickListener(new MenuListener());
		
		deleteTextView = (TextView)findViewById(R.id.deleteTextView);
		SingtelCardListener cListener = new SingtelCardListener();
		
		Button settingsButton = (Button)findViewById(R.id.settingsButton);
		settingsButton.setOnClickListener(new MenuListener());
				
		listView = (ListView)findViewById(android.R.id.list);
		LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = layoutInflater.inflate(R.layout.loadmore, null);
		listView.addFooterView(view);
		
		reloadData();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		MerchantInfo mInfo = merchantList.get(position);
		if(!isEdit) {
			DescriptionPage.merchantInfo = mInfo;
			DescriptionPage.catID = mInfo.getId();
			DescriptionPage.banks.clear();
			Intent details = new Intent(instance, DescriptionPage.class);
			startActivity(details);
		}
		else {
			new AlertDialog.Builder(instance)
            .setTitle("BestSGDeals")
            .setMessage("Are you sure you want to remove this item?")
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                	try {
                		DBManager dbMgr = new DBManager(DescriptionPage.instance, Constants.DB_NAME);
        				dbMgr.deleteMerchant(DescriptionPage.merchantInfo);
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
	
	private class MenuListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			totalItems = 1;
			totalPage = 1;
			page = 1;
			switch(v.getId()) {
				case R.id.settingsButton:
					Intent settings = new Intent(instance, SettingsPage.class);
					startActivityForResult(settings, BANK_REQUEST);
					break;
				case R.id.locationButton:
					isFavorite = false;
					isEdit = false;
					deleteTextView.setVisibility(Button.GONE);
					editButton.setVisibility(Button.GONE);
					searchEditText.setFocusableInTouchMode(false);
					searchEditText.setFocusable(false);
					isLocation = true;
					isRestaurants = false;
					isCuisines = false;
					searchEditText.setText("Around Me - All");
					SingtelDiningMainPage.URL = 
						Constants.RESTAURANT_LOCATION_PAGE + Util.latitude +
						"&longitude=" + Util.longitude +
						"&resultsPerPage=20" + SettingsPage.bankQuery + "&pageNum=";
					reloadData();
					break;
				case R.id.restaurantButton:
					isFavorite = false;
					isEdit = false;
					deleteTextView.setVisibility(Button.GONE);
					editButton.setVisibility(Button.GONE);
					searchEditText.setFocusableInTouchMode(false);
					searchEditText.setFocusable(false);
					isLocation = false;
					isRestaurants = true;
					isCuisines = false;
					searchEditText.setText("");
					SingtelDiningMainPage.URL = Constants.RESTAURANT_RESTO_PAGE + SettingsPage.bankQuery + "&pageNum=";
					reloadData();
					break;
				case R.id.cuisineButton:
					isEdit = false;
					isFavorite = false;
					deleteTextView.setVisibility(Button.GONE);
					editButton.setVisibility(Button.GONE);
					searchEditText.setFocusableInTouchMode(false);
					searchEditText.setFocusable(false);
					isLocation = false;
					isRestaurants = false;
					isCuisines = true;
					searchEditText.setText("Chinese");
					SingtelDiningMainPage.URL = Constants.RESTAURANT_CUSINE_PAGE + SettingsPage.bankQuery + "&pageNum=";
					reloadData();
					break;
				case R.id.favoriteButton:
					isFavorite = true;
					isEdit = false;
					deleteTextView.setVisibility(Button.GONE);
					editButton.setVisibility(Button.VISIBLE);
					isLocation = false;
					isRestaurants = false;
					isCuisines = false;
					searchEditText.setText("Favourites");
					searchEditText.setFocusableInTouchMode(false);
					searchEditText.setFocusable(false);
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
					deleteTextView.setVisibility(Button.GONE);
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
					deleteTextView.setVisibility(Button.VISIBLE);
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
		System.out.println(SettingsPage.bankQuery);
		if(requestCode == BANK_REQUEST) {
			URL = Constants.RESTAURANT_LOCATION_PAGE + latitude +
		      "&longitude=" + longitude +
		      "&resultsPerPage=20" + SettingsPage.bankQuery + "&pageNum=";
		}
		reloadData();
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	private void refreshBitmap() {
		SingtelCardListener cardListener = new SingtelCardListener();
		TableRow tableRow = (TableRow)findViewById(R.id.tableRow);
		tableRow.removeAllViews();
		LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if(!SettingsPage.images.isEmpty()) {
			for(int i = 0; i < SettingsPage.images.size(); i++) {
				CustomImageView view = (CustomImageView) inflater.inflate(R.layout.row_cell, null);
				view.setImageResource(SettingsPage.images.get(i).getId());
				view.setImageInfo(SettingsPage.images.get(i));
				view.setOnClickListener(cardListener);
				tableRow.addView(view);
			}
		}
		else {
			for(int i = 0; i < SettingsPage.getDefaultCards().size(); i++) {
				CustomImageView view = (CustomImageView) inflater.inflate(R.layout.row_cell, null);
				view.setImageResource(SettingsPage.getDefaultCards().get(i).getId());
				view.setImageInfo(SettingsPage.getDefaultCards().get(i));
				view.setOnClickListener(cardListener);
				tableRow.addView(view);
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
			
			System.out.println("Petz::" + SettingsPage.bankQuery);
			result = Util.getHttpData(SingtelDiningMainPage.URL + page);
			
			if(result == null || result.equalsIgnoreCase("408") || result.equalsIgnoreCase("404")) {
				Util.showAlert(SingtelDiningMainPage.instance, "BestSGDeals", "Please make sure Internet connection is available.", "OK", false);
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
						runOnUiThread(new AddToMerchantList());
					}
					catch(Exception e) {
						e.printStackTrace();
					}
					
					totalItems = jsonObject1.getInt("totalResults");
					totalPage = totalItems / Constants.ITEMS_PER_PAGE;
					if (totalItems % Constants.ITEMS_PER_PAGE != 0) {
						totalPage += 1;
					}
					
					settingLoadMore();
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
				//TODO:
			}
			try {
				if (progressDialog.isShowing()) {
					progressDialog.dismiss();
				}					
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	protected void onResume() {
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
		}
	}
}