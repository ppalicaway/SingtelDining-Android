package com.insidetip.singtel.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class SingtelDiningMainScreen extends SingtelDiningActivity {
	
	public static SingtelDiningMainScreen instance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(R.style.Theme_Translucent);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mainscreen);
		
		instance = this;
		
		ImageView merchantPic = (ImageView)findViewById(R.id.merchantPic);
		merchantPic.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent details = new Intent(instance, Description.class);
				startActivity(details);
			}
		});
		
		Button settingsButton = (Button)findViewById(R.id.settingsButton);
		settingsButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent settings = new Intent(instance, SettingsPage.class);
				startActivity(settings);
			}
		});
	}
}
