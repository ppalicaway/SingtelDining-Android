package com.singtel.ilovedeals.screen;

import android.app.Activity;
import android.app.ListActivity;

public class SingtelDiningListActivity extends ListActivity {
	
	private Activity activity;
	private SingtelDiningListActivity singtelDiningListActivity;
	
	protected void initActivity(Activity activity) {
		this.activity = activity;
		singtelDiningListActivity = this;
	}
}
