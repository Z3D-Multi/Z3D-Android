package com.z3d.android.base;

import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

public class Vertex 
{
	private Vector3f pos;
	private Vector2f textureCoords;
	public static final int SIZE=5;
	
	//Member Fucntions
	public Vertex(){}
	public Vertex(Vector3f pos)
	{
		this.pos=pos;
		textureCoords=new Vector2f(0.0f,0.0f);
	}
	public Vertex(Vector3f pos,Vector2f tex)
	{
		this.pos=pos;
		this.textureCoords=tex;
	}
	
	//getters and setters
	public Vector3f getPos()
	{
		return this.pos;
	}
	public Vector2f getTextureCoords()
	{
		return this.textureCoords;
	}
	public void setPos(Vector3f pos)
	{
		this.pos=pos;
	}
	public void setTextureCoords(Vector2f tex)
	{
		this.textureCoords=tex;
	}
}
