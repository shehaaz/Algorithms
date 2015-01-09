package stringManipulation;

import static org.junit.Assert.*;

import org.junit.Test;

public class ExampleTest {

	@Test
	public void testReverseString() {
		String input = "example";
		String output = Example.reverseString(input);
		String result = "elpmaxe";
		assertTrue(result.equals(output));
	}
	
	@Test
	public void testReverseStringDivideAndConquer() {
		String input = "example";
		String output = Example.reverseStringDivideAndConquer(input);
		String result = "elpmaxe";
		assertTrue("result: " + result + " output: " + output, result.equals(output));
		
		String test = "Madam, I'm Adam";
		System.out.println(test + " : " + Example.reverseStringDivideAndConquer(test));
	}

}
