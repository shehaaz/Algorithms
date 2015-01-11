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

}
