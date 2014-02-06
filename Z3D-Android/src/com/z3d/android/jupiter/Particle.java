package com.z3d.android.jupiter;

import javax.vecmath.Vector3f;

public class Particle 
{
	private Vector3f position,velocity,acceleration;
	private Vector3f forceAccumulator;
	float damping,inverseMass;
    public	Particle()
	{
		inverseMass=0.0f;
		damping=1.0f;
	}
	public Particle(float mass)
	{
		inverseMass=(float)Math.pow(mass, -1.0);
		damping=1.0f;
	}
	public Particle(float mass,float damping)
	{
		inverseMass=(float)Math.pow(mass, -1.0);
		this.damping=damping;
	}
	public boolean hasFiniteMass()
	{
		return inverseMass>0.0f;
	}
}
