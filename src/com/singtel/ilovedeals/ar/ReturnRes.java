package com.singtel.ilovedeals.ar;

import com.singtel.ilovedeals.screen.ARScreen;
import com.singtel.ilovedeals.screen.R;
import com.singtel.ilovedeals.util.Util;

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
