package com.z3d.android.tests.gametest;

import javax.vecmath.Point2f;

import android.content.Context;
import android.widget.Toast;

import com.z3d.android.base.GameObject;
import com.z3d.android.base.Input;
import com.z3d.android.base.ResourceLoader;
import com.z3d.android.base.SimpleVaryingColorMaterial;
import com.z3d.android.base.Transform;

public class GameTest2 
{
	private GameObject player1;
	private static Point2f lastMoveCoordinates;
	private boolean startMove;
	/*
	 * Test for checking rotation of a GameObject.
	 */
	public GameTest2(int program,Context c)
	{
		try {
			player1=new GameObject(program,ResourceLoader.loadMesh("pyramid.obj",c),
					new SimpleVaryingColorMaterial(program, c),new Transform());
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
				float a=1.0f-((y-lastMoveCoordinates.getY())*0.01f);
				player1.getTransform().setScale(a,a,a);
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
