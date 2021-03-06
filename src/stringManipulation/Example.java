package stringManipulation;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;


public class Example {

	static String reverseString(String s) {
		
		String result = "";
		
		for(int i = s.length()-1; i>=0; i--){
			result = result + s.charAt(i);
		}
		
		return result;
	}
	
	static String reverseStringDivideAndConquer(String s){
		
		char[] result = new char[s.length()];
		int lastIndex = s.length() - 1;
		
		for(int i=0; i<= s.length()/2; i++){
			result[i] = s.charAt(lastIndex - i);
			result[lastIndex - i] = s.charAt(i);
		}
		
		return new String(result);
	}
	
	
	//Q1: Find the longest palindrom in a String
	/**
	 * Find the longest palindrome in a String. The palindrome can be contained inside the String
	 * Return Empty String if none
	 * @param s
	 * @return
	 */
	static String longestPalindromInsideString(String s)
	{
		if(s.length() < 3)
		{
			return "";
		}
		
		String palindrome = getLongestPalindromeFromBeginning(s);
		
		if (palindrome.isEmpty())
		{
			return longestPalindromInsideString(s.substring(1));
		}
		else
		{
			return palindrome;
		}
	}
		
	/**
	 * Given a String s. This function returns longest palindrome starting from index 0
	 * Returns Empty String if there is no palindrome
	 * @param String s
	 * @return String
	 */
	static String getLongestPalindromeFromBeginning(String s)
	{
		if(s.length() < 3)
		{
			return "";
		}
		if(palindromeChecker(s))
		{
			return s;
		}
		return getLongestPalindromeFromBeginning(s.substring(0, s.length()-1));
	}
	
	/**
	 * 
	 * @param String s
	 * @return boolean
	 * 
	 * LAVVAL
	 * 
	 * lastIndex = 4
	 * 
	 * s.length()/2 = 3
	 * 
	 * 0: L != L -> false 
	 * 1: A != A -> false
	 * 2: V != V -> false
	 * 
	 * result: true
	 */
	static boolean palindromeChecker(String s)
	{
		//convert the string to lowerCase
		s = s.toLowerCase();
		
		int lastIndex = s.length() - 1;
		
		for(int i=0; i<s.length()/2; i++)
		{
			//System.out.println("comparing: " + s.charAt(i) + " AND " + s.charAt(lastIndex - i));
			if(s.charAt(i) != s.charAt(lastIndex - i))
			{
				return false;
			}
		}
		return true;
	}
	
	
	// Q2: Given String s, find the minimum cuts that partition s into subStrings which are all palindromes
	
	
	/* Q3
	 * The goal of the challenge is to count how many times a phrase occurs in a body of text. 
	 * The catch is, there can be any number of characters in between the characters in the phrase. 
	 * For instance, the phrase "dog" appears twice in the text "did you go?" 
	 * It would match twice because of "D i d y O u G o ?" and "d i D y O u G o ?" 
	 * In other words, how many ways can you delete the characters in a body of text in such a way that you'll be left with 
	 * the desired phrase.
	 */
	
	//Q4: Longest Substring with At Most Two Distinct Characters
	static String longestSubtringTwoDistinctChars(String s)
	{
		
		
		return "";
	}
	
	/*
	 * Implement an algorithm to determine if a string has all unique characters. 
	 * What if you cannot use additional data structures?
	 */
	static boolean uniqueString(String s)
	{
		for(int i=0; i<s.length(); i++)
		{
			for(int j=i+1; j<s.length(); j++)
			{
				if(s.charAt(j) == s.charAt(i))
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	static boolean uniqueStringHashSet(String s)
	{
		Set<Character> set = new HashSet<Character>();
		
		for(int i=0; i<s.length(); i++)
		{
			set.add(s.charAt(i));
		}
		
		return (set.size() != s.length()) ? false : true;
	}
	
	static int stack(String[] tokens)
	{
		int returnValue = 0;
		String operators = "+-*/";
 
		Stack<String> stack = new Stack<String>();
 
		for (String t : tokens) 
		{
			if (!operators.contains(t)) 
			{
				stack.push(t);
			} 
			else 
			{
				int a = Integer.valueOf(stack.pop());
				int b = Integer.valueOf(stack.pop());
				switch (t) 
				{
				case "+":
					stack.push(String.valueOf(a + b));
					break;
				case "-":
					stack.push(String.valueOf(b - a));
					break;
				case "*":
					stack.push(String.valueOf(a * b));
					break;
				case "/":
					stack.push(String.valueOf(b / a));
					break;
				}
			}
		}
 
		returnValue = Integer.valueOf(stack.pop());
 
		return returnValue;
	}
	
	static boolean isAnagram(String s1, String s2)
	{
		//convert string to lower case
		s1 = s1.toLowerCase();
		s2 = s2.toLowerCase();
		
		if(s1.length() != s2.length())
		{
			return false;
		}
		else
		{
			StringBuilder sb1 = new StringBuilder(s1);
			StringBuilder sb2 = new StringBuilder(s2);
			
			return isAnagram(sb1, sb2);
		}
	}
	
	private static boolean isAnagram(StringBuilder sb1, StringBuilder sb2)
	{
		if(sb1.length() == 0 && sb2.length() == 0)
		{
			return true;
		}
		
		char checker = sb1.charAt(0);
		int index = sb2.indexOf(Character.toString(checker));

		//returns -1 if the CharacterSequence is not in StringBuilder
		if(index == -1)
		{
			return false;
		}
		
		sb1 = sb1.deleteCharAt(0);
		sb2 = sb2.deleteCharAt(index);
		return isAnagram(sb1, sb2);
	}

}
