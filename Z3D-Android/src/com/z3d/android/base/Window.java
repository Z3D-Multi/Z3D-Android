package com.z3d.android.base;

import android.opengl.GLES20;
import android.opengl.Matrix;

public class Window 
{
	private static float aspectRatio;
	public static void reshape(int w,int h)
	{
		GLES20.glViewport(0, 0, w, h);
		aspectRatio=(float)w/h;
		float[] mProjection=new float[16];
		Matrix.frustumM(mProjection, 0, -aspectRatio,aspectRatio,-1.0f,1.0f, 1,1000);
		Transform.setProjection(mProjection);
	}
	public static float getAspectRatio()
	{
		return aspectRatio;
	}
	
}
