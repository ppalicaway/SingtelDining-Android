package com.insidetip.singtel.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Description extends SingtelDiningActivity {

	public static Description instance;
	private static boolean isFlipped = true;
	
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
		
		ImageView creditCards = (ImageView)findViewById(R.id.creditCardImageView);
		creditCards.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Animation animation = AnimationUtils.loadAnimation(instance.getApplicationContext(), R.anim.hyperspace_out);
				LinearLayout ll = (LinearLayout)findViewById(R.id.detailFlipper);
				TextView offer = (TextView)findViewById(R.id.offerTextView);
				ll.startAnimation(animation);
				if(isFlipped) {
					offer.setText("Citibank Offer:\n1 for 1 Dinner promo\nValid till jun 2010");
					isFlipped = false;
				}
				else {
					offer.setText("Citibank Offer:\n1 for 1 Lunch promo\nValid till jun 2010");
					isFlipped = true;
				}
			}
		});
	}
}
