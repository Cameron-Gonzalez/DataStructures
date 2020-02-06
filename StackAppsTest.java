//Part of the Stack Project
//By: Cameron Gonzalez
//Helped by: Dr. Hong

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class StackAppsTest extends StackApps
{
	Stack<Double> myStack;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception 
	{
		
	}

	@Before
	public void setUp() throws Exception 
	{
		myStack = new Stack<Double>();
	}

	@After
	public void tearDown() throws Exception 
	{
		
	}

	@Test
	public void testInt2StrMethod1() 
	{
		int myInt = 56;
		String myIntString = int2Str(myInt);
		assertEquals("56", myIntString);
	}
	
	@Test
	public void testInt2StrMethod2()
	{
		int myInt = 0;
		String myIntString = int2Str(myInt);
		assertEquals("0", myIntString);
	}
	
	@Test
	public void testInt2StrMethod3()
	{
		int myInt = -56;
		String myIntString = int2Str(myInt);
		assertEquals("-56", myIntString);
	}
	
	@Test
	public void testEvalPostfixMethod1()
	{
		String myString = "3 5+";
		double result = evalPostfix(myString);
		assertTrue(result == 8.0);
	}
	
	@Test 
	public void testEvalPostfixMethod2()
	{
		String myString = "3 5 -";
		double result = evalPostfix(myString);
		assertTrue(result == -2.0);
	}

	@Test 
	public void testEvalPostfixMethod3()
	{
		String myString = "3 5 *";
		double result = evalPostfix(myString);
		assertTrue(result == 15.0);
	}
	
	@Test 
	public void testEvalPostfixMethod4()
	{
		String myString = "3 5/";
		double result = evalPostfix(myString);
		assertTrue(result == 0.6);
	}
	
	@Test 
	public void testEvalPostfixMethod5()
	{
		String myString = "5 3 5.0/*";
		double result = evalPostfix(myString);
		assertTrue(result == 3);
	}
	
	@Test 
	public void testEvalPostfixMethod6()
	{
		String myString = "8.0 2 3+*4.0/";
		double result = evalPostfix(myString);
		assertTrue(result == 10);
	}
	
	@Test 
	public void testEvalPostfixMethod7()
	{
		String myString = "8.0 2 3+*4.0";
		double result = evalPostfix(myString);
		assertEquals(Double.NaN, result, 0.0000001);
	}
	
	@Test
	public void testEvalPostfixMethod8()
	{
		String myString = "8.0 2 3+*4.0/+";
		double result = evalPostfix(myString);
		assertEquals(Double.NaN, result, 0.0000001);
	}
}
