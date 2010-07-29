package com.singtel.ilovedeals.screen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.codecarpet.fbconnect.FBFeedActivity;
import com.codecarpet.fbconnect.FBLoginButton;
import com.codecarpet.fbconnect.FBRequest;
import com.codecarpet.fbconnect.FBSession;
import com.codecarpet.fbconnect.FBLoginButton.FBLoginButtonStyle;
import com.codecarpet.fbconnect.FBRequest.FBRequestDelegate;
import com.codecarpet.fbconnect.FBSession.FBSessionDelegate;
import com.singtel.ilovedeals.db.DBManager;
import com.singtel.ilovedeals.info.BankOffer;
import com.singtel.ilovedeals.info.ImageInfo;
import com.singtel.ilovedeals.info.MerchantDetails;
import com.singtel.ilovedeals.info.MerchantInfo;
import com.singtel.ilovedeals.util.Constants;
import com.singtel.ilovedeals.util.Util;

public class DescriptionPage extends SingtelDiningActivity {

	public static DescriptionPage instance;
	private static boolean isFlipped = true;
	public static MerchantInfo merchantInfo;
	public static MerchantDetails merchantDetails;
	public static int catID;
	
	private FBSession fbSession;
	private FBLoginButton facebookButton;
	private final String GET_SESSION_PROXY = null;
	private TextView offer;
	private ImageView merchantPic;
	private ProgressDialog progressDialog = null;
	private Runnable queryThread;
	private String offerFirst = "";
	private Bitmap bitmapDefault;
	
	private static final int MESSAGE_PUBLISHED = 2;
	
	public static ArrayList<String> banks = new ArrayList<String>();
	
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
		
		progressDialog = ProgressDialog.show(this, "", getString(R.string.loading), true);
		
		queryThread = new Runnable() {
			
			@Override
			public void run() {
				getData();
				runOnUiThread(populateData);
			}
		};
		
		Thread thread = new Thread(null, queryThread, "queryThread");
		thread.start();
		
		Button twitter = (Button)findViewById(R.id.twitterButton);
		twitter.setOnClickListener(new ButtonEvents());
				
		Button mapButton = (Button) findViewById(R.id.mapButton);
		mapButton.setOnClickListener(new ButtonEvents());
		
		Button infoButton = (Button)findViewById(R.id.infoButton);
		infoButton.setOnClickListener(new ButtonEvents());
		
		Button phoneButton = (Button)findViewById(R.id.phoneButton);
		phoneButton.setOnClickListener(new ButtonEvents());
		
		facebookButton = (FBLoginButton) findViewById(R.id.facebookButton);
		facebookButton.setStyle(FBLoginButtonStyle.FBLoginButtonStyleWide);
		facebookButton.setSession(fbSession);
		fbSession.resume(this);
		
		final Button addFave = (Button)findViewById(R.id.detailsAddFaveButton);
		final Button removeFave = (Button)findViewById(R.id.detailsRemoveFaveButton);
		
		addFave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				removeFave.setVisibility(Button.VISIBLE);
				addFave.setVisibility(Button.GONE);
				try {
					DBManager dbMgr = new DBManager(DescriptionPage.instance, Constants.DB_NAME);
					dbMgr.insertMerchant(DescriptionPage.merchantInfo);
					dbMgr.close();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				Util.showAlert(instance, "ILoveDeals", "Successfully added to Favourites.", "OK", false);
			}
		});
		
		removeFave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				removeFave.setVisibility(Button.GONE);
				addFave.setVisibility(Button.VISIBLE);
				try {
					DBManager dbMgr = new DBManager(DescriptionPage.instance, Constants.DB_NAME);
					dbMgr.deleteMerchant(DescriptionPage.merchantInfo);
					dbMgr.close();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				Util.showAlert(instance, "ILoveDeals", "Successfully removed from Favourites.", "OK", false);
			}
		});
		
		try {
			DBManager dbMgr = new DBManager(instance, Constants.DB_NAME);
			if(dbMgr.isMerchantExist(merchantInfo)) {
				removeFave.setVisibility(Button.VISIBLE);
				addFave.setVisibility(Button.GONE);
			}
			else {
				removeFave.setVisibility(Button.GONE);
				addFave.setVisibility(Button.VISIBLE);
			}
			dbMgr.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void reloadCardImage() {
		InnerCardListener cardListener = new InnerCardListener();
		TableRow tableRow = (TableRow)findViewById(R.id.tableRow);
		tableRow.removeAllViews();
		LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		for(int i = 0; i < banks.size(); i++) {
			for(int j = 0; j < SettingsPage.images.size(); j++) {
				CustomImageView view = (CustomImageView) inflater.inflate(R.layout.row_cell, null);
				if(SettingsPage.images.get(j).getBankName().equalsIgnoreCase(banks.get(i))) {
					view.setImageResource(SettingsPage.images.get(j).getNonticked());
					view.setImageInfo(SettingsPage.images.get(j));
					view.setOnClickListener(cardListener);
					tableRow.addView(view);
				}
			}
		}
		
		for(int k = 0; k < banks.size(); k++) {
			for(int l = 0; l < SettingsPage.untickedImages.size(); l++) {
				CustomImageView view = (CustomImageView) inflater.inflate(R.layout.row_cell, null);
				if(SettingsPage.untickedImages.get(l).getBankName().equalsIgnoreCase(banks.get(k))) {
					view.setImageResource(SettingsPage.untickedImages.get(l).getNonticked());
					view.setImageInfo(SettingsPage.untickedImages.get(l));
					view.setOnClickListener(cardListener);
					tableRow.addView(view);
				}
			}
		}
		
		offerFirst = ((CustomImageView)tableRow.getChildAt(0)).getImageInfo().getBankName();
	}

	private Runnable populateData = new Runnable() {

		@Override
		public void run() {
			
			TextView merchantName = (TextView)findViewById(R.id.merchantName);
			merchantName.setText(merchantDetails.getTitle());
			
			TextView merchantType = (TextView)findViewById(R.id.merchantType);
			merchantType.setText(merchantDetails.getType());
			
			TextView merchantAddress = (TextView)findViewById(R.id.merchantAddress);
			merchantAddress.setText(merchantDetails.getAddress());
			
			TextView merchantPhone = (TextView)findViewById(R.id.merchantPhone);
			merchantPhone.setText(merchantDetails.getPhone());
			
			TextView merchantDescription = (TextView)findViewById(R.id.descriptionTextView);
			merchantDescription.setText(merchantDetails.getDescription());
			
			for(int i = 0; i < merchantDetails.getBankOffers().size(); i++) {
				String bank = merchantDetails.getBankOffers().get(i).getBank();
				banks.add(bank);
			}
			
			LinearLayout branchGroup = (LinearLayout)findViewById(R.id.branchGroup);
			Button branchButton = (Button)findViewById(R.id.branchButton);
			final TextView branchesText = (TextView)findViewById(R.id.branchesText);
			branchesText.setText(merchantDetails.getBranches());
			
			if(merchantDetails.getBranches().equalsIgnoreCase("")) {
				branchGroup.setVisibility(LinearLayout.GONE);
			}
			
			branchButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					branchesText.setVisibility(TextView.VISIBLE);
				}
			});
			
			reloadCardImage();
			
			offer = (TextView)findViewById(R.id.offerTextView);
			String offerText = merchantDetails.getBankOffers().get(getMerchantBankIndex(offerFirst)).getBank() + " Offer:\n";
			offerText += merchantDetails.getBankOffers().get(getMerchantBankIndex(offerFirst)).getOffer();
			offer.setText(offerText);
			
			
			merchantPic = (ImageView)findViewById(R.id.merchantPic);
			if(!merchantDetails.getImage().equals(null) || !merchantDetails.getImage().equalsIgnoreCase("")) {
				bitmapDefault = Util.getBitmap(merchantDetails.getImage());
				if(bitmapDefault != null) {
					bitmapDefault = Util.resizeImage(bitmapDefault, 90, 70);
				}
				else {
					bitmapDefault = BitmapFactory.decodeResource(SingtelDiningMainPage.instance.getResources(), R.drawable.default_icon1);
				}
			}
			else {
				bitmapDefault = BitmapFactory.decodeResource(SingtelDiningMainPage.instance.getResources(), R.drawable.default_icon1);
			}
			
			if(merchantDetails.getBankOffers().get(getMerchantBankIndex(offerFirst)).getBank().equalsIgnoreCase("Citibank")) {
				merchantPic.setImageResource(R.drawable.gourmetpleasure);
			}
			else {
				merchantPic.setImageBitmap(bitmapDefault);
			}
			
			TermsPage.termsAndCondition = merchantDetails.getBankOffers().get(getMerchantBankIndex(offerFirst)).getTnc();
			
			if(progressDialog.isShowing()) {
				progressDialog.dismiss();
			}
		}		
	};

	protected void getData() {
		String result = "";
		result = Util.getHttpData(Constants.RESTAURANT_DETAIL + catID);
		
		if(result == null || result.equalsIgnoreCase("408") || result.equalsIgnoreCase("404")) {
			//TODO
		}
		else {
			result = Util.toJSONString(result);
			merchantDetails = new MerchantDetails();
			
			try {
				JSONObject jsonObject1 = new JSONObject(result);
				JSONArray names = jsonObject1.names();
				JSONArray valArray = jsonObject1.toJSONArray(names);
				JSONObject jsonObject2 = valArray.getJSONObject(0);
				
				int id = Integer.parseInt(jsonObject2.getString("id"));
				String image = jsonObject2.getString("img");
				String thumbnail = jsonObject2.getString("thumb");
				String title = jsonObject2.getString("title");
				String type = jsonObject2.getString("type");
				double rating = Double.parseDouble(jsonObject2.getString("rating"));
				int reviews = Integer.parseInt(jsonObject2.getString("reviews"));
				String address = jsonObject2.getString("address");
				String phone = jsonObject2.getString("phone");
				double latitude = Double.parseDouble(jsonObject2.getString("latitude"));
				double longitude = Double.parseDouble(jsonObject2.getString("longitude"));
				String description = jsonObject2.getString("description");
				
				merchantDetails.setId(id);
				merchantDetails.setImage(image);
				merchantDetails.setThumbnail(thumbnail);
				merchantDetails.setTitle(title);
				merchantDetails.setType(type);
				merchantDetails.setRating(rating);
				merchantDetails.setReviews(reviews);
				merchantDetails.setAddress(address);
				merchantDetails.setPhone(phone);
				merchantDetails.setLatitude(latitude);
				merchantInfo.setLatitude(latitude);
				merchantDetails.setLongitude(longitude);
				merchantInfo.setLongitude(longitude);
				merchantDetails.setDescription(description);
				
				JSONArray bankInfo = jsonObject2.getJSONArray("offers");
				
				for(int i = 0; i < bankInfo.length(); i++) {
					JSONObject jsonObject3 = bankInfo.getJSONObject(i);
					String bankname = jsonObject3.getString("bank");
					String card = jsonObject3.getString("card");
					String offer = jsonObject3.getString("offer");
					String tnc = jsonObject3.getString("tnc");
					
					merchantDetails.getBankOffers().add(new BankOffer(bankname, card, offer, tnc));
				}
				
				String branch = "";
				try {
					JSONArray branches = jsonObject2.getJSONArray("branches");
					for(int a = 0; a < branches.length(); a++) {
						branch += "- " + branches.getString(a) + "\n";
					}
					merchantDetails.setBranches(branch);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void checkPermission() {
		String fql = "select publish_stream from permissions where uid == " + String.valueOf(fbSession.getUid());
        Map<String, String> params = Collections.singletonMap("query", fql);
        FBRequest.requestWithDelegate(new FBHasPermissionRD()).call("facebook.fql.query", params);
	}
	
	public void publishFeed() {
		String nameMerchant = Constants.FACEBOOK_NAME + "ILoveDeals" + "\"";
		String hrefMerchant = Constants.FACEBOOK_HREF + "http://www.singtel.com/ilovedeals/\"";
		String captionMerchant = Constants.FACEBOOK_CAPTION + "Search for ILoveDeals on Apple appstore or Android Market." + "\"";
		String descriptionMerchant = Constants.FACEBOOK_DESCRIPTION + "\"";
		String mediaMerchant = Constants.FACEBOOK_MEDIA + "" + "http://singtel.dc2go.net/singtel/images/icon.png" + "\"";
		String hrefMedia = Constants.FACEBOOK_HREF + merchantDetails.getImage() +"\"";
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
	
	private String getOffer(String bankName) {
		String offer = "";
		for(int i = 0; i < merchantDetails.getBankOffers().size(); i++) {
			if(bankName.equalsIgnoreCase(merchantDetails.getBankOffers().get(i).getBank())) {
				offer = merchantDetails.getBankOffers().get(i).getBank() + " Offer:\n";
				offer += merchantDetails.getBankOffers().get(i).getOffer();
			}
			else if(bankName.equalsIgnoreCase("POSB")) {
				offer = bankName + " Offer:\n";
				offer += merchantDetails.getBankOffers().get(getMerchantBankIndex("DBS")).getOffer();
			}
		}
		return offer;
	}
	
	private class InnerCardListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			CustomImageView civ = (CustomImageView)v;
			Animation animation = AnimationUtils.loadAnimation(instance.getApplicationContext(), R.anim.hyperspace_out);
			LinearLayout ll = (LinearLayout)findViewById(R.id.detailFlipper);					
			ll.startAnimation(animation);
			String bankName = civ.getImageInfo().getBankName();
			if(civ.getImageInfo().getBankName().equalsIgnoreCase("DBS")) {
				if(civ.getImageInfo().getId() == R.drawable.dbs_platinum_mastercard) {
					bankName = "POSB";
				}
			}
			offer.setText(getOffer(bankName));
			
			if(civ.getImageInfo().getBankName().equalsIgnoreCase("Citibank")) {
				merchantPic.setImageResource(R.drawable.gourmetpleasure);
			}
			else {
				merchantPic.setImageBitmap(bitmapDefault);
			}
			
			int bankIndex = getMerchantBankIndex(civ.getImageInfo().getBankName());
			TermsPage.termsAndCondition = merchantDetails.getBankOffers().get(bankIndex).getTnc();
		}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		SingtelDiningMainPage.isListing = false;
		super.onResume();
	}

	public int getMerchantBankIndex(String bankName) {
		int index = 0;
		for(int i = 0; i < banks.size(); i++) {
			if(banks.get(i).equalsIgnoreCase(bankName)) {
				index = i;
			}
		}
		return index;
	}
}
