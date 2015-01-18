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
		int[] a = {16,7,1,11};
		int target = 18;
		int [] result = Example.twoSum(a, target);
		int[] expected = {2,4};
		assertTrue(Arrays.equals(expected, result));
	}
	
	@Test
	public void testTwoSumLongList()
	{
		int[] a = {16,7,1,11,3};
		int target = 19;
		int [] result = Example.twoSum(a, target);
		int[] expected = {1,5};
		assertTrue(Arrays.equals(expected, result));
	}
	
	@Test
	public void testTwoSumSameValues1()
	{
		int[] a = {0,4,3,0};
		int target = 0;
		int [] result = Example.twoSum(a, target);
		int[] expected = {1,4};
		assertTrue(Arrays.equals(expected, result));
	}
	
	@Test
	public void testTwoSumSameValues2()
	{
		int[] a = {3,4,3,0};
		int target = 6;
		int [] result = Example.twoSum(a, target);
		int[] expected = {1,3};
		assertTrue(Arrays.equals(expected, result));
	}
	
	@Test
	public void testTwoSumSameNone()
	{
		int[] a = {3,4,3,0};
		int target = 8;
		int [] result = Example.twoSum(a, target);
		int[] expected = {0,0};
		assertTrue(Arrays.equals(expected, result));
	}
	
	@Test
	public void testfibonacci()
	{
		int fib = Example.fibonacci(7);
		assertTrue(fib==13);
		
		fib = Example.fibonacci(-7);
		assertTrue(fib==0);
		
		fib = Example.fibonacci(36);
		assertTrue(fib==14930352);
	}

}
