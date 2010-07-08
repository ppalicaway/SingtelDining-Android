package com.insidetip.singtel.screen;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SettingsPage extends SingtelDiningListActivity {

	public static SettingsPage instance;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(R.style.Theme_Translucent);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.settings);
		
		final Button citibank = (Button)findViewById(R.id.citibankButton);
		final Button dbs = (Button)findViewById(R.id.dbsButton);
		final Button ocbc = (Button)findViewById(R.id.ocbcButton);
		final Button uob = (Button)findViewById(R.id.uobButton);
		
		citibank.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				citibank.setBackgroundResource(R.drawable.citibank_hover);
				dbs.setBackgroundResource(R.drawable.dbs);
				ocbc.setBackgroundResource(R.drawable.ocbc);
				uob.setBackgroundResource(R.drawable.uob);
			}
		});
		
		dbs.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				citibank.setBackgroundResource(R.drawable.citibank);
				dbs.setBackgroundResource(R.drawable.dbs_hover);
				ocbc.setBackgroundResource(R.drawable.ocbc);
				uob.setBackgroundResource(R.drawable.uob);
			}
		});
		
		ocbc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				citibank.setBackgroundResource(R.drawable.citibank);
				dbs.setBackgroundResource(R.drawable.dbs);
				ocbc.setBackgroundResource(R.drawable.ocbc_hover);
				uob.setBackgroundResource(R.drawable.uob);
			}
		});
		
		uob.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				citibank.setBackgroundResource(R.drawable.citibank);
				dbs.setBackgroundResource(R.drawable.dbs);
				ocbc.setBackgroundResource(R.drawable.ocbc);
				uob.setBackgroundResource(R.drawable.uob_hover);
			}
		});
	}
}
