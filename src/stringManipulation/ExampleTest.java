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
		assertTrue(Example.palindromeChecker(input));
	}
	
	@Test
	public void testAnagramCheckerFalse(){
		System.out.println("testAnagramCheckerFalse()");
		
		String input = "lav";
		assertFalse(Example.palindromeChecker(input));
		
	}
	
	@Test
	public void testAnagramCheckerEdgeCase(){
		System.out.println("testAnagramCheckerEdgeCase()");
				
		String input = "LAVaL";
		assertTrue(Example.palindromeChecker(input));
	}
	
	@Test
	public void testpChecker()
	{
		assertTrue(Example.getLongestPalindromeFromBeginning("zaazl").equals("zaaz"));
	}
	
	@Test
	public void testpCheckerNone()
	{
		assertTrue(Example.getLongestPalindromeFromBeginning("zl").equals(""));
	}
	
	@Test
	public void testLongestPalindrom()
	{
		assertTrue(Example.longestPalindromInsideString("jzAdax").equals("Ada"));
	}
	
	@Test
	public void testLongestPalindromLengthFour()
	{
		assertTrue(Example.longestPalindromInsideString("jzaazx").equals("zaaz"));
	}
	
	@Test
	public void testLongestPalindromRaceCar()
	{
		assertTrue(Example.longestPalindromInsideString("RaceCar").equals("RaceCar"));
	}
	
	@Test
	public void testLongestPalindromSmallerPalindromInside()
	{
		assertTrue(Example.longestPalindromInsideString("Laval").equals("Laval"));
	}
	
	@Test
	public void testLongestPalindromNoPalindromInside()
	{
		assertTrue(Example.longestPalindromInsideString("example").isEmpty());
		
	}
	
	@Test
	public void testUniqueString()
	{
		String s = "abcde";
		assertTrue(Example.uniqueString(s));
		
		s = "abbcde";
		assertFalse(Example.uniqueStringHashSet(s));
	}
	
	@Test
	public void testStack()
	{
		String[] tokens = new String[] {"4", "13", "5", "/", "+"};
		int actual = Example.stack(tokens);
		int expected = 6;
		
		assertEquals(actual, expected);
	}
	
	@Test
	public void testAnagram()
	{
		String s1 = "iceman";
		String s2 = "cinema";
		
		assertTrue(Example.isAnagram(s1, s2));
	}

}
