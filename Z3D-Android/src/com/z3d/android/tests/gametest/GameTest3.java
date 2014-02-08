package com.z3d.android.tests.gametest;

import javax.vecmath.Point2f;
import javax.vecmath.Vector3f;

import android.content.Context;

import com.z3d.android.base.GameObject;
import com.z3d.android.base.Input;
import com.z3d.android.base.ResourceLoader;
import com.z3d.android.base.SimpleVaryingColorMaterial;
import com.z3d.android.base.Transform;

/*
 * Test for checking rotation of a GameObject.
 */
public class GameTest3 
{
	private GameObject player1;
	private static Point2f lastMoveCoordinates;
	private boolean startMove;
	private Vector3f mRotation;
	public GameTest3(int program,Context c)
	{
		try {
			player1=new GameObject(program,ResourceLoader.loadMesh("pyramid.obj",c),
					new SimpleVaryingColorMaterial(program, c),new Transform());
			mRotation=new Vector3f(0.0f,1.0f,0.0f);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void render()
	{
		player1.draw();
	}
	public void cleanup()
	{
		player1.cleanup();
	}
	public void update(Input input)
	{
		Input.GestureState st=input.getState();
        if(lastMoveCoordinates==null)
                lastMoveCoordinates=new Point2f(0, 0);
		switch(st)
		{
			case MOVE:
			{
				Point2f move=input.getMoveCoordinates();
				float x=move.getX(),y=move.getY();
				if(!startMove)
				{
					lastMoveCoordinates.set(x,y);
					startMove=true;
				}
				float a=((x-lastMoveCoordinates.getX())*0.5f);
				if(a==0.0f)
					break;
				mRotation.set(0.0f,1.0f,0.0f);
				mRotation.scale(a);
				player1.getTransform().setRotation(mRotation);

				break;
			}
			case NONE:
			{
				startMove=false;
				break;
			}
		}
	}	
}
