package com.insidetip.singtel.screen;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.insidetip.singtel.info.Location;
import com.insidetip.singtel.info.SubLocation;
import com.insidetip.singtel.util.Constants;
import com.insidetip.singtel.util.Util;

public class CategoryListingPage extends SingtelDiningListActivity {

	public static CategoryListingPage instance;
	private ProgressDialog progressDialog = null;
	public static ArrayList<Location> location;
	public static ArrayList<SubLocation> subLocation;
	private ListViewAdapter m_adapter;
	private Runnable queryThread;
	
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
		location = new ArrayList<Location>();
		m_adapter = new ListViewAdapter(instance, R.layout.category_list, location);
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
		
		result = Util.getHttpData(Constants.RESTAURANT_LOCATION_LISTING);
		
		if(result == null || result.equalsIgnoreCase("408") || result.equalsIgnoreCase("404")) {
			Util.showAlert(instance, "", "Internet Connection", "OK", false);
		}
		else {
			result = Util.toJSONString(result);
			location = new ArrayList<Location>();
			
			Location temp = new Location(-100, "Around Me");
			SubLocation tempSub = new SubLocation(-101, "All");
			location.add(temp);
			location.get(0).getSubLocation().add(tempSub);
			
			try {
				JSONObject jsonObject1 = new JSONObject(result);
				JSONArray nameArray = jsonObject1.getJSONArray("data");
				
				for(int i = 0; i < nameArray.length(); i++) {
					JSONObject jsonObject2 = nameArray.getJSONObject(i);
					
					int id = Integer.parseInt(jsonObject2.getString("id"));
					String name = jsonObject2.getString("name");
					
					Location locTemp = new Location(id, name);
					location.add(locTemp);
					
					JSONArray subNameArray = jsonObject2.getJSONArray("sublocation");
					
					for(int j = 0; j < subNameArray.length(); j++) {
						JSONObject jsonObject3 = subNameArray.getJSONObject(j);
						
						int subId = Integer.parseInt(jsonObject3.getString("id"));
						String subName = jsonObject3.getString("name");
						
						SubLocation subLocTemp = new SubLocation(subId, subName);
						
						location.get(i+1).getSubLocation().add(subLocTemp);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private Runnable addToMerchantList = new Runnable() {
		
		@Override
		public void run() {
			if (location != null && location.size() > 0) {
				m_adapter.notifyDataSetChanged();
				for (int i = 0; i < location.size(); i++) {
					m_adapter.add(location.get(i));
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
		Location lInfo = location.get(position);
		SubCategoryListingPage.location = lInfo;
		Intent subLocations = new Intent(instance, SubCategoryListingPage.class);
		startActivity(subLocations);
		instance.finish();
	};
	
	private class ListViewAdapter extends ArrayAdapter<Location> {
		private ArrayList<Location> location;
		
		public ListViewAdapter(Context context, int resourceLayoutId, ArrayList<Location> location) {
			super(context, resourceLayoutId, location);
			this.location = location;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			
			if(view == null) {
				LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = layoutInflater.inflate(R.layout.category_list, null);
			}
			
			final Location loc = location.get(position);
			if(loc != null) {
				TextView categoryName = (TextView)view.findViewById(R.id.categoryName);
				categoryName.setText(loc.getName());
			}
			
			return view;
		}
	}
	
	@Override
	protected void onDestroy() {
		SingtelDiningMainPage.isFirst = true;
		super.onDestroy();
	}
}
