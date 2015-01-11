package searching;

import static org.junit.Assert.*;

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

}
