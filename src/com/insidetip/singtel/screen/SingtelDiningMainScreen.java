package com.insidetip.singtel.screen;

import android.os.Bundle;
import android.view.Window;

public class SingtelDiningMainScreen extends SingtelDiningActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(R.style.Theme_Translucent);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.twitter);
	}
}
