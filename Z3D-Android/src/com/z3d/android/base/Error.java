package com.z3d.android.base;

import android.opengl.GLES20;
import android.opengl.GLU;
import android.util.Log;

public class Error 
{
	public static void getProgramiv(int program,int type) throws Exception
	{
		int[] result=new int[1];
		GLES20.glGetProgramiv(program, type, result,0);
		if(result[0]==0)
		{
			String err=GLES20.glGetProgramInfoLog(program);
			throw new Exception(type+" program error occurred- "+err);
		}
	}
	public static void getShaderiv(int program,int type) throws Exception
	{
		int[] result=new int[1];
		GLES20.glGetShaderiv(program, type, result,0);
		if(result[0]==0)
		{
			String err=GLES20.glGetProgramInfoLog(program);
			throw new Exception(type+" shader error occurred- "+err);
		}
	}
	public static void LogOpenglError()
	{
		Log.d("OpenGL Error: ",GLU.gluErrorString(GLES20.glGetError()));
	}
}
