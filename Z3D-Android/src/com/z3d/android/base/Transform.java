package com.z3d.android.base;

import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;

import android.opengl.Matrix;
import android.util.Log;


public class Transform 
{
	private Vector3f rotation,translation,scale;
	private static Camera camera;
	
	public static final int MATRIX4F_INIT_WITH_TRANSLATION=0;
	public static final int MATRIX4F_INIT_WITH_ROTATION=1;
	public static final int MATRIX4F_INIT_WITH_SCALE=2;
	public static final float RADIAN_CONVERSION_FACTOR=0.0f;
	
	private static float[] projectionMatrix=new float[16];
	private static float[] viewMatrix=new float[16];
	private static float[] MVPMatrix=new float[16];
	public Transform()
	{
		rotation=new Vector3f(0.0f,0.0f,0.0f);
		translation=new Vector3f(0.0f,0.0f,0.0f);
		scale=new Vector3f(1.0f,1.0f,1.0f);
	}
	
	//helper methods. to increase scope of Matrix4f to do our work.
	private void initMatrix(Matrix4f mat,boolean identity)
	{
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
				if(i==j)
					mat.setElement(i,j,identity?1.0f:0.0f);
				else mat.setElement(i, j, 0.0f);
	}
	private Matrix4f getMatrixFromAxis(Vector3f axis,int type)
	{
		Matrix4f result=new Matrix4f();
		switch(type)
		{
			case MATRIX4F_INIT_WITH_TRANSLATION:
			{
				initMatrix(result,true);
				result.m03=axis.getX();
				result.m13=axis.getY();
				result.m23=axis.getZ();
				break;
			}
			case MATRIX4F_INIT_WITH_ROTATION:
			{
				
				float theta=axis.length()*RADIAN_CONVERSION_FACTOR;
				if(axis.length()!=0.0f)
					axis.normalize();
				float sT=(float) Math.sin(theta);
				float cT=(float) Math.cos(theta);
				float cC=1-cT;
				float x=axis.getX(),y=axis.getY(),z=axis.getZ();
				result.setColumn(0,new float[]{(float) (cT + Math.pow(x, 2) * cC),x * y * cC - z * sT, x * z
                        					* cC + y * sT, 0.0f});
				result.setColumn(1,new float[]{y * x * cC + z * sT, (float) (cT + Math.pow(y, 2) * cC), z
                        * y * cC - x * sT, 0.0f });
				result.setColumn(2,new float[]{ z * x * cC - y * sT, y * z
									* cC + x * sT, (float) (cT + Math.pow(z, 2) * cC), 0.0f});
				result.setColumn(3,new float[]{0.0f, 0.0f, 0.0f, 1.0f});
				
				break;
			}
			case MATRIX4F_INIT_WITH_SCALE:
			{
				initMatrix(result,true);
				result.m00=axis.getX();
				result.m11=axis.getY();
				result.m22=axis.getZ();
				break;
			}
			default:
			{
				initMatrix(result,true);
				break;
			}
		}
		return result;
	}
	
	//Assorted getters and setters.
	public Vector3f getTranslation()
	{
		return this.translation;
	}
	public void setTranslation(Vector3f tr)
	{
		this.translation=tr;
	}
	public void setScale(Vector3f scaler)
	{
		this.scale=scaler;
	}
	public static void setProjection(float[] proj)
	{
		projectionMatrix=proj;
	}
	public Vector3f getScale()
	{
		return this.scale;
	}
	public static Camera getCamera()
	{
		return camera;
	}
	public static void setCamera(Camera cam)
	{
		camera=cam;
	}
	public Matrix4f getTransform()
	{
		Matrix4f tr=getMatrixFromAxis(this.translation,MATRIX4F_INIT_WITH_TRANSLATION);
		
		Matrix4f ro=getMatrixFromAxis(this.rotation,MATRIX4F_INIT_WITH_ROTATION);
		
		Matrix4f sc=getMatrixFromAxis(this.scale,MATRIX4F_INIT_WITH_SCALE);
		ro.mul(sc);
		tr.mul(ro);
		//Util.displayMatrix(tr);
		return tr;
	}
	public static float[] getMVPMatrix() throws Exception
	{
		if(camera==null)
			throw new Exception("Camera not defined. Cant get MVP.");
		Matrix.setLookAtM(viewMatrix, 0, camera.getPos().getX(),camera.getPos().getY(),
				camera.getPos().getZ(),camera.getForward().getX(),camera.getForward().getY()
				,camera.getForward().getZ(),camera.getUp().getX(),camera.getUp().getY(),
				camera.getUp().getZ());
		Matrix.multiplyMM(MVPMatrix, 0, projectionMatrix, 0, viewMatrix, 0);
		return MVPMatrix;
	}
}
