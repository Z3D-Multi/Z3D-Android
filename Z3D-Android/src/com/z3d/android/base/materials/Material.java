package com.z3d.android.base.materials;

import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;

import com.z3d.android.base.Shader;

import android.opengl.GLES20;

/*
 * Simple abstract class doing simple operations to make Materials.
 */
public abstract class Material 
{
	protected Shader shader;
	public abstract String getType();
	public Shader getShader()
	{
		return shader;
	}

	public void setUniform(String uniformName,int value) throws Exception
	{
		shader.setUniform(uniformName, value);
	}
	public void setUniform(String uniformName,Vector4f value) throws Exception
	{
		shader.setUniform(uniformName, value);
	}
	public void setUniform(String uniformName,float value) throws Exception
	{
		shader.setUniform(uniformName, value);
	}
	public void setUniform(String uniformName,Vector3f value) throws Exception
	{
		shader.setUniform(uniformName, value);
	}
	public void setUniform(String uniformName,Matrix4f value) throws Exception
	{
		shader.setUniform(uniformName, value);
	}
	public void setUniform(String uniformName, float[] value) throws Exception 
	{
		shader.setUniform(uniformName, value);
	}
}
