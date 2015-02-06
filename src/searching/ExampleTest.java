package searching;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import searching.Example.Iter;
import searching.Example.Pair;

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
		
		//Slow
//		fib = Example.fibonacci(46);
//		assertTrue(fib==1836311903);
		
		fib = (int) Example.fibonacciDP(46);
		assertTrue(fib==1836311903);
		
	}
	
	@Test
	public void testFindPairs()
	{
		int[] a = {3,6,5,5,7,1,16,24,11,4,-6};
		int target = 10;
		
		List<Pair<Integer, Integer>> actual = Example.findPairs(a, target);
		
		List<Pair<Integer, Integer>> expected = new ArrayList<Pair<Integer,Integer>>();
		expected.add(Pair.createPair(5, 5));
		expected.add(Pair.createPair(7, 3));
		expected.add(Pair.createPair(6, 4));
		expected.add(Pair.createPair(-6, 16));
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testtoIterator()
	{
		String[] s = {"h","e","l","l","o"};
		Iter iter = Example.toIterator(s);
		System.out.println(iter.next());
		System.out.println(iter.next());
		System.out.println(iter.next());
		System.out.println(iter.next());
		System.out.println(iter.next());
		System.out.println(iter.next());
	}

}
