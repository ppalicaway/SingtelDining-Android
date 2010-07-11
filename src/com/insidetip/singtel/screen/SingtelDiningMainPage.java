package com.insidetip.singtel.screen;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.insidetip.singtel.adapter.Controller;
import com.insidetip.singtel.info.MerchantInfo;
import com.insidetip.singtel.util.Constants;
import com.insidetip.singtel.util.Util;

public class SingtelDiningMainPage extends SingtelDiningListActivity {
	
	public static SingtelDiningMainPage instance;
	public static ArrayList<MerchantInfo> merchantList;
	private ListViewAdapter m_adapter;
	private Runnable queryThread;
	private ProgressDialog progressDialog = null;
	
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
		
		try {
			Thread coords = new Thread(new Runnable() {
				
				@Override
				public void run() {
					double latLong[] = Util.queryLatLong(instance);
				}
			});
			coords.start();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		merchantList = new ArrayList<MerchantInfo>();
		m_adapter = new ListViewAdapter(instance, R.layout.merchant_list, merchantList);
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
		
		Button mapButton = (Button) findViewById(R.id.mapButton);
		mapButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Controller.displayMapScreen(instance);
			}
		});
	}

	protected void getData() {
		String result = "";
		
		result = Util.getHttpData(Constants.RESTAURANT_LINK);
		
		if(result == null || result.equalsIgnoreCase("408") || result.equalsIgnoreCase("404")) {
			//TODO:
		}
		else {
			result = Util.toJSONString(result);
			merchantList = new ArrayList<MerchantInfo>();
			
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
						merchantList.add(mInfo);
					}
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private Runnable addToMerchantList = new Runnable() {
		
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
	};
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		MerchantInfo mInfo = merchantList.get(position);
		DescriptionPage.merchantInfo = mInfo;
		Intent details = new Intent(instance, DescriptionPage.class);
		startActivity(details);
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
				TextView merchantName = (TextView)view.findViewById(R.id.merchantName);
				merchantName.setText(merchant.getRestaurantName());
				
				TextView merchantAddress = (TextView)view.findViewById(R.id.merchantAddress);
				merchantAddress.setText(merchant.getAddress());
				
				ImageView merchantPic = (ImageView)view.findViewById(R.id.merchantPic);
				Bitmap bitmap;
				
				if(!merchant.getImage().equals(null) || !merchant.getImage().equalsIgnoreCase("")) {
					bitmap = Util.getBitmap(merchant.getImage());
					if(bitmap != null) {
						bitmap = Util.resizeImage(bitmap, 55, 55);
						merchantPic.setImageBitmap(bitmap);
					}
					else {
						merchantPic.setImageResource(R.drawable.default_icon);
					}
				}
				else {
					merchantPic.setImageResource(R.drawable.default_icon);
				}
			}
			
			return view;
		}
	}
}