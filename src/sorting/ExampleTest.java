package sorting;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class ExampleTest {

	@Test
	public void testBubbleSort() 
	{
		int[] a = {5,7,1,3};
		Example.bubbleSort(a);
		int[] b = {1,3,5,7};
		assertTrue(Arrays.equals(a, b));
	}
	
	@Test
	public void testfindLargest()
	{
		int[] a = {5,7,1,3};
		int actual = Example.findLargest(a);
		int expected = 7;
		assertEquals(actual,expected);
	}
	
	@Test
	public void testMostFreq()
	{
		int[] a = {5,5,-1,-1,-1,3};
		int actual = Example.mostFreq(a);
		int expected = -1;
		assertEquals(actual, expected);
	}

}
