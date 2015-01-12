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

}
