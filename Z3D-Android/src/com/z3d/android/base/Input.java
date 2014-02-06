package com.z3d.android.base;

import javax.vecmath.Point2f;

import android.util.Log;

public class Input 
{
	public enum GestureState{SINGLE_TAP,DOUBLE_TAP,MOVE,FLING,PRESS,NONE};
	private boolean listeningToInput;	//for error checking reasons, and to allow disabling.
	private GestureState state;
	private Point2f singleTapCoordinates;
	private Point2f doubleTapCoordinates;
	private Point2f pressCoordinates;
	private Point2f moveCoordinates;
	private Point2f flingVelocity;
	private static Input instance;

	private Input()
	{
		singleTapCoordinates=new Point2f();
		doubleTapCoordinates=new Point2f();
		moveCoordinates=new Point2f();
		flingVelocity=new Point2f();
		state=Input.GestureState.NONE;
	}
	
	//assorted getters and setters-
	public static Input getInstance()
	{
		if(instance==null)
			instance=new Input();
		return instance;
	}
	public GestureState getState()
	{
		return this.state;
	}
	public void setState(GestureState st)
	{
		this.state=st;
	}
	public Point2f getSingleTapCoordinates()
	{
		return singleTapCoordinates;
	}
	public Point2f getDoubleTapCoordinates()
	{
		return doubleTapCoordinates;
	}
	public Point2f getPressCoordinates()
	{
		return pressCoordinates;
	}
	public Point2f getMoveCoordinates()
	{
		return moveCoordinates;
	}
	public Point2f getFlingVelocity()
	{
		return flingVelocity;
	}
	public void setSingleTapCoordinates(float x,float y)
	{
		singleTapCoordinates.set(x, y);
	}
	public void setDoubleTapCoordinates(float x,float y)
	{
		doubleTapCoordinates.set(x, y);
	}
	public void setPressCoordinates(float x,float y)
	{
		pressCoordinates.set(x, y);
	}
	public void setMoveCoordinates(float x,float y)
	{
		moveCoordinates.set(x, y);
	}
	public void setFlingVelocity(float x,float y)
	{
		flingVelocity.set(x, y);
	}
	public void enableInput()
	{
		listeningToInput=true;
	}
	public void disableInput()
	{
		listeningToInput=false;
	}
	public boolean isListening()
	{
		return this.listeningToInput;
	}
	
}
