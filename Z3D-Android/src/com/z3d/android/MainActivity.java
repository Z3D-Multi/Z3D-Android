package com.z3d.android;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;

import com.z3d.android.base.Input;

public class MainActivity extends Activity 
{
	private Input input;
    private GestureDetectorCompat mDetector;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		input=Input.getInstance();
		MyGLSurfaceView mainView=new MyGLSurfaceView(this);
		setContentView(mainView);
		mDetector=new GestureDetectorCompat(this.getBaseContext(), new TouchListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public void onPause()
	{
		super.onPause();
		if(input.isListening())
			input.disableInput();
	}
	@Override
	public void onResume()
	{
		super.onResume();
		if(!input.isListening())
			input.enableInput();
	}
	@Override
	public boolean onTouchEvent(MotionEvent e)
	{
		if(input.isListening())
		{
			if(e.getActionMasked()==MotionEvent.ACTION_UP)
			{
				Input.GestureState st=input.getState();
				if(st==Input.GestureState.MOVE||st==Input.GestureState.PRESS)
				{
					input.setState(Input.GestureState.NONE);
				}
			}
			this.mDetector.onTouchEvent(e);
			return super.onTouchEvent(e);
		}
		else return false;
	}

	//Read GestureHelper.txt in the assets folder for help in implementing the Input class.
	//TODO: Make and implement logic for multitouch.
	private class TouchListener extends GestureDetector.SimpleOnGestureListener
	{
		private static final String DEBUG_TAG="Gestures";
		@Override
		public boolean onDown(MotionEvent event)
		{
			//Log.d(DEBUG_TAG,"onDown: ");
			input.setState(Input.GestureState.SINGLE_TAP);
			input.setSingleTapCoordinates(event.getX(),event.getY());
			return true;
		}
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
		{
			//Log.d(DEBUG_TAG,"onScroll: ");// + e2.toString());
			input.setState(Input.GestureState.MOVE);
			input.setMoveCoordinates(e2.getX(),e2.getY());
			return true;
		}
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
		{
			//Log.d(DEBUG_TAG,"onFling: ");// + e2.toString());
			//input.setState(Input.GestureState.FLING);
			//input.setFlingVelocity(velocityX, velocityY);
			return true;
		}
		@Override
		public boolean onSingleTapConfirmed(MotionEvent e)
		{
			//Log.d(DEBUG_TAG,"onSingleTapConfirmed: ");// + e.toString()); 
			input.setState(Input.GestureState.NONE);
			return true;
		}
		@Override
		public boolean onSingleTapUp(MotionEvent e)
		{
			//Log.d(DEBUG_TAG,"onSingleTapUp: ");// + e.toString()); 
			input.setSingleTapCoordinates(e.getX(), e.getY());
			return true;
		}
		@Override
		public void onShowPress(MotionEvent e)
		{
			input.setState(Input.GestureState.PRESS);
			input.setDoubleTapCoordinates(e.getX(),e.getY());
			//Log.d(DEBUG_TAG,"onShowPress: ");
		}
		@Override
		public boolean onDoubleTap(MotionEvent e)
		{
			//Log.d(DEBUG_TAG,"onDoubleTap");
			input.setState(Input.GestureState.DOUBLE_TAP);
			input.setDoubleTapCoordinates(e.getX(),e.getY());
			return true;
		}
		@Override
		public boolean onDoubleTapEvent(MotionEvent e)
		{
			//Log.d(DEBUG_TAG,"onDoubleTapEvent");
			input.setState(Input.GestureState.NONE);
			return true;
		}
	}
}
