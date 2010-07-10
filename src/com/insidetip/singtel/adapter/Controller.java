package com.insidetip.singtel.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;

import com.insidetip.singtel.info.MerchantInfo;
import com.insidetip.singtel.map.MapLocationViewer;
import com.insidetip.singtel.screen.DescriptionPage;
import com.insidetip.singtel.screen.MapPage;
import com.insidetip.singtel.screen.SingtelDiningMainPage;

public class Controller {

	public static void displayMapScreen(Context context) {
		if(context instanceof DescriptionPage) {
			ArrayList<MerchantInfo> mapList = new ArrayList<MerchantInfo>();
			mapList.add(DescriptionPage.merchantInfo);
			MapLocationViewer.setMapLocations(mapList, 0, false);
			Intent intent = new Intent(context, MapPage.class);
			context.startActivity(intent);
		}
		else if(context instanceof SingtelDiningMainPage) {
			MapLocationViewer.setMapLocations(SingtelDiningMainPage.merchantList, 0, false);
			Intent intent = new Intent(context, MapPage.class);
			context.startActivity(intent);
		}
	}
}
