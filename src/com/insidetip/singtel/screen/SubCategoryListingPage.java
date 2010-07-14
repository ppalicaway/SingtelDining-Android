package com.insidetip.singtel.screen;

import java.util.ArrayList;

import com.insidetip.singtel.info.Location;
import com.insidetip.singtel.info.SubLocation;
import com.insidetip.singtel.util.Constants;
import com.insidetip.singtel.util.Util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SubCategoryListingPage extends SingtelDiningListActivity {

	public SubCategoryListingPage instance;
	public static Location location;
	private static ArrayList<SubLocation> subLocations = new ArrayList<SubLocation>();
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
		subLocations = new ArrayList<SubLocation>();		
		m_adapter = new ListViewAdapter(instance, R.layout.category_list, subLocations);
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
		subLocations = new ArrayList<SubLocation>();
		subLocations = location.getSubLocation();
	}

	private Runnable addToMerchantList = new Runnable() {

		@Override
		public void run() {
			if (subLocations != null && subLocations.size() > 0) {
				m_adapter.notifyDataSetChanged();
				for (int i = 0; i < subLocations.size(); i++) {
					m_adapter.add(subLocations.get(i));
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
		if(subLocations.get(position).getId() == -101) {
			SingtelDiningMainPage.URL = 
				Constants.RESTAURANT_LOCATION_PAGE + Util.latitude +
				"&longitude=" + Util.longitude +
				"&resultsPerPage=20&bank=Citibank,DBS,OCBC,UOB&pageNum=" + SingtelDiningMainPage.page;
		}
		else {
			SingtelDiningMainPage.URL = Constants.RESTAURANT_LOCATION_PLACES + subLocations.get(position).getId() + "&resultsPerPage=20&bank=Citibank,DBS,OCBC,UOB&pageNum=";
		}
		SingtelDiningMainPage.searchText = location.getName() + " - " + subLocations.get(position).getName();
		instance.finish();
	};
	
	private class ListViewAdapter extends ArrayAdapter<SubLocation> {
		private ArrayList<SubLocation> subLocation;

		public ListViewAdapter(Context context, int resource, ArrayList<SubLocation> subLocations) {
			super(context, resource, subLocations);
			this.subLocation = subLocations;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			
			if(view == null) {
				LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = layoutInflater.inflate(R.layout.category_list, null);
			}
			
			final SubLocation loc = subLocation.get(position);
			if(loc != null) {
				TextView categoryName = (TextView)view.findViewById(R.id.categoryName);
				categoryName.setText(loc.getName());
			}
			
			return view;
		}
		
	}
}
