package com.insidetip.singtel.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.telephony.TelephonyManager;
import android.view.Display;
import android.view.WindowManager;

public class Util {

	public static int getScreenWidth(Context c){
		Display display = ((WindowManager)c.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay(); 
		return display.getWidth(); 
	}
	
	public static int getScreenHeight(Context c){
		Display display = ((WindowManager)c.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay(); 
		return display.getHeight(); 
	}
	
	public static Bitmap resizeImage(Bitmap bitmapOrg, int width_, int height_) {
		Bitmap resizedBitmap = bitmapOrg;
		try {
			int width = bitmapOrg.getWidth();
			int height = bitmapOrg.getHeight();
			int newWidth = width_;
			int newHeight = height_;

			float scaleWidth = ((float) newWidth) / width;
			float scaleHeight = ((float) newHeight) / height;

			Matrix matrix = new Matrix();
			matrix.postScale(scaleWidth, scaleHeight);
			matrix.postRotate(0);

			resizedBitmap = Bitmap.createBitmap(bitmapOrg, 0, 0, width, height, matrix, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resizedBitmap;
	}
	
	public static void showAlert(Activity act, final String title, final String msg, final String buttontext, final boolean finishScreen) {
		final Activity myact = act;
		
		try {
			act.runOnUiThread(new Runnable() {
				public void run() {
					AlertDialog alertDialog = new AlertDialog.Builder(myact).create();
					alertDialog.setTitle(title);
					alertDialog.setMessage(msg);
					alertDialog.setButton(buttontext,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									if (finishScreen) {
										myact.finish();
									}
									return;
								}
							});
					alertDialog.show();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getNetworkProvider(Context context) {
		String networkProvider = null;
		try {
			TelephonyManager telephonyManager;
			telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
			networkProvider = telephonyManager.getNetworkOperatorName();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return networkProvider;
	}
	
	public static String getHttpData(String url) {
		System.out.println("URL: " + url);
		String result = null;
		int timeOutMS = 1000*10;
		try {
			HttpParams my_httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(my_httpParams, timeOutMS);
			HttpConnectionParams.setSoTimeout(my_httpParams, timeOutMS);
			DefaultHttpClient client = new DefaultHttpClient(my_httpParams);
			URI uri = new URI(url);
			HttpGet httpGetRequest = new HttpGet(uri);
			HttpResponse response = client.execute(httpGetRequest);
			
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				response.getEntity().writeTo(os);
				result = os.toString();
			}
		} 
		catch (SocketTimeoutException e) {
			return Constants.ERROR_CODE_TIME_OUT;
		}
		catch (ClientProtocolException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			return Constants.ERROR_CODE_UNKNOW_HOST;

		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static Bitmap getBitmap(String fileUrl) {
		Bitmap bmImg = null;
		URL myFileUrl = null;
		
		try {
			myFileUrl = new URL(fileUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		try {
			HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
			conn.setDoInput(true);
			conn.connect();
			int length = conn.getContentLength();
			InputStream is = conn.getInputStream();
			bmImg = BitmapFactory.decodeStream(is);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return bmImg;
	}
	
	public static String toJSONString(String result){
		if(result.startsWith("("))
			return result.substring(1);
		if(!result.startsWith("{")){
			int index = result.indexOf("{");
			return result.substring(index);
		}		
		else
			return result;
	}
}
