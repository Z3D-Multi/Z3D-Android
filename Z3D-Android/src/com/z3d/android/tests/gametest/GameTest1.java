package com.z3d.android.tests.gametest;

import javax.vecmath.Point2f;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.z3d.android.base.GameObject;
import com.z3d.android.base.Input;
import com.z3d.android.base.ResourceLoader;
import com.z3d.android.base.SimpleVaryingColorMaterial;
import com.z3d.android.base.Transform;

public class GameTest1 
{
	private GameObject player1;
	private static Point2f lastMoveCoordinates;
	private boolean startMove;
	/*
	 * Test for checking translation of an object.
	 */
	public GameTest1(int program,Context c)
	{
		try {
			Toast.makeText(c,"Testing object translation",Toast.LENGTH_SHORT).show();
			player1=new GameObject(program,ResourceLoader.loadMesh("pyramid1.obj",c),
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
				player1.getTransform().setTranslation((x-lastMoveCoordinates.getX())*0.01f,
						(y-lastMoveCoordinates.getY())*-0.01f, 0.0f);
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
