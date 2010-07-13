package com.insidetip.singtel.screen;

import com.insidetip.singtel.util.Util;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TwitterPage extends SingtelDiningActivity {
	
	public static TwitterPage instance;
	private Twitter twitter;
	private String userName, password, tweet, singtelText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(R.style.Theme_Translucent);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.twitter);
		
		instance = this;
		initActivity(instance);
		
		settingLayout();
	}

	private void settingLayout() {
		final EditText userNameEditText = (EditText)findViewById(R.id.twitterUsernameEditText);
		final EditText passwordEditText = (EditText)findViewById(R.id.twitterPasswordEditText);
		final EditText tweetEditText = (EditText)findViewById(R.id.twitterTweetEditText);
		TextView singtelTextView = (TextView)findViewById(R.id.twitterExtraText);
				
		singtelText = singtelTextView.getText().toString();
		
		Button submitButton = (Button)findViewById(R.id.twitterSubmitButton);
		submitButton.setOnClickListener(new OnClickListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				userName = userNameEditText.getText().toString();
				password = passwordEditText.getText().toString();
				tweet = tweetEditText.getText().toString();
				String status = tweet + " " + singtelText;
				
				if(!userName.equalsIgnoreCase("") && !userName.equals(null) &&
				   !password.equalsIgnoreCase("") && !password.equals(null)) {
					twitter = new Twitter(userName, password);
					
					try {
						twitter.setStatus(status);
						Util.showAlert(instance, getResources().getString(R.string.twitter), getResources().getString(R.string.successful_twitter), getResources().getString(R.string.ok), true);
					}
					catch (TwitterException.E401 e) {
						Util.showAlert(instance, getResources().getString(R.string.twitter), getResources().getString(R.string.wrong_info), getResources().getString(R.string.ok), false);
					}
					catch (Exception e) {
						Util.showAlert(instance, getResources().getString(R.string.twitter), getResources().getString(R.string.not_responding), getResources().getString(R.string.ok), false);
					}
				}
				else {
					Util.showAlert(instance, getResources().getString(R.string.twitter), getResources().getString(R.string.input_all_data), getResources().getString(R.string.ok), false);
				}
			}
		});
	}
}
