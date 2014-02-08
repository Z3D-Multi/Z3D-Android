package com.z3d.android.base;

import javax.vecmath.Vector3f;

import android.content.Context;
import android.opengl.GLES20;

import com.z3d.android.tests.gametest.GameTest2;
import com.z3d.android.tests.gametest.GameTest3;

public class Game 
{
	private int mProgram;
	private Input input;
	private GameTest3 test;
	
	/*
	 * In this class many game tests can be run. Simply choose the class of test to run
	 * and instantiate it. Just instantiate a different class to run its test.
	 */
	public Game(Context c)// test.
	{
		mProgram=GLES20.glCreateProgram();
		input=Input.getInstance();
		Transform.setCamera(new Camera(new Vector3f(0.0f, 0.0f, 3.0f), new Vector3f(0.0f, 0.0f, -1.0f),
                new Vector3f(0.0f, 1.0f, 0.f)));
		test=new GameTest3(mProgram, c);
		
	}
	public void input()	//read the inputs arrived from the Input class. 
	{}					//issue: is it even needed? diff btwn this and update?
	public void render(int orientation)
	{
		test.render();
	}
	public void update()
	{
		test.update(input);
	}		//perform update effects based on received inputs.
	public void cleanup()
	{
		test.cleanup();
	}
}
