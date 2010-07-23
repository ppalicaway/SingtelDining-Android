package com.singtel.ilovedeals.screen;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

import com.singtel.ilovedeals.util.Constants;
import com.singtel.ilovedeals.util.Util;

public class SingtelDining extends Activity {
	
	public static SingtelDining instance;
	protected boolean isActive = true;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Black_NoTitleBar);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);
        instance = this;
        
        SingtelDiningMainPage.totalPage = 1;
        SingtelDiningMainPage.page = 1;
        SingtelDiningMainPage.totalItems = 1;
        
        try {
        	ImageView splashImage = (ImageView)findViewById(R.id.splashImgView);
        	Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.splash);
        	int newWidth = Util.getScreenWidth(instance) + 18;
        	int newHeight = Util.getScreenHeight(instance) + 10;
        	image = Util.resizeImage(image, newWidth, newHeight);
        	splashImage.setImageBitmap(image);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
        System.out.println("Network Provider: " + Util.getNetworkProvider(instance));
        if(!Util.getNetworkProvider(instance).contains("SingTel")) {
        	Util.showAlert(instance, "ILoveDeals", "This service is available only on a Singtel mobile network and at selected Wireless@SG hotspots.", "OK", true);
        }
        else {
        	splashThread.start();
        }
    }
    
    private Thread splashThread = new Thread() {
    	@Override
		public void run() {
			try {
				int waited = 0;
				while(isActive &&(waited < Constants.SPLASH_WAIT_MILLISEC)) {
					sleep(100);
					if(isActive) {
						waited += 100;
					}
				}
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
			finally {
				finish();
				SharedPreferences shared = getSharedPreferences(Constants.DEFAULT_SHARE_DATA, 0);
				boolean isFirstTime = shared.getBoolean("isFirstTime", true);
				SharedPreferences.Editor edit = shared.edit();
				
				if(isFirstTime) {
					edit.putBoolean("isFirstTime", false);
					edit.commit();
					startActivity(new Intent(instance, SettingsPage.class));
				}
				else {
					startActivity(new Intent(instance, SingtelDiningMainPage.class));
				}
				stop();
			}
		}
    };
}