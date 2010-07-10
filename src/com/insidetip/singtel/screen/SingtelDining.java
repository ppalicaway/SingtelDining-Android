package com.insidetip.singtel.screen;

import com.insidetip.singtel.util.Constants;
import com.insidetip.singtel.util.Util;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

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
        
        splashThread.start();
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
				startActivity(new Intent(instance, SingtelDiningMainPage.class));
				stop();
			}
		}
    };
}