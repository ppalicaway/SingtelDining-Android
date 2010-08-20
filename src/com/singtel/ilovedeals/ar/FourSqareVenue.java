package com.singtel.ilovedeals.ar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.Paint.Style;

import com.singtel.ilovedeals.info.MerchantInfo;
import com.singtel.ilovedeals.util.Util;
import com.singtel.ilovedeals.screen.R;

public class FourSqareVenue extends ARSphericalView {
	
	private Paint innerPaint, borderPaint, textPaint;
	private Bitmap bitmap;
	private int bitmapW, bitmapH;
	private Paint fontTop, fontMiddle, fontBottom, fontDistance;
	public MerchantInfo merchantInfo;
	private Context context;
	String address = "";
	public String name = "";

	public FourSqareVenue(Context context) {
		super(context);
		this.context = context;
		inclination = 0;
		bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ar_bg);
		bitmapW = bitmap.getWidth();
		bitmapH = bitmap.getHeight();
		
		fontTop = FontDefault(12, Color.BLACK, true);
		fontMiddle = FontDefault(10, Color.BLACK, false);
		fontBottom = FontDefault(10, Color.BLACK, false);
		fontDistance = FontDefault(12, Color.BLACK, false);
	}
	
	@Override
	public void draw(Canvas canvas) {
		paint.setColor(Color.WHITE);
		
		if (merchantInfo != null) {
			address = merchantInfo.getAddress();
			
			if(address.length() > 20) {
				address = address.substring(0, 20) + "...";
			}
			
			name = merchantInfo.getRestaurantName();
			
			if(name.length() > 17) {
				name = name.substring(0, 17) + "...";
			}
			
			String dist = merchantInfo.getDistance();
			
			int contentWidth = ARLayout.contentWidth;
			int contentHeight = ARLayout.contentHeight;
			RectF infoWindowRect = new RectF(0, 0, contentWidth, contentHeight);
			int start_x = getLeft() - contentWidth / 2;
			int start_y = getTop() - contentHeight / 2;
			infoWindowRect.offset(start_x, start_y);
			
			//canvas.drawRoundRect(infoWindowRect, 5, 5, getInnerPaint());
			//canvas.drawRoundRect(infoWindowRect, 5, 5, getBorderPaint());
			
			int offset_x = 10;
			int offset_y = 25;
			int x = start_x + offset_x;
			int y = start_y + offset_y;
			
			canvas.drawBitmap(bitmap, x - 21, y - 24, null);			
			canvas.drawText(name, x + 15, y - 3, fontTop);
		
			if(dist.length() > 4) {
				dist = dist.substring(0,4);
			}
			
			canvas.drawText(dist, (x - 15), y , fontDistance);
			canvas.drawText(" km", (x - 15), y + 10 , fontDistance);
			
			y += 20;
			String promoText = merchantInfo.getAddress();
			
			if(promoText.length() > 20) {
				promoText = promoText.substring(0, 20) + "...";
			}
			
			canvas.drawText(promoText,(x + 15), y - 6, fontMiddle);	
		}
	}
	
	public Paint getInnerPaint() {
		if (innerPaint == null) {
			innerPaint = new Paint();
			innerPaint.setAlpha(180);
			innerPaint.setARGB(225, 75, 75, 75);
			innerPaint.setAntiAlias(true);
		}
		
		return innerPaint;
	}
	
	public Paint getBorderPaint() {
		if (borderPaint == null) {
			borderPaint = new Paint();
			innerPaint.setAlpha(180);
			borderPaint.setARGB(255, 255, 255, 255);
			borderPaint.setAntiAlias(true);
			borderPaint.setStyle(Style.STROKE);
			borderPaint.setStrokeWidth(2);
		}
		
		return borderPaint;
	}
	
	public Paint getTextPaint() {
		if (textPaint == null) {
			textPaint = new Paint();
			textPaint.setARGB(255, 255, 255, 255);
			textPaint.setAntiAlias(true);
		}
		
		return textPaint;
	}
	
	public static Paint FontDefault(int size, int color, boolean isBold) {
		Paint textPaint = new Paint();
		textPaint.setTextSize(size);
		textPaint.setColor(color);
		
		if(isBold) {
			textPaint.setTypeface(Typeface.DEFAULT_BOLD);
		}
		else {
			textPaint.setTypeface(Typeface.DEFAULT);
		}
		
		textPaint.setTextAlign(Paint.Align.LEFT);
		textPaint.setAntiAlias(true);
		
		return textPaint;
	}
}
