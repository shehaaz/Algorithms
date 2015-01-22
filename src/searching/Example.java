package searching;

import java.util.HashMap;
import java.util.Map;

import graphs.Tree;
import graphs.Tree.Node;


public class Example {
	
	/**
	 * Fetch all the indexes that have the target int
	 * Linear Search is best when there are repeated elements and you want to find how many occurances. 
	 * Worse and Best case is O(n)
	 * @param intArray
	 * @param target
	 * @return
	 */
	static String linearSearch(int[] intArray, int target)
	{
		String result = "";
		
		for(int i=0; i<intArray.length; i++)
		{
			if(intArray[i] == target)
			{
				result += i + "|";
			}
		}
		
		if(result.isEmpty())
		{
			result = "NONE";
		}

		return result;
	}
	
	/***
	 * Binary Search: Divide and conquer search for an element in a sorted array
	 * returns index of the element or -1 if it doesn't exist;
	 */
	static int binarySearch(int[] a, int value)
	{
		int lowIndex = 0;
		int highIndex = a.length - 1;
		int result = -1;
		
		while (lowIndex <= highIndex)
		{
			int middleIndex = (lowIndex + highIndex)/2;
			
			if(value == a[middleIndex])
			{
				result = middleIndex;
				break;
			}
			else if(value > a[middleIndex])
			{
				lowIndex = middleIndex + 1;
			}
			else if(value < a[middleIndex])
			{
				highIndex = middleIndex - 1;
			}
		}
		
		return result;
	}
	
	/**
	 * write a function that takes in an int[] and int target
	 * returns non-zero based index of two ints in the array that add up to int target
	 * {2,7,11,1} 18 -> [2,3]
	 */
	
	static int[] twoSum(int[] a, int target)
	{
		int[] result = new int[2];
		Tree tree = new Tree();

		for(int i=0; i<a.length; i++)
		{
			if(a[i] <= target)
			{
				tree.insert(a[i], i+1);
			}
		}
		
		Node current = tree.getRoot();
		Node node1;
		Node node2;
		
		while(current != null)
		{
			node1 = current;
			int diff = target-node1.getKey();
			
			if(diff < current.getKey())
			{
				current = current.getLeftChild();
				node2 = tree.find(current, diff);
			}
			else
			{
				current = current.getRightChild();
				node2 = tree.find(current, diff);
			}
			
			if(node2 != null)
			{
				result[0] = node1.getValue();
				result[1] = node2.getValue();
				break;
			}
		}
		
		return result;
	}
	
	/**
	 * Football scores question
	 * 30 -> 7,7,7,7,2
	 */
	
	/**
	 * Algorithm to verify whether a number is prime or not  
	 */
	
	/**
	 * Fibonacci recursive algorithm
	 * 0,1,1,2,3,5,8,...
	 * fib(-1) = 0
	 * fib(0) = 0
	 * fib(1) = 1
	 */
	static int fibonacci(int n)
	{
		//base case
		if(n==0 || n<0)
		{
			return 0;
		}
		if(n==1)
		{
			return 1;
		}
		//step case: two previous numbers give you n
		return fibonacci(n-2) + fibonacci(n-1);
	}
	
	//Fibonacci using dynamic programming
	static long fibonacciDP(int n) {
	    long n0 = 0;
	    long n1 = 1;
	    long current = 0;
	    for (int i = 2; i <= n; i++) {
	        current = n0 + n1;
	        n0 = n1;
	        n1 = current;
	    }
	    return current;
	}
	
}
