package com.singtel.ilovedeals.ar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CustomCameraView extends SurfaceView {
	
	Camera camera;
	SurfaceHolder previewHolder;
	Context context;

	public CustomCameraView(Context context) {
		super(context);
		this.context = context;
		previewHolder = this.getHolder();
		previewHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		previewHolder.addCallback(surfaceHolderListener);
		setBackgroundColor(Color.TRANSPARENT);
	}
	
	public CustomCameraView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	SurfaceHolder.Callback surfaceHolderListener = new SurfaceHolder.Callback() {
		
		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			camera.release();
			camera = null;
		}
		
		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			camera = Camera.open();
			
			try {
				camera.setPreviewDisplay(previewHolder);
			}
			catch(Throwable t) {
				t.printStackTrace();
			}
		}
		
		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
			Camera.Parameters parameters = camera.getParameters();
			parameters.setPreviewSize(width, height);
			camera.setParameters(parameters);
			camera.startPreview();
		}
	};
	
	protected void onDraw(android.graphics.Canvas canvas) {
		super.onDraw(canvas);
	};
	
	public void closeCamera() {
		if (camera != null) {
			camera.release();
		}
	}
	
	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
	}
}
