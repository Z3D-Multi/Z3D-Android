package com.z3d.android;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;

import com.z3d.android.base.Game;
import com.z3d.android.base.MainComponent;
import com.z3d.android.base.Window;

public class MyRenderer implements Renderer 
{
	public static final float FRAME_CAP=30.0f;
	Context mContext;
	MainComponent mc;
	public MyRenderer(Context context)
	{
		this.mContext=context;
	}
	@Override
	public void onDrawFrame(GL10 gl) 
	{
		mc.run();
	}

	public void cleanup()
	{
		mc.stop();
		mc.cleanup();
	}
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) 
	{
		Window.reshape(width, height);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// Set the background frame color
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        mc=new MainComponent(mContext);
        mc.start();
        mc.setFrameCap(FRAME_CAP);
	}

}
