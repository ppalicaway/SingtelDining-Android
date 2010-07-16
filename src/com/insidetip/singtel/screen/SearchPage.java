package com.insidetip.singtel.screen;

import com.insidetip.singtel.util.Constants;
import com.insidetip.singtel.util.Util;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SearchPage extends SingtelDiningActivity {

	public static SearchPage instance;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(R.style.Theme_Translucent);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.searchpage);
		
		instance = this;
		initActivity(instance);
		
		init();
	}

	private void init() {
		final EditText keyword = (EditText)findViewById(R.id.searchKeywordEditText);
		
		Util.showKeyboard(instance);
		
		Button search = (Button)findViewById(R.id.searchSubmitButton);
		search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String searchText = keyword.getText().toString();
				searchText = searchText.replaceAll(" ", "%20");
				SingtelDiningMainPage.searchText = keyword.getText().toString();
				SingtelDiningMainPage.URL = Constants.RESTAURANT_SEARCH + searchText + SettingsPage.bankQuery + "&pageNum=";
				instance.finish();
			}
		});
	}
}
