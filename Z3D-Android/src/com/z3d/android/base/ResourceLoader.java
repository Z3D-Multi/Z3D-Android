package com.z3d.android.base;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;

import javax.vecmath.Vector3f;

import android.content.Context;
import android.util.Log;

public class ResourceLoader 
{
	enum mode{None,V,F,T,N,P};
	enum expected{Word,Number,EOL};
	
	
	public static String loadShader(String filename,Context context)
	{
		return loadFile(filename, context);
	}
	private static String loadFile(String filename,Context context)
	{
		StringBuilder result=new StringBuilder();
		try
		{
		InputStream is=context.getAssets().open(filename);
		BufferedReader br=new BufferedReader(new InputStreamReader(is));
		String tempStr;
		while((tempStr=br.readLine())!=null)
		{
			result.append(tempStr);
			result.append("\n");
		}
		if(br!=null)
			br.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result.toString();
	}
	public static Mesh loadMesh(String filename,Context context)
	{
		try 
		{
			InputStream is=context.getAssets().open(filename);
			return loadMeshObjFromStream(new InputStreamReader(is));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	private static Mesh loadMeshObjFromStream(InputStreamReader isr)
	{
		StreamTokenizer st=new StreamTokenizer(isr);
		ArrayList<Vertex>vertices=new ArrayList<Vertex>();
		ArrayList<Integer>indices=new ArrayList<Integer>();
		Mesh result=new Mesh();
		int token,vindex=0;
		float[] tempVertex=new float[3];
		mode currentMode=mode.None;
		expected nextExpected=expected.Word;
		boolean ignoreAfterSlash=false;
		st.eolIsSignificant(true);
		st.ordinaryChar('/');
		st.commentChar('#');
		try
		{
				while((token=st.nextToken())!=StreamTokenizer.TT_EOF)
				{
					//the ignore after slash part is during development. Once textures et al are
					//implemented, remove them and parse the numbers between the slashes
					//instead of ignoring them.
					if(ignoreAfterSlash)
					{
						if(token!='/')		//incase many slashes come continuously
							ignoreAfterSlash=false;
						continue;
					}
					switch(token)
					{
						case StreamTokenizer.TT_WORD:
						{
							if(nextExpected!=expected.Word)
								continue;//ignore.
							String val=st.sval;
							if(val.equals("v"))
							{
								currentMode=mode.V;
								tempVertex=new float[3];
								vindex=0;
								nextExpected=expected.Number;
							}
							if(val.equals("f"))
							{
								currentMode=mode.F;
								nextExpected=expected.Number;
							}
							break;
						}
						case StreamTokenizer.TT_NUMBER:
						{
							if(nextExpected!=expected.Number)
								continue;
							if(currentMode==mode.V)
							{
								if(vindex>=3)
								{
									nextExpected=expected.EOL;
									continue;
								}
								tempVertex[vindex++]=(float)st.nval;
							}
							else if(currentMode==mode.F)
							{
								indices.add(Integer.valueOf((int)st.nval));
							}
							else if(currentMode==mode.None)
								continue;
							break;
						}
						case StreamTokenizer.TT_EOL:
						{
							if(currentMode==mode.V)
							{
								Vertex temp=new Vertex();
								temp.setPos(new Vector3f(tempVertex));
								vertices.add(temp);
							}
							//setting everything back to the usual.
							currentMode=mode.None;
							nextExpected=expected.Word;
							break;
						}
						case '/':
						{
							ignoreAfterSlash=true;
							break;
						}
					}
				}
				//time to loop over the elements to put them in an array and get fucked. :(
				//TODO:- In order to not get fucked, create an implementation of Arraylist
				//		which uses int.
				int[] ind=toIntArray(indices.toArray(new Integer[indices.size()]));
				result.addVertices(vertices.toArray(new Vertex[vertices.size()]),ind);
				if(isr!=null)
					isr.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	public static int[] toIntArray(Integer[] ints)
	{
		int[] res=new int[ints.length];
		for(int i=0;i<ints.length;i++)
		{
			res[i]=ints[i].intValue();
		}
		return res;
	}
}
