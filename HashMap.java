//TODO: necessary imports

/**
 * A map that supports the key-value pir type of map.
 * The implementation is based on hash table.
 * 
 * @author Cameron Gonzalez
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public class HashMap <K, V> 
{
	/**
	 * An Entry maintaining a key and a value.
	 * 
	 * @author someone
	 * 
	 * @param <K> the type of keys for an entry
	 * @param <V> the type of mapped values
	 */
	public static class MapEntry<K, V>
	{
		private K    key;
		private V    val;
		
		/**
		 * Creates an entry representing a mapping from the specified
		 * key to the specified value.
		 * 
		 * @param k    the key represented by this entry
		 * @param v    the value represented by this entry
		 */
		public MapEntry(K k, V v)
		{
			key = k;
			val = v;
		}
		
		/**
		 * Returns the key corresponding to this entry.
		 * 
		 * @return    the key corresponding to this entry.
		 */
		public K getKey()
		{
			return key;
		}
		
		/**
		 * Returns the value corresponding to this entry.
		 * 
		 * @return    the value corresponding to this entry.
		 */
		public V getValue()
		{
			return val;
		}
		
		/**
		 * Replaces the value corresponding to this entry with the specified value.
		 * 
		 * @param v	   new value to be stored in this entry
		 * @return     the old value corresponding to the entry
		 */
		public V setValue(V v)
		{
			V oldVal = val;
			val = v;
			return oldVal;
		}
	}
	
	private MapEntry<K, V>[]    table; //each object will be of MapEntry<K, V>
	private int                 count;
	private int                 maxCount;
	private MapEntry<K, V>      DUMMY; //used as a special tag for deleted entry
	
	/**
	 * Constructs an empty HashMap with the default initial capacity (7) and
	 * the default load factor (0.67).
	 */
	@SuppressWarnings("unchecked")
	public HashMap()
	{
		table = new MapEntry[7];
		count = 0;
		maxCount = table.length - table.length/3;
		DUMMY = new MapEntry<>(null, null);
	}
	
	/**
	 * Returns the number of key-value mappings in this map.
	 * 
	 * @return    the number of key-value mappings in this map.
	 */
	public int size()
	{
		return count;
	}
	
	/**
	 * Returns true if this map contains no key-value mappings.
	 * 
	 * @return    true if this map contains no key-value mappings
	 */
	public boolean isEmpty()
	{
		return count == 0;
	}
	
	/**
	 * Returns true if this map contains a mapping for the specified key.
	 * 
	 * @param key   The key whose presence in this map is to be tested
	 * @return      true if this map contains a mapping for the specified key
	 */
	public boolean containsKey(K key)
	{
		int slot = findSlot(key, false);
		return slot >= 0;
	}
	
	/**
	 * Returns the value to which the specified key is mapped,
	 * or null if this map contains no mapping for the key.
	 * 
	 * @param key   the key whose associated value is to be returned
	 * @return      the value to which the specified key is mapped,
	 *              or null if this map contains no mapping for the key
	 */
	public V getValue(K key)
	{
		int slot = findSlot(key, false);
		
		if (slot < 0) 
		{
			return null;
		}
		MapEntry<K, V> e = table[slot];
		return e.getValue();
	}
	
	/**
	 * Depending on whether it is for adding a new entry or not,
	 * returns a slot number to indicate where you can add a new
	 * entry with the specified key or where an entry with the 
	 * specified key is found.
	 * 
	 * @param key      key which is to be searched in the map
	 * @param forAdd   if true, indicates the key to be searched
	 * 				   does not exist in the map and the search
	 *                 is to find an empty slot to add a new entry
	 *                 with the specified key
	 * @return
	 */
	private int findSlot(K key, boolean forAdd)
	{
		int slot = hash1(key);
		int step = hash2(key);
		int M = table.length;
		
		if (forAdd)
		{
			//TODO: is it possible that this will loop forever?
			while (true)
			{
				MapEntry<K, V> e = table[slot];
				if(e == null || e == DUMMY) //we can override DUMMY
				{ 
					return slot;
				}
				else
				{
					slot = (slot + step) % M;
				}
			}
		}
		else
		{
			//TODO: is it possible that this will loop forever?
			while (table[slot] != null)
			{
				MapEntry<K, V> e = table[slot];
				if(key.equals(e.getKey()))
				{
					return slot;
				}
				else
				{
					slot = (slot + step) % M;
				}
			}
			
		}
		
		return -1;
	}
	
	private int hash1(K key)
	{
		return Math.abs(key.hashCode()) % table.length;
	}
	
	private int hash2(K key)
	{
		return 1 + Math.abs(key.hashCode()) % (table.length - 2);
	}
	
	/**
	 * Expands a hash table and rehashes every existing table entry.
	 */
	@SuppressWarnings("unchecked")
	private void rehash()
	{
		int M = table.length;
		MapEntry<K, V>[] origTable = table; // save original
		
		//new table
		table = new MapEntry[2*M + 1];
		count = 0;
		maxCount = table.length - table.length/3;
		
		for (MapEntry<K, V> oe : origTable)
		{
			MapEntry<K, V> e = oe;
			if (e != null && e != DUMMY) // No need to rehash dummy
			{
				int slot = findSlot(e.getKey(), true);
				table[slot] = e;
				count++;
			}
		}
	}
	
	/**
	 * Associates the specified value with the specified key in this map.
	 * If the map previous contained a mapping for the key, the old value
	 * is replaced.
	 * 
	 * @param key     key with which the specified value is to be associated
	 * @param value   value to be associated with the specified key
	 * @return        the previous value associated with key, or null if there
	 *                was no mapping for key. (A null return can also indicate
	 *                that the map previous associated null with key.)
	 */
	public V add(K key, V value)
	{
		int slot = findSlot(key, false);    // check if key already exists
		V oldVal = null;
		
		if (slot >= 0)
		{
			MapEntry<K, V> e = table[slot];
			oldVal = e.setValue(value);
		}
		else
		{
			slot = findSlot(key, true);    // find empty slot for adding
			table[slot] = new MapEntry<>(key, value);
			count++;
			if (count >= maxCount)
			{
				rehash();
			}
			
		}
		
		return oldVal;
	}
	
	/**
	 * Removes the mapping for the specified key from this map if present.
	 * 
	 * @param key    key whose mapping is to be removed from the map
	 * @return       the previous value associated with key, or null
	 *               if there was no mapping for key
	 */
	public V remove(K key)
	{
		//TODO: add your code here
		V oldVal;
		int slot = findSlot(key, false);    // check if it already exists
		// sets the slot in the map to DUMMY value if it exists
		if (slot >= 0)
		{
			oldVal = table[slot].getValue();
			table[slot] = DUMMY;
			count--;
		}
		else
		{
			oldVal = null;
		}
		
		
		return oldVal;
	}
}
