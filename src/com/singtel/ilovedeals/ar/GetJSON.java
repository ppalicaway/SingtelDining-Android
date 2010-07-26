package com.singtel.ilovedeals.ar;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.singtel.ilovedeals.info.MerchantInfo;
import com.singtel.ilovedeals.screen.ARScreen;
import com.singtel.ilovedeals.screen.SettingsPage;
import com.singtel.ilovedeals.util.Constants;
import com.singtel.ilovedeals.util.Util;

public class GetJSON implements Runnable {

	private HashMap<String, String> merchantHash;
	
	@Override
	public void run() {
		String result = "";
		
		result = Util.getHttpData(Constants.RESTAURANT_LOCATION_PAGE + Util.latitude +
			      "&longitude=" + Util.longitude +
			      "&resultsPerPage=20" + SettingsPage.bankQuery + "&pageNum=1");
		result = Util.toJSONString(result);
		
		ARScreen.merchantList = new ArrayList<MerchantInfo>();
		merchantHash = new HashMap<String, String>();
		
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
					String distance = "";
					
					id = Integer.parseInt(jsonObject2.getString("ID"));
					image = jsonObject2.getString("Image");
					restaurantName = jsonObject2.getString("RestaurantName");
					address = jsonObject2.getString("Address");
					rating = Float.parseFloat(jsonObject2.getString("Rating"));
					reviews = Integer.parseInt(jsonObject2.getString("Reviews"));
					latitude = Double.parseDouble(jsonObject2.getString("Latitude"));
					longitude = Double.parseDouble(jsonObject2.getString("Longitude"));
					distance = jsonObject2.getString("Distance");
					
					MerchantInfo mInfo = new MerchantInfo(id, image, restaurantName, address, rating, reviews, latitude, longitude);
					mInfo.setDistance(distance);
					
					if(!merchantHash.containsKey(id)) {
						merchantHash.put(Integer.toString(id), Integer.toString(id));
						ARScreen.merchantList.add(mInfo);
					}
				}
				
				Thread t = new Thread(new ReturnRes());
				t.start();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		catch(Exception e) {
			try {
				if (ARScreen.progressDialog.isShowing()) {
					ARScreen.progressDialog.dismiss();
				}					
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
