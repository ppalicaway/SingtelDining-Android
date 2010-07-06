package com.insidetip.singtel.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Matrix;
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
}
