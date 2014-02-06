package com.z3d.android.base;

import javax.vecmath.Matrix4f;

import android.util.Log;

public class Util 
{
	public static float[] createFlippedBuffer(Vertex[] vertices)
	{
		float[] result=new float[vertices.length*Vertex.SIZE];
		for(int i=0,resultIndex=0;i<vertices.length;i++)
		{
			result[resultIndex++]=vertices[i].getPos().getX();
			result[resultIndex++]=vertices[i].getPos().getY();
			result[resultIndex++]=vertices[i].getPos().getZ();
			if(vertices[i].getTextureCoords()==null)
			{
				result[resultIndex++]=0.0f;
				result[resultIndex++]=0.0f;
			}
			else
			{
				result[resultIndex++]=vertices[i].getTextureCoords().getX();
				result[resultIndex++]=vertices[i].getTextureCoords().getY();
			}
		}
		return result;
	}
	public static int[] createFlippedBuffer(int[] indices)
	{
		int[] result=new int[indices.length];
		for(int i=0,resultIndex=0;i<indices.length;i++)
		{
			result[resultIndex++]=indices[i];
		}
		return result;
	}
	public static float[] createFlippedBuffer(Matrix4f matrix)
	{
		float[] result=new float[16];
		int resultIndex=0;
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
				result[resultIndex++]=matrix.getElement(j,i);
		return result;
	}
	public static void displayMatrix(Matrix4f mat)
	{
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
				Log.d("mattest",""+mat.getElement(i, j));
	}
}
