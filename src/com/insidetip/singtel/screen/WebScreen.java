package com.insidetip.singtel.screen;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.insidetip.singtel.util.Constants;
import com.insidetip.singtel.util.Util;

public class WebScreen extends SingtelDiningActivity {

	public static WebScreen instance;
	private WebView webView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(android.R.style.Theme_Black_NoTitleBar);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.webscreen);
		
		instance = this;
		initActivity(instance);
		settingLayout();
	}

	private void settingLayout() {
		webView = (WebView)findViewById(R.id.myWebView);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl(Constants.SITE_TNC);
		webView.setWebViewClient(new SingtelWebViewClient());
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK) {
			
			if(webView.canGoBack()) {
				webView.goBack();
			}
			else {
				instance.finish();
			}
			return true;
		}
		
		return super.onKeyDown(keyCode, event);
	}
	
	private class SingtelWebViewClient extends WebViewClient {
	    @Override
	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
	    	if(!url.contains("tel:")) {
	    		view.loadUrl(url);
	    	}
	    	else {
	    		Util.makeCall(instance, url);
	    	}
	    	view.showContextMenu();
	        return true;
	    }
	}
}
