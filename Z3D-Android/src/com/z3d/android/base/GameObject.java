package com.z3d.android.base;

import com.z3d.android.jupiter.Particle;

public class GameObject 
{
	private int mProgram;
	private Mesh mesh;
	private Shader shader;
	private Transform transform;
	private Particle particle;
	public GameObject(int program)
	{
		this.mProgram=program;
	}
	public GameObject(int p,Mesh m,Shader s,Transform t)
	{
		this.mProgram=p;
		this.mesh=m;
		this.shader=s;
		this.transform=t;
	}
	public void draw()
	{
		try 
		{
			shader.bind();
			shader.setUniform("transform",transform.getTransform());
			shader.setUniform("MVPMatrix",Transform.getMVPMatrix());
			mesh.draw(mProgram, shader);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public void update(Input inp)
	{
		
	}
	public boolean hasMeshAttached()
	{
		return mesh!=null;
	}
	public void cleanup()
	{
		mesh.release();
	}
	//getters and setters-
	public Mesh getMesh()
	{
		return mesh;
	}
	public void setMesh(Mesh m)
	{
		this.mesh=m;
	}
	public Shader getShader()
	{
		return shader;
	}
	public void setShader(Shader s)
	{
		this.shader=s;
	}
	public Transform getTransform()
	{
		return transform;
	}
	public void setTransform(Transform t)
	{
		this.transform=t;
	}
	public Particle getParticle()
	{
		return particle;
	}
	public void setParticle(Particle p)
	{
		this.particle=p;
	}	
	
}
