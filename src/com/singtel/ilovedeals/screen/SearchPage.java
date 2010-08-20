package com.singtel.ilovedeals.screen;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.singtel.ilovedeals.util.Constants;
import com.singtel.ilovedeals.util.Util;
import com.singtel.ilovedeals.screen.R;

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
				SharedPreferences shared = getSharedPreferences(Constants.DEFAULT_SHARE_DATA, 0);
				SharedPreferences.Editor edit = shared.edit();
				
				String searchText = keyword.getText().toString();
				searchText = searchText.replaceAll(" ", "%20");
				
				SingtelDiningMainPage.searchText = keyword.getText().toString();
				SingtelDiningMainPage.URL = Constants.RESTAURANT_SEARCH + searchText + SettingsPage.bankQuery + "&pageNum=";
				
				edit.putString("searchKeyword", keyword.getText().toString());
				edit.putString("searchURL", Constants.RESTAURANT_SEARCH + searchText + SettingsPage.bankQuery + "&pageNum=");
				edit.commit();
				
				Util.disableKeyboard(instance, keyword);
				
				instance.finish();
			}
		});
	}
}
