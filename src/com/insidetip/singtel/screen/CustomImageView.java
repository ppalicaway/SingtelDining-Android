package com.insidetip.singtel.screen;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.insidetip.singtel.info.ImageInfo;

public class CustomImageView extends ImageView {

	private ImageInfo imageInfo;
	private boolean isPressed = false;
	
	public CustomImageView(Context context) {
		super(context);
	}

	public CustomImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public CustomImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public void setImageInfo(ImageInfo imageInfo) {
		this.imageInfo = imageInfo;
	}

	public ImageInfo getImageInfo() {
		return imageInfo;
	}
	
	public void setIsPressed(boolean isPressed) {
		this.isPressed = isPressed;
	}
	
	public boolean getIsPressed() {
		return isPressed;
	}
}
