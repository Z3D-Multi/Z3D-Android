package com.z3d.android.base;

public class Time 
{
	public static final double SECOND=1e9;
	private static double delta;
	private static boolean happened=false;
	private static long start=0;
	public static long getTime()	
	{
		if(!happened)
		{
			happened=true;
			start=System.nanoTime();
		}
		return System.nanoTime()-start;
	}
	public static double getDelta()
	{
		return delta;
	}
	public static void setDelta(double deltaVal)
	{
		delta=deltaVal;
	}
}
