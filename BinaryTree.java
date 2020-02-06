//Part of the BinaryTree Project
//By: Cameron Gonzalez
//Helped by: Dr. Hong

import java.util.Scanner;

public class BinaryTree<E>
{
	//TODO: add the inner class Node<E> here
	protected static class Node<E>
	{
		//Data Fields of class Node<E>
		
		/**
		 * The information stored in this node.
		 */
		public E data;
		/**
		 * Reference to the left child.
		 */
		public Node<E> left;
		/**
		 * Reference to the right child.
		 */
		public Node<E> right;
		
		//Constructors
		/**
		 * Construct a node with given data and no children.
		 * 
		 * @param data The data to store in this node
		 */
		public Node(E data)
		{
			this.data = data;
			left = null;
			right = null;
		}
		
		//Methods
		/**
		 * Returns a string representation of the node.
		 * 
		 * @return A string representation of the data fields
		 */
		@Override
		public String toString()
		{
			return data.toString();
		}
	} // End of class Node<E>
	
	//Data Field of class BinaryTree<E>
	/**
	 * The root of the binary tree
	 */
	protected Node<E> root;

	public BinaryTree()
	{
		root = null;
	}
	
	/**
	 * Construct a BinaryTree with a specified root. Should only be used by subclasses.
	 * 
	 * @param root The Node that is the root of the tree.
	 */
	protected BinaryTree(Node<E> root)
	{
		this.root = root;
	}
	
	/**
	 * Constructs a new binary tree with data in its root, leftTree as its left
	 * subtree and rightTree as its right subtree.
	 * 
	 * @param data The data item to store in the root
	 * @param leftTree the left child
	 * @param rightTree the right child
	 */
	public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree)
	{
		root = new Node<>(data);
		if (leftTree != null)
		{
			root.left = leftTree.root;
		}
		else
		{
			root.left = null;
		}
		if (rightTree != null)
		{
			root.right = rightTree.root;
		}
		else
		{
			root.right = null;
		}
	}

	/**
	 * Return the left subtree.
	 * 
	 * @return The left subtree or null if either the root or the left subtree is null
	 */
	public BinaryTree<E> getLeftSubtree()
	{
		if (root != null && root.left != null)
		{
			return new BinaryTree<>(root.left);
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * Return the right sub-tree
	 * 
	 * @return the right sub-tree or null if either the root or the right subtree is null
	 */
	public BinaryTree<E> getRightSubtree()
	{
		if (root != null && root.right != null)
		{
			return new BinaryTree<>(root.left);
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * Return the data field of the root
	 * 
	 * @return the data field of the root or null if the root is null
	 */
	public E getData()
	{
		if(root != null)
		{
			return root.data;
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * Determine whether this tree is a leaf
	 * 
	 * @return true if the root has no children
	 */
	public boolean isLeaf()
	{
		return (root == null || (root.left == null && root.right == null));
	}
	
	@Override
	public String toString()
	{
		return preOrderTraverse(root, 1);
	}
	
	public String preOrderTraverse(Node<E> node, int depth)
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < depth; i++)
		{
			sb.append("  ");
		}
		if (node == null)
		{
			sb.append("null");
			sb.append("\n");
		}
		else
		{
			sb.append(node.data);
			sb.append("\n");
			sb.append(preOrderTraverse(node.left, depth + 1));
			sb.append(preOrderTraverse(node.right, depth + 1));
		}
		return sb.toString();
	}
	
	/**
	 * Method to read a binary tree
	 * 
	 * @pre The input consists of a pre-order traversal of the binary tree. The
	 * line "null" indicates a null tree.
	 * @param scan the Scanner attached to the input file
	 * @return The binary tree
	 */
	public static BinaryTree<String> readBinaryTree(Scanner scan)
	{
		//Read a line and trim leading and trailing spaces
		String data = scan.nextLine().trim();
		if (data.equals("null"))
		{
			return null;
		}
		else
		{
			BinaryTree<String> leftTree = readBinaryTree(scan);
			BinaryTree<String> rightTree = readBinaryTree(scan);
			return new BinaryTree<>(data, leftTree, rightTree);
		}
	}
	
	/**
	 * Method to return the pre-order traversal of the binary tree as a sequence
	 * of strings each separated by a space.
	 * 
	 * @return A pre-order traversal as a string
	 */
	public String preorderToString()
	{
		return preorderToString(root);
	}

	private String preorderToString(Node<E> node)
	{
		if (node == null)
		{
			return "";
		}
		String result = "";
		result = result.trim();
		result += " " + node.toString();
		result = result.trim();
		if (node.left != null)
		{
			result += " " + preorderToString(node.left);
		}
		if (node.right != null)
		{
			result += " " + preorderToString(node.right);
		}
		return result;
	}

	/**
	 * Method to return the post-order traversal of the binary tree as a
	 * sequence of strings each separated by a space
	 * 
	 * @return A post-order traversal as a string
	 */
	public String postorderToString()
	{
		return postorderToString(root);
	}
	
	private String postorderToString(Node<E> node)
	{
		//TODO: add your code
		if (node == null)
		{
			return "";
		}
		String result = "";
		if (node.left != null)
		{
			result = postorderToString(node.left);
		}
		if (node.right != null)
		{
			result += " " + postorderToString(node.right);
		}
		result = result.trim();
		result += " " + node.toString();
		result = result.trim();
		return result;
	}
	
	/**
	 * Method to return the in-order traversal of the binary tree as a
	 * sequence of strings each separated by a space.
	 * 
	 * @return An in-order traversal as a string
	 */
	public String inorderToString()
	{
		return inorderToString(root);
	}
	
	private String inorderToString(Node<E> node)
	{
		//TODO: add your code here
		if (node == null)
		{
			return "";
		}
		String result = "";
		if (node.left != null)
		{
			result = inorderToString(node.left);
		}
		result = result.trim();
		result += " " + node.toString();
		result = result.trim();
		if (node.right != null)
		{
			result += " " + inorderToString(node.right);
		}
		return result;
		
	}
	
}
