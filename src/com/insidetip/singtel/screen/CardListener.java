package com.insidetip.singtel.screen;

import android.view.View;
import android.view.View.OnClickListener;

public class CardListener implements OnClickListener {
	
	@Override
	public void onClick(View v) {
		CustomImageView civ = (CustomImageView)v;
		
		if(!civ.getIsPressed()) {
			civ.setImageResource(civ.getImageInfo().getIdLabel());
			civ.setIsPressed(true);
		}
		else {
			civ.setImageResource(civ.getImageInfo().getId());
			civ.setIsPressed(false);
		}
	}
}
