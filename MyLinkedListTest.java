//Part of the LinkedList Project
//Cameron Gonzalez
//Help from: Dr. Hong

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedListTest 
{
	MyLinkedList<String> myList;
	
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
		myList = new MyLinkedList<>();
	}

	@After
	public void tearDown() throws Exception 
	{
	}

	@Test
	public void testIsEmpty()
	{
		assertTrue(myList.isEmpty());
	}
	
	//tests add method
	@Test
	public void testAppend()
	{
		ListIterator<String> itr = myList.listIterator(true);
		
		itr.add("hello");
		
		assertFalse(myList.isEmpty());
	}
	
	//tests add and size methods
	@Test
	public void testSize()
	{
		ListIterator<String> itr = myList.listIterator(true);
		itr.add("hello");
		itr.add("world");
		itr.add("how's it hangin'");
		
		assertTrue(myList.size() == 3);
	}
	
	//tests add and get methods
	@Test
	public void testGet()
	{
		ListIterator<String> itr = myList.listIterator(true);
		itr.add("hello");
		itr.previous();
		itr.add("world");
		itr.add("how's it hangin'");
		
		itr = myList.listIterator(true);
		assertTrue(itr.hasNext());
		assertEquals("world", itr.next());
		assertEquals("how's it hangin'", itr.next());
		assertEquals("hello", itr.next());
	}
	
	//tests add and set methods
	@Test
	public void testSet()
	{
		ListIterator<String> itr = myList.listIterator(true);
		itr.add("hello");
		itr.hasPrevious();
		itr.add("world");;
		itr.add("how's it hangin'");
		
		itr = myList.listIterator(true);
		itr.next();
		itr.next();
		itr.set("I Am");
		itr.previous();
		String temp = itr.next();
		assertNotEquals("how's it hangin'", temp);
		assertEquals("I Am", temp);
	}
	
	//tests add and remove methods
	@Test
	public void testRemove()
	{
		ListIterator<String> itr = myList.listIterator(true);
		itr.add("hello");
		itr.previous();
		itr.add("world");
		itr.add("how's it hangin'");
		
		itr = myList.listIterator(true);
		itr.next();
		itr.next();
		itr.remove();
		
		itr = myList.listIterator(false);
		assertTrue(itr.hasPrevious());
		String temp = itr.previous();
		assertNotEquals("how's it hangin'", temp);
		assertEquals("hello", temp);
	}
	
	//tests forward iterator with exception expected
	@Test
	public void testForward()
	{
		ListIterator<String> itr = myList.listIterator(true);
		itr.add("hello");
		itr.previous();
		itr.add("world");
		itr.add("how's it hangin'");
		
		itr = myList.listIterator(true);
		assertTrue(itr.hasNext());
		assertEquals("world", itr.next());
		assertTrue(itr.hasNext());
		assertEquals("how's it hangin'", itr.next());
		assertTrue(itr.hasNext());
		assertEquals("hello", itr.next());
		assertFalse(itr.hasNext());
		
		try
		{
			itr.next();
		}
		catch (NoSuchElementException ex)
		{
			
		}
	}
	
	//tests backward iterator with exception expected
	@Test
	public void testBackward()
	{
		ListIterator<String> itr = myList.listIterator(true);
        itr.add("hello");
        itr.previous();
        itr.add("world");
        itr.add("how's it hangin'");
        itr = myList.listIterator(false);
        
        assertTrue(itr.hasPrevious());
        assertEquals("hello", itr.previous());
        assertTrue(itr.hasPrevious());
        assertEquals("how's it hangin'", itr.previous());
        assertTrue(itr.hasPrevious());
        assertEquals("world", itr.previous());
        assertFalse(itr.hasPrevious());
          
        try 
        {
        	itr.previous();
        }
        catch (NoSuchElementException ex) 
        {
          
        }
	}
	
	//tests the contains method
	@Test
	public void testContains()
	{
		ListIterator<String> itr = myList.listIterator(true);
		itr.add("hello");
		itr.add("world");
		itr.add("how's it hangin'");
		
		itr = myList.listIterator(true);
		itr.next();
		String temp = itr.next();
		assertTrue(myList.contains(temp));
	}
}
