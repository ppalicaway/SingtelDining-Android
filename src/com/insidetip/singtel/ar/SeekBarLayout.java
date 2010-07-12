package com.insidetip.singtel.ar;

import java.text.DecimalFormat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.Display;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.insidetip.singtel.screen.ARScreen;
import com.insidetip.singtel.screen.R;
import com.insidetip.singtel.util.Constants;

public class SeekBarLayout extends FrameLayout implements OnSeekBarChangeListener {

	private SeekBar seekBar;
	private TextView leftTView, rightTView;
	private Context context;
	private SharedPreferences shared;
	private double prog;
	
	public SeekBarLayout(Context context) {
		super(context);
		this.context = context;
		LinearLayout linearLayout = new LinearLayout(context);
		linearLayout.setGravity(Gravity.CENTER);
		linearLayout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		linearLayout.setOrientation(LinearLayout.HORIZONTAL);
		
		leftTView = new TextView(context);
		leftTView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		leftTView.setText("0 km");
		leftTView.setPadding(0, 0, 10, 0);
		leftTView.setTextColor(Color.WHITE);
		
		rightTView = new TextView(context);
		rightTView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		rightTView.setText("10 km");
		rightTView.setPadding(10, 0, 0, 0);
		rightTView.setTextColor(Color.WHITE);
		
		seekBar = new SeekBar(context);
		seekBar.setLayoutParams(new LayoutParams(300, LayoutParams.WRAP_CONTENT));
		seekBar.setOnSeekBarChangeListener(this);
		seekBar.setMax(10000);
		seekBar.setProgress((int) (ARScreen.radius * 1000));
		
		linearLayout.addView(leftTView);
		linearLayout.addView(seekBar);
		linearLayout.addView(rightTView);
		
		WindowManager w = ARScreen.instance.getWindowManager();
        Display d = w.getDefaultDisplay();
        int width = d.getWidth();
     	int height = d.getHeight();
     	
     	LayoutParams mainParams = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		setPadding(0, height - 50, 0, 0);
		setLayoutParams(mainParams);
		
		this.addView(linearLayout);
		shared = context.getSharedPreferences(Constants.DEFAULT_SHARE_DATA, 0);
		double radius = shared.getFloat("radius", (float) 0.8);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		prog = (double)progress / 1000;
		DecimalFormat df = new DecimalFormat("#.##");
		String distance = df.format(prog);
		leftTView.setText(distance + " km");
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		ARScreen.radius = (float) prog;
		
		if(!ARScreen.isMerchantList) {
			ARScreen.progressDialog = ProgressDialog.show(context, "", context.getResources().getString(R.string.retrieving), true);
			Thread t = new Thread(null, new GetJSON(), "initR");
			t.start();
		}
	}

}
