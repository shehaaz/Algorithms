package searching;

import graphs.Tree;
import graphs.Tree.Node;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;


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
	
	/*
	 * Find pairs in an integer array whose sum is equal to 10 
	 */
	static List<Pair<Integer, Integer>> findPairs(int[] a, int target)
	{	
		List<Pair<Integer, Integer>> result = new ArrayList<Pair<Integer, Integer>>();
		
		//working memory
		Set<Integer> holder = new HashSet<Integer>();
		
		for(int i=0; i<a.length; i++)
		{
		  int diff = target - a[i];
		  
		  if(holder.contains(diff))
		  {
			  result.add(Pair.createPair(a[i], diff));
			  holder.remove(diff);
		  }
		  else
		  {
			  holder.add(a[i]);
		  }
		}
		
		return result;
	}
	
	public static class Pair<K, V> 
	{

	    private final K element0;
	    private final V element1;

	    public static <K, V> Pair<K, V> createPair(K element0, V element1) {
	        return new Pair<K, V>(element0, element1);
	    }
	    
	    private Pair(K element0, V element1) {
	        this.element0 = element0;
	        this.element1 = element1;
	    }

	    public K getElement0() {
	        return element0;
	    }

	    public V getElement1() {
	        return element1;
	    }
	    
	    @Override
	    public boolean equals(Object o)
	    {
    	   if ( !(o instanceof Example.Pair<?,?>) ) 
    	   {
               return false;
           }
    	   
           return Objects.equals(((Example.Pair<?,?>)o).getElement0(), this.getElement0())
                   && Objects.equals(((Example.Pair<?,?>)o).getElement1(), this.getElement1())
                   ||Objects.equals(((Example.Pair<?,?>)o).getElement0(), this.getElement1())
                   && Objects.equals(((Example.Pair<?,?>)o).getElement1(), this.getElement0());
	    }
	    
	    @Override
	    public int hashCode()
	    {
	    	return com.google.common.base.Objects.hashCode(this.element0, this.element1);
	    }

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
	
	static Iter toIterator(Object[] a)
	{
	  return new Iter(a);
	}


	public static class Iter
	{

	//Fifo
	Queue<Object> q = new LinkedBlockingQueue<Object>();
	 
	public Iter(Object[] a)
	{
	  for(int i=0; i<a.length; i++)
	   {
		 q.add(a[i]);
	   }
	}

	public Object next()
	{
	  if(q.size() == 0)
	   {
	     return null;
	   }
	  else
	  {
	   return q.poll();
	  }
	 }
	}
	
}
