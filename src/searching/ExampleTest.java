package searching;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class ExampleTest {

	@Test
	public void testlinearSearch() {
		
		int[] intArray = {1,7,8,7,5,7};
		
		int target = 7;
		
		String result = Example.linearSearch(intArray, target);
		
		assertTrue("expected: " + result, result.equals("1|3|5|"));
	}
	
	@Test
	public void testlinearSearchNone() {
		
		int[] intArray = {1,7,8,7,5,7};
		
		int target = 17;
		
		String result = Example.linearSearch(intArray, target);
		
		assertTrue("expected: " + result, result.equals("NONE"));
	}
	
	@Test
	public void testBinarySearch()
	{
		int[] intArray = {1,2,3,4,5,6,7,8,9,10};
		int target = 9;
		int targetIndex = Example.binarySearch(intArray, target);
		
		assertTrue(8 == targetIndex);
	}
	
	@Test
	public void testBinarySearchNone()
	{
		int[] intArray = {1,2,3,4,5,6,7,8,9,10};
		int target = 11;
		int targetIndex = Example.binarySearch(intArray, target);
		
		assertTrue(-1 == targetIndex);
	}
	
	@Test
	public void testTwoSum()
	{
		int[] a = {2,7,11,1};
		int target = 18;
		int [] result = Example.twoSum(a, target);
		int[] expected = {2,3};
		Arrays.equals(expected, result);
	}

}
