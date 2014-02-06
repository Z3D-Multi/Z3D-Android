package com.z3d.android.base;

import javax.vecmath.Vector3f;

public class Camera 
{
	private Vector3f pos,forward,up;
	private static final Vector3f worldUp=new Vector3f(0.0f,1.0f,0.0f);
	
	public Camera()
	{
		init(new Vector3f(0.0f, 0.0f, 0.0f),new Vector3f(0.0f, 0.0f, 1.0f),Camera.worldUp);
	}
	public Camera(Vector3f pos,Vector3f forward,Vector3f up)
	{
		this.init(pos, forward, up);
	}
	private void init(Vector3f pos,Vector3f forward,Vector3f up)
	{
		this.pos=pos;
		this.forward=forward;
		this.up=up;
		this.up.normalize();
		this.forward.normalize();
	}
	
	public Vector3f getPos()
	{
		return pos;
	}
	public void setPos(float x,float y,float z)
	{
		if(pos==null)
			pos=new Vector3f(x,y,z);
		else pos.set(x, y, z);
	}
	public Vector3f getForward()
	{
		return forward;
	}
	public void setForward(float x,float y,float z)
	{
		if(forward==null)
			forward=new Vector3f(x,y,z);
		else forward.set(x, y, z);
	}
	public Vector3f getUp()
	{
		return up;
	}
	public void setUp(float x,float y,float z)
	{
		if(up==null)
			up=new Vector3f(x,y,z);
		else up.set(x, y, z);
	}
}
