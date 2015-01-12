package sorting;

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
	
}
