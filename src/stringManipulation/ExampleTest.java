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
		System.out.println("testReverseStringDivideAndConquer()");
		System.out.println(test + " : " + Example.reverseStringDivideAndConquer(test));
	}
	
	@Test
	public void testAnagramCheckerTrue(){
		System.out.println("testAnagramCheckerTrue()");
		
		String input = "laval";
		assertTrue(Example.anagramChecker(input));
	}
	
	@Test
	public void testAnagramCheckerFalse(){
		System.out.println("testAnagramCheckerFalse()");
		
		String input = "lav";
		assertFalse(Example.anagramChecker(input));
		
	}
	
	@Test
	public void testAnagramCheckerEdgeCase(){
		System.out.println("testAnagramCheckerEdgeCase()");
				
		String input = "LAVaL";
		assertTrue(Example.anagramChecker(input));
	}

}
