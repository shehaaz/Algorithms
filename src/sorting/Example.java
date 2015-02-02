package sorting;

import java.util.HashMap;
import java.util.Map;

public class Example {

	/**
	 * Bubble Sort: Sets the Nth value. Then the n-1. Then n-2, etc
	 * @return 
	 */
	static void bubbleSort(int[] intArray)
	{
		int lastIndex = intArray.length - 1;
		
		for(int j=lastIndex; j>1; j--)
		{
			for(int i=0; i<j; i++)
			{
				if(intArray[i] > intArray[i+1])
				{
					Example.swap(intArray, i, i+1);
				}
			}
		}
	}

	private static void swap(int[] intArray, int i, int j) 
	{
		int tmp = intArray[i];
		intArray[i] = intArray[j];
		intArray[j] = tmp;
	}
	
	//find the largest int in an array
	static int findLargest(int[] a)
	{
		int largest = a[0];
		
		for (int i=1; i<a.length; i++)
		{
			largest = (largest > a[i]) ? largest : a[i];
		}
		
		return largest;
	}
	
	//find the most frequent int in an array
	static int mostFreq(int[] a)
	{
		final Map<Integer, Integer> holder = new HashMap<Integer,Integer>();
		int key = Integer.MIN_VALUE;
		int count = 0;
		
		for(int i=0; i<a.length; i++)
		{
			if(!holder.containsKey(a[i]))
			{
				//add new key
				holder.put(a[i], 1);
			}
			else
			{
				//increment the count then add key
				holder.put(a[i], holder.get(a[i])+1);
			}
			
			if(count < holder.get(a[i]))
			{
				key = a[i];
				count = holder.get(a[i]);
			}
		}
		
		return key;
	}
	
	
	
}
