package com.insidetip.singtel.screen;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class TermsPage extends SingtelDiningActivity {

	public TermsPage instance;
	public static String termsAndCondition;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(R.style.Theme_Translucent);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.terms);
		
		instance = this;
		initActivity(instance);
		
		TextView termsAndConditionView = (TextView)findViewById(R.id.tncTextView);
		termsAndConditionView.setText(termsAndCondition);
		
		Button okButton = (Button)findViewById(R.id.termsPageOKButton);
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				instance.finish();
			}
		});
	}
}
