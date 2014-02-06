package com.z3d.android.base;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.vecmath.Vector4f;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.opengl.GLES20;
import android.opengl.GLU;
import android.os.Build;
import android.util.Log;

public class Mesh 
{
	private int[] vbo,ibo;
	private float[] floatArray;
	private int size;
	public static final int SIZE_OF_FLOAT=4;
	public static final int SIZE_OF_INT=4;
	
	public Mesh()
	{
		vbo=new int[1];
		ibo=new int[1];
		GLES20.glGenBuffers(1,vbo,0);
		GLES20.glGenBuffers(1,ibo,0);
		
	}
	private FloatBuffer getFloatBuffer(float[] input)
	{
		FloatBuffer mFloatBuffer=ByteBuffer.allocateDirect(input.length*SIZE_OF_FLOAT)
				.order(ByteOrder.nativeOrder())
				.asFloatBuffer();
		mFloatBuffer.put(input);
		mFloatBuffer.position(0);
		return mFloatBuffer;
	}
	private IntBuffer getIntBuffer(int[] input)
	{
		IntBuffer mIntBuffer=ByteBuffer.allocateDirect(input.length*SIZE_OF_INT)
				.order(ByteOrder.nativeOrder())
				.asIntBuffer();
		mIntBuffer.put(input).position(0);
		return mIntBuffer;
	}
	public void addVertices(Vertex[] vertices,int[] indices) throws Exception
	{
		size=indices.length;
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER,vbo[0]);
		if(!GLES20.glIsBuffer(vbo[0]))
			throw new Exception("vbo not bound");
		floatArray=Util.createFlippedBuffer(vertices);
		GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER,SIZE_OF_FLOAT*vertices.length*Vertex.SIZE,
				getFloatBuffer(floatArray),GLES20.GL_STATIC_DRAW);
		
		GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER,ibo[0]);
		if(!GLES20.glIsBuffer(ibo[0]))
			throw new Exception("ibo not bound");
		GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER,SIZE_OF_INT*indices.length,
				getIntBuffer(indices),GLES20.GL_STATIC_DRAW);
	}
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)		//temporary solution. will be solved 
	@SuppressLint("NewApi")							//permanently later.
	public void draw(int program,Shader shader) throws Exception
	{
		int mPositionHandle=GLES20.glGetAttribLocation(program, "position");
		GLES20.glEnableVertexAttribArray(mPositionHandle);
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER,vbo[0]);
		GLES20.glVertexAttribPointer(mPositionHandle, 3,GLES20.GL_FLOAT,false,SIZE_OF_FLOAT*Vertex.SIZE,0);
		GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER,ibo[0]);
		//buffer bind checking-
		if(!GLES20.glIsBuffer(vbo[0]))
			throw new Exception("vbo not bound");
		if(!GLES20.glIsBuffer(ibo[0]))
			throw new Exception("ibo not bound");
		GLES20.glDrawElements(GLES20.GL_TRIANGLES,this.size,GLES20.GL_UNSIGNED_INT,0);
		GLES20.glDisableVertexAttribArray(mPositionHandle);
	}
	
	public void release()
	{
		int[] buff={vbo[0],ibo[0]};
		if(GLES20.glIsBuffer(vbo[0]))
			GLES20.glDeleteBuffers(1,buff,0);
		
	}
}
