package com.z3d.android.base;

import android.opengl.GLES20;

public class RenderUtil 
{
	public static void clearScreen()
	{
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT|GLES20.GL_DEPTH_BUFFER_BIT);
		
		// Set the camera
        /*Camera camera = Transform::getCamera();
        Vector3f eyePosition = camera.getPos();
        Vector3f eyeCenter = eyePosition + camera.getForward();
        Vector3f eyeUp = camera.getUp();
        gluLookAt(        eyePosition.getX(), eyePosition.getY(), eyePosition.getZ(),
                                eyeCenter.getX(), eyeCenter.getY(), eyeCenter.getZ(),
                                eyeUp.getX(), eyeUp.getY(),  eyeUp.getZ());
         */
	}
	public static void initGraphics()
	{
		//Commented out ones are not allowed. find other means to implement them.
		GLES20.glClearColor(0.0f,0.0f,0.0f,1.0f);
		//GLES20.glEnable(GLES20.GL_DEPTH_TEST);
		GLES20.glFrontFace(GLES20.GL_CW);
		GLES20.glCullFace(GLES20.GL_BACK);
		//GLES20.glEnable(GLES20.GL_CULL_FACE);
		
		//TODO: depth clamp
		
		GLES20.glEnable(GLES20.GL_TEXTURE_2D);
		//GLES20.glEnable(GLES20.GL_FRAMEBUFFER);		//SRGB not available.
	}
	public static String getOpenGLVersion()
	{
		return GLES20.glGetString(GLES20.GL_VERSION);
	}
	public static void setTextures(boolean enabled)
	{
		if(enabled)
			GLES20.glEnable(GLES20.GL_TEXTURE_2D);
		else GLES20.glDisable(GLES20.GL_TEXTURE_2D);
	}
}
