package com.z3d.android.base;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLU;
import android.util.Log;

public class MainComponent 
{
	//constants
	private static final int PORTRAIT=1;
	private static final int LANDSCAPE=2;
	
	private Game game;
	private boolean isRunning;
	private double frameCap;
	private int orientation;
	
	//static variables-
	private static MainComponent current;
	private static long lastTime;
	private static double unprocessedTime;
	static int frames;
	static long frameCounter;
	
	//Member Functions
	public MainComponent(Context con)
	{
		current=this;
		isRunning=false;
		frameCap=30.0f;
		onWindowInit(con);
	}
	public void onWindowInit(Context c)
	{
		game=new Game(c);
		RenderUtil.initGraphics();
	}
	public void start()
	{
		if(this.isRunning)
			return;
		isRunning=true;
		lastTime=Time.getTime();
		unprocessedTime=0.0;
        frames = 0;
        frameCounter = 0;
	}
	public void stop()
	{
		isRunning=false;
	}
	public void run()
	{
		//TODO: some error checking. If framecap gets set to 0 somehow, detect it.
        final double frameTime = 1.0 / this.frameCap;
        if (this.isRunning) 
        {

                
                boolean render = false;

                long startTime = Time.getTime();
                long passedTime = startTime - lastTime;
                lastTime = startTime;

                unprocessedTime += passedTime / Time.SECOND;
                frameCounter += passedTime;
                while(unprocessedTime>frameTime) 
                {
                        render = true;

                        unprocessedTime -= frameTime;


                        Time.setDelta(frameTime);
                        game.update();

                        if (frameCounter >= Time.SECOND)
                        {
                                Log.d("MainComponent","Frame Rate: "+frames);
                                frames = 0;
                                frameCounter = 0;
                        }

                }
                if (render) 
                {
                        this.render();
                        frames++;
                }
        }
	}
	public void render()
	{
		RenderUtil.clearScreen();
		game.render(orientation);
	}
	public void cleanup()
	{
		game.cleanup();
	}

	//assorted getters and setters
	public void setIsRunning(boolean run)
	{
		this.isRunning=run;
	}
	public void setFrameCap(double fc)
	{
		this.frameCap=fc;
	}
	public double getFrameCap()
	{
		return this.frameCap;
	}
	public int getOrientation()
	{
		return orientation;
	}
	//re-think exception thrown (for better designed error handling throughout).
	public void setOrientation(int or) throws Exception
	{
		if(or==PORTRAIT||or==LANDSCAPE)
			this.orientation=or;
		else throw new Exception("Invalid input on setOrientation");
	}
	
}
