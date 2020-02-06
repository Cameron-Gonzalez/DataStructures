import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HashMapTest 
{
	HashMap<String, String> mm;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception 
	{
		mm = new HashMap<>();
	}

	@After
	public void tearDown() throws Exception 
	{
		mm = null;
	}

	private void addsToMap() 
	{
		mm.add("a", "1");
		mm.add("b", "2");
		mm.add("c", "3");
	}
	
	private void addsManyToMap()
	{
		mm.add("a", "1");
		mm.add("b", "2");
		mm.add("c", "3");
		
		mm.add("a", "one");
		
		for (int i = 0; i < 26; i++)
		{
			mm.add(Integer.toString(i), "x");
		}
	}
	
	@Test
	public void testSize0()
	{
		assertEquals(0, mm.size());
		assertTrue(mm.isEmpty());
	}
	
	@Test
	public void testAddSize()
	{
		addsToMap();
		assertEquals(3, mm.size());
		assertFalse(mm.isEmpty());
	}
	
	@Test
	public void testAddManySize()
	{
		addsManyToMap();
		assertEquals(29, mm.size());
		assertFalse(mm.isEmpty());
	}
	
	@Test
	public void testAddContainsKey()
	{
		assertFalse(mm.containsKey("a"));
		assertFalse(mm.containsKey("b"));
		assertFalse(mm.containsKey("c"));
		addsToMap();
		assertTrue(mm.containsKey("c"));
		assertTrue(mm.containsKey("b"));
		assertTrue(mm.containsKey("a"));
	}
	
	@Test
	public void testRemoveKey()
	{
		addsManyToMap();
		mm.remove("b");
		assertTrue(mm.getValue("b") == null);
	}
	
	@Test
	public void testRemoveAndSize()
	{
		addsManyToMap();
		
		String s1 = mm.remove("a");
		assertTrue("one".equals(s1));
		assertEquals(28, mm.size());
		
		String s2 = mm.remove("a");
		assertEquals(null, s2);
		assertEquals(28, mm.size());
	}
	
}
