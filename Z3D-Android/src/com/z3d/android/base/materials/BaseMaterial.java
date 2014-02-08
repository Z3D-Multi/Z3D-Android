package com.z3d.android.base.materials;

import com.z3d.android.base.Shader;

import android.content.Context;

/*
 * A basic material class which allows for simple shaders which use
 * transform and MVPMatrix uniforms.
 * For shaders which do not use these, subclass Material class.
 */
public abstract class BaseMaterial extends Material 
{
	private static final String TYPE="Basic Material";
	protected Context mContext;
	public BaseMaterial(int program,Context c)
	{
		try {
			shader=new Shader(program);
			this.mContext=c;
			loadShader();
			addInitUniforms();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected abstract void loadShader() throws Exception;
	protected void addInitUniforms()
	{
		shader.bind();
		shader.addUniform("transform");
		shader.addUniform("MVPMatrix");
	}
	
	@Override
	public String getType() {
		return TYPE;
	}
}
