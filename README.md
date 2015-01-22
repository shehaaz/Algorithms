Algorithms
==========

Algorithms in Java.

1. Graphs
 	* Java Tree Example 

2. Searching
	* linearSearch
	* binarySearch
	* twoSum e.g: Input: {2,7,11,1} 18 -> Output: [2,3]
	* Fibonacci Recursively/Dynamic Programming

3. Sorting
	* bubbleSort	 	

4. String Manipulation
  * reverseString
  * Find the longest palindrome in a String (Recursively)



# Awesome Resources

## Problems and blog posts
* [The Five Essential Phone-Screen Questions](https://sites.google.com/site/steveyegge2/five-essential-phone-screen-questions)
* [collection of data structure and algorithm questions](http://www.dsalgo.com/2013/02/index.php.html?m=1)
* [TLDR Interviews](https://docs.google.com/document/d/116s-IXQFsf6FNaPsc2jFXs_1wTJzemh1QNA7KVSaoII/edit)
* [LeetCodeOJ Problems](https://oj.leetcode.com/problemset/algorithms/)
* [LeetCode Solutions](http://www.programcreek.com/category/java-2/algorithms/)
* [Blog post on failing Google Interviews](http://alexbowe.com/failing-at-google-interviews/)
* [Quora Question](https://www.quora.com/What-should-I-expect-in-a-Software-Engineer-interview-at-Google-and-how-should-I-prepare?srid=vhu&share=1)
* [Hacking G+ Interview](http://courses.csail.mit.edu/iap/interview/materials.php)
* [Goog hiring process](https://www.google.ca/about/careers/lifeatgoogle/hiringprocess/)
* [Top 15 data structures and Algo questions](http://javarevisited.blogspot.ca/2013/03/top-15-data-structures-algorithm-interview-questions-answers-java-programming.html)
* [Big-O Cheat Sheet](http://bigocheatsheet.com/)
* [Google Code Jam Questions](https://code.google.com/codejam/contests.html)
* [FitCoding](http://www.fitcoding.com/)
* [Algorithm Zone](http://algorithm.zone/)
* [Project Euler](https://projecteuler.net)

## Books
* [Algorithms Sedgewick](https://github.com/kasaquan/book/blob/master/Robert%20Sedgewick%20and%20Kevin%20Wayne%20-%20Algorithms,%204th%20edition.pdf)
* [Think Complexity](http://greenteapress.com/complexity/thinkcomplexity.pdf)
* [Data Structures and Algorithms in Java](http://rineshpk.weebly.com/uploads/1/8/2/0/1820991/data_structures_and_algorithms_in_javatqw_darksiderg.pdf)

## Controlling Access to Members of a Class
![](http://i.imgur.com/6CPMyGY.png)


#LinkedList
* Arrays disadvantage: in an un-ordered array searching is slow and in an ordered array insertion is slow. Deletion is slow for un-ordered and ordered arrays. Also, the size of the array can't be changed after it's created.
* Linked List solves this problem.
* Linked Lists replace arrays as the basis structure for stacks and queues. That is why for the LRU Cache we use a linked list. If fact, you can use a linked list in many cases in which you would use an array, unless you need **frequent random access** to individual items using an index.

##Trees
* Provide both quick insertion and deletion of a linked list, and also the quick searching of an ordered array.
* A tree is actually an instance of a more general category called a graph
* No Cycles: For a collection of nodes and edges to be defined as a tree, there must be one (and only one!) path from the root to any other node
* Binary tree: every node can have at most two children
* A Binary Search Tree: A node's **left** child has key less than its parent, and **right** child must have a key greater than or equal to its parent.
* un-balanced trees: One side of the parent has more nodes than the other. For example, when a data-entry person arranges a stack of personnel files into order of ascending employee number before entering the data. When this happens, tree efficiency can be seriously degraded. What to do about it? A red–black tree is a data structure which is a type of self-balancing binary search tree

