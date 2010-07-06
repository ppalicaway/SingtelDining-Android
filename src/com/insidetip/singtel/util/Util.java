package com.insidetip.singtel.util;

import android.content.Context;
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
}
