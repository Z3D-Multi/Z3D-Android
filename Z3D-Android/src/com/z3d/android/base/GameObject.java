package com.z3d.android.base;

import com.z3d.android.jupiter.Particle;

public class GameObject 
{
	private int mProgram;
	private Mesh mesh;
	private Transform transform;
	private Particle particle;
	private Material material;
	public GameObject(int program)
	{
		this.mProgram=program;
	}
	public GameObject(int p,Mesh mesh,Material material,Transform transform)
	{
		this.mProgram=p;
		this.mesh=mesh;
		this.material=material;
		this.transform=transform;
	}
	public void draw()
	{
		try 
		{
			material.setUniform("transform",transform.getTransform());
			material.setUniform("MVPMatrix",Transform.getMVPMatrix());
			mesh.draw(mProgram,material.getShader());
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
	public Material getMaterial()
	{
		return material;
	}
	public void setMaterial(Material material)
	{
		this.material=material;
	}
	
}
