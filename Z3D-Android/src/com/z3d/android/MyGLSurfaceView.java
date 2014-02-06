package com.z3d.android;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.z3d.android.base.Input;

public class MyGLSurfaceView extends GLSurfaceView {

    private final MyRenderer mRenderer;
	public MyGLSurfaceView(Context context) {
		super(context);
		setEGLContextClientVersion(2);
		mRenderer=new MyRenderer(context);
		this.setEGLConfigChooser(true);
		this.setRenderer(mRenderer);
	}
	@Override
	public void onDetachedFromWindow()
	{
		super.onDetachedFromWindow();
		mRenderer.cleanup();
	}
	
}
