package com.z3d.android.base.materials;

import com.z3d.android.base.ResourceLoader;

import android.content.Context;

/*
 * static color is achieved by setting the uniform vColor.
 * Not setting this might make the object invisible.
 */
public class SimpleStaticColorObject extends BaseMaterial 
{
	public SimpleStaticColorObject(int p,Context c)
	{
		super(p,c);
	}
	protected void addInitUniforms()
	{
		super.addInitUniforms();
		shader.addUniform("vColor");
	}
	@Override
	protected void loadShader() throws Exception 
	{
		shader.addVertexShader(ResourceLoader.loadShader("vertex1.vxs",mContext));
		shader.addFragmentShader(ResourceLoader.loadShader("fragment1.fxs",mContext));
	}

}
