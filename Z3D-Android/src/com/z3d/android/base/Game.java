package com.z3d.android.base;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLU;
import android.util.Log;

public class Game 
{
	private GameObject player1;
	private int mProgram;
	private Mesh mesh;
	private Shader shader;
	private Transform transform;
	private Input input;
	private static Point2f lastMoveCoordinates;
	private boolean startMove;
	private Camera camera;
	 // Texture Texture;
	
	public Game(Context c)// test.
	{
		mProgram=GLES20.glCreateProgram();
		input=Input.getInstance();
		mesh=ResourceLoader.loadMesh("pyramid.obj", c);
		transform=new Transform();
		camera=new Camera(new Vector3f(0.0f, 0.0f, 3.0f), new Vector3f(0.0f, 0.0f, -1.0f),
                new Vector3f(0.0f, 1.0f, 0.f));
		Transform.setCamera(camera);
		player1=new GameObject(mProgram);
		try {
			shader=new Shader(mProgram);
			shader.addVertexShader(ResourceLoader.loadShader("vertex2.vxs", c));
			shader.addFragmentShader(ResourceLoader.loadShader("fragment.fxs", c));
			shader.compileShader();
			shader.bind();
			shader.addUniform("transform");
			shader.addUniform("MVPMatrix");
			player1.setMesh(mesh);
			player1.setShader(shader);
			player1.setTransform(transform);
			Log.d("game","Init Done.");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void input()	//read the inputs arrived from the Input class. 
	{}					//issue: is it even needed? diff btwn this and update?
	public void render(int orientation)
	{
		try 
		{
			player1.draw();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void update()
	{
		Input.GestureState st=input.getState();
        if(lastMoveCoordinates==null)
                lastMoveCoordinates=new Point2f(0, 0);
		switch(st)
		{
			case SINGLE_TAP:
			{
				/*Point2f tap=input.getSingleTapCoordinates();
				transform.setTranslation(new Vector3f(tap.getX()*0.001f,tap.getY()*0.001f,0.0f));
				Log.d("setting at",""+tap.getX()+" "+tap.getY());
				*/break;
			}
			case MOVE:
			{
				Point2f move=input.getMoveCoordinates();
				float x=move.getX(),y=move.getY();
				if(!startMove)
				{
					lastMoveCoordinates.set(x,y);
					startMove=true;
				}
				player1.getTransform().setTranslation(new Vector3f((x-lastMoveCoordinates.getX())*0.01f,
						(y-lastMoveCoordinates.getY())*-0.01f, 0.0f));
				break;
			}
			case NONE:
			{
				startMove=false;
				break;
			}
		}
	}		//perform update effects based on received inputs.
	public void cleanup()
	{
		player1.cleanup();
	}
}
