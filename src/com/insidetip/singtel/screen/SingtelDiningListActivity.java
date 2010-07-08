package com.insidetip.singtel.screen;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class SingtelDiningListActivity extends ListActivity {
	
	private Activity activity;
	private SingtelDiningListActivity singtelDiningListActivity;
	
	protected void initActivity(Activity activity) {
		this.activity = activity;
		singtelDiningListActivity = this;
	}

	class MenuListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch(v.getId()) {
				case R.id.settingsButton:
					Intent settings = new Intent(activity, SettingsPage.class);
					startActivity(settings);
					break;
				case R.id.favoriteButton:
					
					break;
			}
		}
		
	}
}
