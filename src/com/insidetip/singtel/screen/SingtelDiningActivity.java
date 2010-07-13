package com.insidetip.singtel.screen;

import com.insidetip.singtel.adapter.Controller;
import com.insidetip.singtel.util.Util;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class SingtelDiningActivity extends Activity {

	private Activity activity;
	SingtelDiningActivity singtelDiningActivity;
	
	protected void initActivity(Activity activity) {
		this.activity = activity;
		singtelDiningActivity = this;
	}
	
	protected class ButtonEvents implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch(v.getId()) {
				case R.id.termsTextView:
					TermsPage.termsAndCondition = DescriptionPage.merchantDetails.getBankOffers().get(0).getTnc();
					Intent termsPage = new Intent(DescriptionPage.instance, TermsPage.class);
					startActivity(termsPage);
					break;
				case R.id.phoneButton:
					Util.makeCall(DescriptionPage.instance, DescriptionPage.merchantDetails.getPhone());
					break;
				case R.id.mapButton:
					Controller.displayMapScreen(DescriptionPage.instance);
					break;
				case R.id.twitterButton:
					Intent twitterPage = new Intent(DescriptionPage.instance, TwitterPage.class);
					startActivity(twitterPage);
					break;
			}
		}
		
	}
}
