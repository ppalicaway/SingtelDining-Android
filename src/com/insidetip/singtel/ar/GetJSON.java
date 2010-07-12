package com.insidetip.singtel.ar;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.insidetip.singtel.info.MerchantInfo;
import com.insidetip.singtel.screen.ARScreen;
import com.insidetip.singtel.util.Constants;
import com.insidetip.singtel.util.Util;

public class GetJSON implements Runnable {

	private HashMap<String, String> merchantHash;
	
	@Override
	public void run() {
		String result = "";
		
		result = Util.getHttpData(Constants.RESTAURANT_LINK);
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
					
					id = Integer.parseInt(jsonObject2.getString("ID"));
					image = jsonObject2.getString("Image");
					restaurantName = jsonObject2.getString("RestaurantName");
					address = jsonObject2.getString("Address");
					rating = Float.parseFloat(jsonObject2.getString("Rating"));
					reviews = Integer.parseInt(jsonObject2.getString("Reviews"));
					latitude = Double.parseDouble(jsonObject2.getString("Latitude"));
					longitude = Double.parseDouble(jsonObject2.getString("Longitude"));
					
					MerchantInfo mInfo = new MerchantInfo(id, image, restaurantName, address, rating, reviews, latitude, longitude);
					
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
			e.printStackTrace();
		}
	}
}
