package com.singtel.ilovedeals.screen;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.singtel.ilovedeals.info.Cuisine;
import com.singtel.ilovedeals.util.Constants;
import com.singtel.ilovedeals.util.Util;

public class CuisineListingPage extends SingtelDiningListActivity {
	
	public static CuisineListingPage instance;
	public static ArrayList<Cuisine> cuisines;
	private ListViewAdapter m_adapter;
	private Runnable queryThread;
	private ProgressDialog progressDialog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(R.style.Theme_Translucent);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.category);
		
		instance = this;
		initActivity(instance);
		
		init();
	}

	private void init() {
		cuisines = new ArrayList<Cuisine>();
		m_adapter = new ListViewAdapter(instance, R.layout.category_list, cuisines);
		setListAdapter(m_adapter);
		
		progressDialog = ProgressDialog.show(this, "", getString(R.string.retrieving), true);
		
		queryThread = new Runnable() {
			
			@Override
			public void run() {
				getData();
				runOnUiThread(addToMerchantList);
			}
		};
		
		Thread thread = new Thread(null, queryThread, "queryThread");
		thread.start();
	}

	protected void getData() {
		String result = "";
		
		result = Util.getHttpData(Constants.RESTAURANT_CUISINE_TYPES + SettingsPage.bankQuery);
		
		if(result == null || result.equalsIgnoreCase("408") || result.equalsIgnoreCase("404")) {
			Util.showAlert(instance, "ILoveDeals", "Please make sure Internet connection is available.", "OK", false);
		}
		else {
			result = Util.toJSONString(result);
			cuisines = new ArrayList<Cuisine>();
			
			try {
				JSONObject jsonObject1 = new JSONObject(result);
				JSONArray nameArray = jsonObject1.getJSONArray("data");
				
				
				for(int i = 0; i < nameArray.length(); i++) {
					JSONObject jsonObject2 = nameArray.getJSONObject(i);
					
					int id = Integer.parseInt(jsonObject2.getString("ID"));
					String name = jsonObject2.getString("CuisineType");
					
					Cuisine cuisineItem = new Cuisine(id, name);
					cuisines.add(cuisineItem);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private Runnable addToMerchantList = new Runnable() {

		@Override
		public void run() {
			if (cuisines != null && cuisines.size() > 0) {
				m_adapter.notifyDataSetChanged();
				for (int i = 0; i < cuisines.size(); i++) {
					m_adapter.add(cuisines.get(i));
				}
			}
			else {
				//TODO
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
	};
	
	protected void onListItemClick(android.widget.ListView l, View v, int position, long id) {
		SharedPreferences shared = getSharedPreferences(Constants.DEFAULT_SHARE_DATA, 0);
		SharedPreferences.Editor edit = shared.edit();
		
		SingtelDiningMainPage.URL = 
			Constants.RESTAURANT_CUISINE_LISTING + cuisines.get(position).getId() + "&resultsPerPage=20" + SettingsPage.bankQuery + "&pageNum=";
		SingtelDiningMainPage.searchText = cuisines.get(position).getName();
		
		edit.putString("cuisineLastPicked", cuisines.get(position).getName());
		edit.putString("cuisineLastURLQuery", Constants.RESTAURANT_CUISINE_LISTING + cuisines.get(position).getId() + "&resultsPerPage=20" + SettingsPage.bankQuery + "&pageNum=");
		edit.commit();
		
		instance.finish();
	};
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		instance.finish();
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	private class ListViewAdapter extends ArrayAdapter<Cuisine> {
		private ArrayList<Cuisine> cuisineItems;
		
		public ListViewAdapter(Context context, int resourceLayoutId, ArrayList<Cuisine> cuisineItems) {
			super(context, resourceLayoutId, cuisineItems);
			this.cuisineItems = cuisineItems;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			
			if(view == null) {
				LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = layoutInflater.inflate(R.layout.category_list, null);
			}
			
			final Cuisine cuisineItem = cuisineItems.get(position);
			if(cuisineItem != null) {
				TextView categoryName = (TextView)view.findViewById(R.id.categoryName);
				categoryName.setText(cuisineItem.getName());
			}
			
			return view;
		}
	}
}
