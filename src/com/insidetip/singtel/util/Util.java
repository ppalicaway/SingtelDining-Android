package com.insidetip.singtel.util;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

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
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.view.Display;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

public class Util {
	
	public static double latitude;
	public static double longitude;

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
	
	public static void makeCall(Context context, String number) {
		if(!number.startsWith("tel:")) {
			number = "tel:" + number;
		}
		Intent intent = new Intent(Intent.ACTION_CALL);
		intent.setData(Uri.parse(number));
		context.startActivity(intent);
	}
	
	public static double[] queryLatLong(Context context) {
		double latLong[] = new double[2];
		
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		GsmCellLocation location = (GsmCellLocation) tm.getCellLocation();
		
		int cellID = location.getCid();
		int lac = location.getLac();
		
		try {
			URL url = new URL(Constants.GOOGLE_MAP_API);
			URLConnection conn = url.openConnection();
			HttpURLConnection httpCon = (HttpURLConnection)conn;
			httpCon.setRequestMethod("POST");
			httpCon.setDoOutput(true);
			httpCon.setDoInput(true);
			httpCon.connect();
			
			OutputStream outputStream = httpCon.getOutputStream();
			WriteData(outputStream, cellID, lac);
			
			InputStream inputStream = httpCon.getInputStream();  
	        DataInputStream dataInputStream = new DataInputStream(inputStream);
	        
	        dataInputStream.readShort();
	        dataInputStream.readByte();
	        int code = dataInputStream.readInt();

	        if(code == 0) {
	        	double lat = (double) dataInputStream.readInt() / 1000000D;
	            double lng = (double) dataInputStream.readInt() / 1000000D;
	            dataInputStream.readInt();
	            dataInputStream.readInt();
	            dataInputStream.readUTF();
	            
	            latLong[0] = lat;
	            latLong[1] = lng;
	            
	            System.out.println("Latitude: " + lat);
	            System.out.println("Longitude: " + lng);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return latLong;
	}
	
	private static void WriteData(OutputStream out, int cellID, int lac) 
    throws IOException
    {    	
        DataOutputStream dataOutputStream = new DataOutputStream(out);
        dataOutputStream.writeShort(21);
        dataOutputStream.writeLong(0);
        dataOutputStream.writeUTF("en");
        dataOutputStream.writeUTF("Android");
        dataOutputStream.writeUTF("1.0");
        dataOutputStream.writeUTF("Web");
        dataOutputStream.writeByte(27);
        dataOutputStream.writeInt(0);
        dataOutputStream.writeInt(0);
        dataOutputStream.writeInt(3);
        dataOutputStream.writeUTF("");

        dataOutputStream.writeInt(cellID);  
        dataOutputStream.writeInt(lac);     

        dataOutputStream.writeInt(0);
        dataOutputStream.writeInt(0);
        dataOutputStream.writeInt(0);
        dataOutputStream.writeInt(0);
        dataOutputStream.flush();    	
    }
	
	public static float distanceTo(double latitude1, double longitude1, double latitude2, double longitude2) {
		
		float distance = 0;
		int MAXITERS = 20;
		
		latitude1 *= Math.PI / 180.0;
		latitude2 *= Math.PI / 180.0;
		longitude1 *= Math.PI / 180.0;
		longitude2 *= Math.PI / 180.0;
		
		double a = 6378137.0;
		double b = 6356752.3142;
		double f = (a - b) / a;
		double aSqMinusBSqOverBSq = (a * a - b * b) / (b * b);
		
		double L = longitude2 - longitude1;
		double A = 0.0;
		double U1 = Math.atan((1.0 - f) * Math.tan(latitude1));
		double U2 = Math.atan((1.0 - f) * Math.tan(latitude2));
		
		double cosU1 = Math.cos(U1);
		double cosU2 = Math.cos(U2);
		double sinU1 = Math.sin(U1);
		double sinU2 = Math.sin(U2);
		double cosU1cosU2 = cosU1 * cosU2;
		double sinU1sinU2 = sinU1 * sinU2;
		
		double sigma = 0.0;
		double deltaSigma = 0.0;
		double cosSqAlpha = 0.0;
		double cos2SM = 0.0;
		double cosSigma = 0.0;
		double sinSigma = 0.0;
		double cosLambda = 0.0;
		double sinLambda = 0.0;
		
		double lambda = L;
		
		for (int iter = 0; iter < MAXITERS; iter++) {
			double lambdaOrig = lambda;
			cosLambda = Math.cos(lambda);
			sinLambda = Math.sin(lambda);
			double t1 = cosU2 * sinLambda;
			double t2 = cosU1 * sinU2 - sinU1 * cosU2 * cosLambda;
			double sinSqSigma = t1 * t1 + t2 * t2;
			sinSigma = Math.sqrt(sinSqSigma);
			cosSigma = sinU1sinU2 + cosU1cosU2 * cosLambda;
			sigma = Math.atan2(sinSigma, cosSigma);
			double sinAlpha = (sinSigma == 0) ? 0.0 :
				cosU1cosU2 * sinLambda / sinSigma;
			cosSqAlpha = 1.0 - sinAlpha * sinAlpha;
			cos2SM = (cosSqAlpha == 0) ? 0.0 :
				cosSigma - 2.0 * sinU1sinU2 / cosSqAlpha;
			
			double uSquared = cosSqAlpha * aSqMinusBSqOverBSq;
			A = 1 + (uSquared / 16384.0) *
				(4096.0 + uSquared *
				 (-768 + uSquared * (320.0 - 175.0 * uSquared)));
			double B = (uSquared / 1024.0) *
				(256.0 + uSquared *
				 (-128.0 + uSquared * (74.0 - 47.0 * uSquared)));
			double C = (f / 16.0) *
				cosSqAlpha *
				(4.0 + f * (4.0 - 3.0 * cosSqAlpha));
			double cos2SMSq = cos2SM * cos2SM;
			deltaSigma = B * sinSigma *
				(cos2SM + (B / 4.0) *
				 (cosSigma * (-1.0 + 2.0 * cos2SMSq) -
				  (B / 6.0) * cos2SM *
				  (-3.0 + 4.0 * sinSigma * sinSigma) *
				  (-3.0 + 4.0 * cos2SMSq)));
			
			lambda = L +
				(1.0 - C) * f * sinAlpha *
				(sigma + C * sinSigma *
				 (cos2SM + C * cosSigma *
				  (-1.0 + 2.0 * cos2SM * cos2SM)));
			
			double delta = (lambda - lambdaOrig) / lambda;
			if (Math.abs(delta) < 1.0e-12) {
				break;
			}
		}
		
		distance = (float) (b * A * (sigma - deltaSigma)) / 1000;
		
		return distance;
	}

	public static String getGPSAddress(Activity activity, double latitude, double longitude) {
		String addressName = "";
		Geocoder geoCoder = new Geocoder(activity.getBaseContext(), Locale.getDefault());
		try {
			List<Address> addresses = geoCoder.getFromLocation(latitude, longitude, 1);
			String add = "";
			if (addresses.size() > 0) {
				for (int i = 0; i < addresses.get(0).getMaxAddressLineIndex(); i++)
					add += addresses.get(0).getAddressLine(i) + " ";
			}
			addressName = add;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return addressName;
	}
	
	public static String getLocalIpAddress() {
	    try {
	        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
	            NetworkInterface intf = en.nextElement();
	            for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
	                InetAddress inetAddress = enumIpAddr.nextElement();
	                if (!inetAddress.isLoopbackAddress()) {
	                    return inetAddress.getHostAddress().toString();
	                }
	            }
	        }
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    }
	    return null;
	}
	
	public static void showKeyboard(Context context){
		InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.SHOW_IMPLICIT);
	}
}
