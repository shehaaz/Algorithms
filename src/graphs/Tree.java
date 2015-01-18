package graphs;

import java.util.ArrayList;

public class Tree {
	
	private Node root;
	
	public Tree()
	{
		root = null;
	}
	
	public void insert(int key, int value)
	{
		Node newNode = new Node(key, value);
		
		if(root == null)
		{
			root = newNode;
		}
		else
		{
			Node current = root;
			Node parent;
			while(true)
			{
				parent = current;
				if(key < current.getKey()) //go left?
				{
					current = current.getLeftChild();
					if(current == null)
					{
						parent.setLeftChild(newNode);
						return;
					}
				}
 				else //go right
				{
					current = current.getRightChild();
					if(current == null)
					{
						parent.setRightChild(newNode);
						return;
					}
				}
			}
		}
	}
	
	public void printTree()
	{
		printTree("root", root);
	}
	
	private void printTree(String location, Node parent)
	{
		if(parent != null)
		{
			System.out.println(location);
			parent.displayNode();
			printTree("left", parent.getLeftChild());
			printTree("right", parent.getRightChild());
		}
	}
	
	public Node getRoot()
	{
		return root;
	}
	
	public Node find(Node parent, int key)
	{
		if(parent != null)
		{
			while(parent.getKey() != key)
			{
				if(key < parent.getKey()) //go left
				{
					parent = parent.getLeftChild();
				}
				else //go right
				{
					parent = parent.getRightChild();
				}
				if(parent == null)
				{
					return null;
				}
			}
		}

		return parent;
	}
	
	public class Node
	{
		private int key;
		private int value;
		private Node leftChild;
		private Node rightChild;
		
		public Node(int key, int value)
		{
			this.key = key;
			this.value = value;
		}
		
		public int getKey() {
			return key;
		}

		public void setKey(int key) {
			this.key = key;
		}

		
		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		
		public Node getLeftChild() {
			return leftChild;
		}

		public void setLeftChild(Node leftChild) {
			this.leftChild = leftChild;
		}

		public Node getRightChild() {
			return rightChild;
		}

		public void setRightChild(Node rightChild) {
			this.rightChild = rightChild;
		}

		public void displayNode()
		{
			System.out.println("{"+key+","+value+"}");
		}
	}
}
