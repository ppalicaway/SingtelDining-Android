package com.insidetip.singtel.ar;

import com.insidetip.singtel.screen.ARScreen;
import com.insidetip.singtel.screen.R;
import com.insidetip.singtel.util.Util;

public class ReturnRes implements Runnable {

	@Override
	public void run() {
		if (ARScreen.merchantList != null && ARScreen.merchantList.size() > 0) {
			ARScreen.addMerchant();
		}
		else {
			Util.showAlert(ARScreen.instance, "", 
					"",
					ARScreen.instance.getResources().getString(R.string.ok), true);
		}
		try {
			if (ARScreen.progressDialog.isShowing())
				ARScreen.progressDialog.dismiss();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
