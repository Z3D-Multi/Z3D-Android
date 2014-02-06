package com.z3d.android.base;

import java.util.HashMap;
import java.util.Map;

import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;

import android.opengl.GLES20;
import android.opengl.GLU;
import android.util.Log;

public class Shader 
{
	private int program;
	private Map<String,Integer>uniformMap;
	
	public Shader(int program) throws Exception
	{
		this.program=program;
		uniformMap=new HashMap<String, Integer>();
		if(this.program==0)
			throw new Exception("creating program failed");
	}
	private void addShaderProgram(String text,int type) throws Exception
	{
		int shader=GLES20.glCreateShader(type);
		if(shader==0)
			throw new Exception("shader creation failed");
		GLES20.glShaderSource(shader, text);
		GLES20.glCompileShader(shader);
		Error.getShaderiv(shader, GLES20.GL_COMPILE_STATUS);
		GLES20.glAttachShader(program, shader);
	}
	public void compileShader() throws Exception
	{
		GLES20.glLinkProgram(program);
		Error.getProgramiv(program, GLES20.GL_LINK_STATUS);
		GLES20.glValidateProgram(program);
		//Gives an invalid enum error. why?
		//Error.getProgramiv(program, GLES20.GL_COMPILE_STATUS);
	}
	public void bind()
	{
		GLES20.glUseProgram(program);
	}
	public void addVertexShader(String text) throws Exception
	{
		this.addShaderProgram(text, GLES20.GL_VERTEX_SHADER);
	}
	public void addFragmentShader(String text) throws Exception
	{
		this.addShaderProgram(text, GLES20.GL_FRAGMENT_SHADER);
	}
	public void addGeometryShader(String text) throws Exception
	{
		//this.addShaderProgram(text, GLES20.GL_Geo);	no geomerty shader! :/
	}
	public void addUniform(String uni)
	{
		try
		{
		int uniformLocation=GLES20.glGetUniformLocation(program, uni);
		if(uniformLocation<0)
			throw new Exception("error adding uniform");
		uniformMap.put(uni,Integer.valueOf(uniformLocation));
		if(uniformMap.get(uni).intValue()!=uniformLocation)
			throw new Exception("error adding uniform");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public void setUniform(String uniformName,int value)
	{
		GLES20.glUniform1i(uniformMap.get(uniformName).intValue(),value);
	}
	public void setUniform(String uniformName,Vector4f value)
	{
		GLES20.glUniform4fv(uniformMap.get(uniformName).intValue(),1,
				new float[]{value.getX(),value.getY(),value.getZ(),value.getW()},0);
	}
	public void setUniform(String uniformName,float value)
	{
		GLES20.glUniform1f(uniformMap.get(uniformName).intValue(),value);
	}
	public void setUniform(String uniformName,Vector3f value)
	{
		GLES20.glUniform3f(uniformMap.get(uniformName).intValue(),value.getX(),value.getY()
				,value.getZ());
	}
	public void setUniform(String uniformName,Matrix4f value) throws Exception
	{
		int n=uniformMap.get(uniformName).intValue();
		float[] matrix=Util.createFlippedBuffer(value);
		GLES20.glUniformMatrix4fv(n,1,false,matrix,0);
		
		int errCode=GLES20.glGetError();
		if(errCode!=GLES20.GL_NO_ERROR)
		{
			String err=GLU.gluErrorString(errCode);
			throw new Exception("OpenGL error: "+err);
		}
	}
	public void setUniform(String uniformName, float[] mvpMatrix) 
	{
		int n=uniformMap.get(uniformName).intValue();
		GLES20.glUniformMatrix4fv(n, 1,false,mvpMatrix,0);
	}
	
}
