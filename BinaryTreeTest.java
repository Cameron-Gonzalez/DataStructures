//Part of the BinaryTree Project

import static org.junit.Assert.*;

import java.io.File;
import java.util.Scanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BinaryTreeTest 
{
	BinaryTree<String> bt;
	String preorderExpectedResult;
	String postorderExpectedResult;
	String inorderExpectedResult;
	String inorderExpectedResultWithParentheses;
	String toStringExpectedResult = 
			"*\n" +
			"  +\n" +
			"	 x\n" +
			"	   null\n" +
			"      null\n" +
			"    y\n" +
			"      null\n" +
			"      null\n" +
			"  /\n" +
			"    a\n" +
			"      null\n" +
			"      null\n" +
			"    b\n" +
			"      null\n" +
			"      null\n";
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception 
	{
		bt = BinaryTree.readBinaryTree(new Scanner(new File("infile.txt")));
		preorderExpectedResult = "* + x y / a b";
		postorderExpectedResult = "x y + a b / *";
		inorderExpectedResult = "x + y * a / b";
		inorderExpectedResultWithParentheses = "(((x) + (y)) * ((a) / (b)))";
	}

	@After
	public void tearDown() throws Exception 
	{
		bt = null;
	}

	@Test
	public void testPreOrderTraversal()
	{
		String actualResult = bt.preorderToString();
		//System.out.println(actualResult);
		//At the end of each test run, use JUnit's assertXXX for an automatic check
		assertEquals(preorderExpectedResult, actualResult);
	}
	
	@Test
	public void testPostOrderTraversal()
	{
		String actualResult = bt.postorderToString();
		//System.out.println(actualResult);
		//At the end of each test run, use Junit's assertXXX for an automatic check
		assertEquals(postorderExpectedResult, actualResult);
	}
	
	@Test
	public void testInOrderTraversal()
	{
		String actualResult = bt.inorderToString();
		//System.out.println(actualResult);
		//At the end of each test run, use Junit's assertXXX for an automatic check
		assertEquals(inorderExpectedResult, actualResult);
	}

}
