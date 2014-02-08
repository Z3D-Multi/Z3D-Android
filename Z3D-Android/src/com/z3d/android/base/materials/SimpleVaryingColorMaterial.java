package com.z3d.android.base.materials;

import com.z3d.android.base.ResourceLoader;

import android.content.Context;

/*
 * Varying of color in this class is done with respect to position of vertices.
 * See shader vertex2.vxs for more info.
 * This class does not use any additional uniforms.
 */
public class SimpleVaryingColorMaterial extends BaseMaterial 
{
	public SimpleVaryingColorMaterial(int program,Context c) throws Exception 
	{
		super(program,c);
	}

	@Override
	protected void loadShader() throws Exception 
	{
		shader.addVertexShader(ResourceLoader.loadShader("vertex2.vxs",mContext));
		shader.addFragmentShader(ResourceLoader.loadShader("fragment.fxs", mContext));
		shader.compileShader();
	}

}
