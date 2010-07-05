package com.insidetip.singtel.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Description extends SingtelDiningActivity {

	public static Description instance;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(R.style.Theme_Translucent);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.details_page);
		
		instance = this;
		
		Button twitter = (Button)findViewById(R.id.twitterButton);
		twitter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent twitterPage = new Intent(instance, TwitterPage.class);
				startActivity(twitterPage);
			}
		});
	}
}
