package searching;

import java.util.LinkedHashMap;
import java.util.Map;

public class Example {
	
	/**
	 * Fetch all the indexes that have the target int
	 * Linear Search is best when there are repeated elements and you want to find how many occurances. 
	 * Worse and Best case is O(n)
	 * @param intArray
	 * @param target
	 * @return
	 */
	static String linearSearch(int[] intArray, int target)
	{
		String result = "";
		
		for(int i=0; i<intArray.length; i++)
		{
			if(intArray[i] == target)
			{
				result += i + "|";
			}
		}
		
		if(result.isEmpty())
		{
			result = "NONE";
		}

		return result;
	}
	
	/***
	 * Binary Search: Divide and conquer search for an element in a sorted array
	 * returns index of the element or -1 if it doesn't exist;
	 */
	static int binarySearch(int[] a, int value)
	{
		int lowIndex = 0;
		int highIndex = a.length - 1;
		int result = -1;
		
		while (lowIndex <= highIndex)
		{
			int middleIndex = (lowIndex + highIndex)/2;
			
			if(value == a[middleIndex])
			{
				result = middleIndex;
				break;
			}
			else if(value > a[middleIndex])
			{
				lowIndex = middleIndex + 1;
			}
			else if(value < a[middleIndex])
			{
				highIndex = middleIndex - 1;
			}
		}
		
		return result;
	}
	
	/**
	 * write a function that takes in an int[] and int target
	 * returns non-zero based index of two ints in the array that add up to int target
	 * {2,7,11,1} 18 -> [2,3]
	 */
	
	static int[] twoSum(int[] a, int target)
	{
		int[] result = new int[2];
		/*
		 * We only have to iterate once through the list. Use a map to remember the value and get to its index+1
		 * Key=integer value
		 * value=index+1
		 * 
		 */
		
		Map<Integer, Integer> memo = new LinkedHashMap<Integer,Integer>();
		
		for(int i=0; i<a.length; i++)
		{
			if(a[i] < target)
			{
				memo.put(a[i], i+1);
			}
		}
		
		for(int key : memo.keySet())
		{
			int first = key;
			int second = target - first;
			
			if(memo.get(second) != null)
			{
				result[0] = memo.get(first);
				result[1] = memo.get(second);
				break;
			}
		}
		return result;
	}
	
	

}
