package com.insidetip.singtel.screen;

import java.util.Collections;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Bitmap;
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

import com.codecarpet.fbconnect.FBFeedActivity;
import com.codecarpet.fbconnect.FBLoginButton;
import com.codecarpet.fbconnect.FBRequest;
import com.codecarpet.fbconnect.FBSession;
import com.codecarpet.fbconnect.FBLoginButton.FBLoginButtonStyle;
import com.codecarpet.fbconnect.FBRequest.FBRequestDelegate;
import com.codecarpet.fbconnect.FBSession.FBSessionDelegate;
import com.insidetip.singtel.adapter.Controller;
import com.insidetip.singtel.info.MerchantInfo;
import com.insidetip.singtel.util.Constants;
import com.insidetip.singtel.util.Util;

public class DescriptionPage extends SingtelDiningActivity {

	public static DescriptionPage instance;
	private static boolean isFlipped = true;
	public static MerchantInfo merchantInfo;
	
	private FBSession fbSession;
	private FBLoginButton facebookButton;
	private final String GET_SESSION_PROXY = null;
	private TextView offer;
	
	private static final int MESSAGE_PUBLISHED = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(R.style.Theme_Translucent);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		
		if(GET_SESSION_PROXY != null) {
			fbSession = FBSession.getSessionForApplication_getSessionProxy(Constants.FACEBOOK_API_KEY, GET_SESSION_PROXY, new FBSessionDelegateImpl());
		}
		else {
			fbSession = FBSession.getSessionForApplication_secret(Constants.FACEBOOK_API_KEY, Constants.FACEBOOK_API_SECRETKEY, new FBSessionDelegateImpl());
		}
		
		setContentView(R.layout.details_page);
		instance = this;
		init();
	}
	
	private void init() {
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
				offer = (TextView)findViewById(R.id.offerTextView);
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
		
		TextView merchantName = (TextView)findViewById(R.id.merchantName);
		merchantName.setText(merchantInfo.getRestaurantName());
		
		Bitmap bitmap;
		ImageView merchantPic = (ImageView)findViewById(R.id.merchantPic);
		if(!merchantInfo.getImage().equals(null) || !merchantInfo.getImage().equalsIgnoreCase("")) {
			bitmap = Util.getBitmap(merchantInfo.getImage());
			if(bitmap != null) {
				bitmap = Util.resizeImage(bitmap, 90, 70);
				merchantPic.setImageBitmap(bitmap);
			}
			else {
				merchantPic.setImageResource(R.drawable.default_icon1);
			}
		}
		else {
			merchantPic.setImageResource(R.drawable.default_icon1);
		}
		
		TextView merchantAddress = (TextView)findViewById(R.id.merchantAddress);
		merchantAddress.setText(merchantInfo.getAddress());
		
		Button mapButton = (Button) findViewById(R.id.mapButton);
		mapButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Controller.displayMapScreen(instance);
			}
		});
		
		facebookButton = (FBLoginButton) findViewById(R.id.facebookButton);
		facebookButton.setStyle(FBLoginButtonStyle.FBLoginButtonStyleWide);
		facebookButton.setSession(fbSession);
		fbSession.resume(this);
	}

	private void checkPermission() {
		String fql = "select publish_stream from permissions where uid == " + String.valueOf(fbSession.getUid());
        Map<String, String> params = Collections.singletonMap("query", fql);
        FBRequest.requestWithDelegate(new FBHasPermissionRD()).call("facebook.fql.query", params);
	}
	
	public void publishFeed() {
		String nameMerchant = Constants.FACEBOOK_NAME + merchantInfo.getRestaurantName() + "\"";
		String hrefMerchant = Constants.FACEBOOK_HREF + "http://www.singtel.com/\"";
		String captionMerchant = Constants.FACEBOOK_CAPTION + merchantInfo.getAddress() + "\"";
		String descriptionMerchant = Constants.FACEBOOK_DESCRIPTION + "\"";
		String mediaMerchant = Constants.FACEBOOK_MEDIA + "" + merchantInfo.getImage() + "\"";
		String hrefMedia = Constants.FACEBOOK_HREF + merchantInfo.getImage() +"\"";
		String propertiesMerchant = Constants.FACEBOOK_PROPERTIES;
		
		Intent intent = new Intent(this, FBFeedActivity.class);
        intent.putExtra("userMessagePrompt", "Example prompt");
        intent.putExtra("attachment", "{" + nameMerchant + "," + hrefMerchant + "," + captionMerchant + "," + descriptionMerchant + "," + mediaMerchant + "," + hrefMedia + "}]," + propertiesMerchant);
        this.startActivityForResult(intent, MESSAGE_PUBLISHED);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == MESSAGE_PUBLISHED) {
			fbSession.logout(instance);
			return;
		}
    }
	
	private class FBSessionDelegateImpl extends FBSessionDelegate {
		
		@Override
		public void sessionDidLogin(FBSession session, Long uid) {
			checkPermission();
			
			String fql = "select uid,name from user where uid == " + session.getUid();

            Map<String, String> params = Collections.singletonMap("query", fql);
            FBRequest.requestWithDelegate(new FBRequestDelegateImpl()).call("facebook.fql.query", params);
            
            publishFeed();
		}
		
		@Override
		public void sessionDidLogout(FBSession session) {
		}
	}
	
	private class FBHasPermissionRD extends FBRequestDelegate {
		
		@Override
		public void requestDidFailWithError(FBRequest request, Throwable error) {
			super.requestDidFailWithError(request, error);
		}
		
		@Override
		public void requestDidLoad(FBRequest request, Object result) {
			int hasPermission = 0;
			
			if(result instanceof JSONArray) {
				JSONArray jsonArray = (JSONArray) result;
                try {
                    JSONObject jo = jsonArray.getJSONObject(0);
                    hasPermission = jo.getInt("publish_stream");                    
                } catch (JSONException e) {
                    e.printStackTrace();
                }
			}
		}
	}	
	
	private class FBRequestDelegateImpl extends FBRequestDelegate {
		
		@Override
		public void requestDidLoad(FBRequest request, Object result) {
			String name = null;

            if (result instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) result;
                try {
                    JSONObject jo = jsonArray.getJSONObject(0);
                    name = jo.getString("name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
		}
		
		@Override
		public void requestDidFailWithError(FBRequest request, Throwable error) {
			super.requestDidFailWithError(request, error);
		}
	}
}
