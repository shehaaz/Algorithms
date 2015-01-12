package searching;

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
	 * Binary Search: Divide and conquer search for an element in an array
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

}
