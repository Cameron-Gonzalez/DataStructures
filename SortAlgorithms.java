import java.util.Comparator;

//TODO: add necessary imports

/**
 * This class provides a method that does heapsort.
 * 
 * @author Cameron Gonzalez
 *
 */
public class SortAlgorithms 
{
	/**
	 * A sort method that does heapsort
	 * @param records   the array of records to be sorted
	 * @param c         the comparator to determine the order of the array.
	 */
	public static <T> void heapsort(T[] records, Comparator<? super T> c)
	{
		// turn the input array of records into a heap in-place
		// ... using siftUp
		for (int i = 1; i < records.length; i++)
		{
			siftUp(records, i, c);
		}
		printRecords("\nResulting heap:", records);    // for debugging
		
		// sort the array (heap) in place
		for (int k = records.length-1; k > 0; k--)
		{
			T t = records[k];
			records[k] = records[0];
			records[0] = t;
			siftDown(records, k, c);
		}
		printRecords("Sorted array:", records); // for debugging
	}
	
	// You may add more sorting methods here. Try it!
	// For example:
	//          bubble sort
	//          selection sort
	//          insertion sort
	//          tim sort
	
	//This method is used for debugging purpose.
	private static <T> void printRecords(String hdr, T[] records)
	{
		System.out.println(hdr);
		for (int i = 0; i < records.length; i++)
		{
			System.out.println("\t"+records[i]);
		}
		System.out.println();
	}
	
	/**
	 * This method sifts a record up for the building of a heap in place.
	 * @param records    the array of records
	 * @param k          indicates the i'th record is to be sifted up
	 * @param c          the comparator to determine the order of the array.
	 */
	private static <T> void siftUp(T[] records, int i, Comparator<? super T> c)
	{
		int parentIdx = (i-1) / 2;
		T parentNode = records[parentIdx];
		T childNode = records[i];
		if(c.compare(parentNode, childNode) < 0)
		{
			records[parentIdx] = childNode;
			records[i] = parentNode;
			i = parentIdx; 
			siftUp(records, i, c);
		}
	}
	
	/**
	 * This method sifts the root record down into a heap.
	 * @param records    the array of records
	 * @param k          identifies the index of the record already swapped
	 * 					 to the root. The new k'th record is thus no longer
	 * 					 part of the heap.
	 * @param c          the comparator to determine the order of the array.
	 */
	private static <T> void siftDown(T[] records, int k, Comparator<? super T> c)
	{
		//k is the limit
		int i = 0;
		
		
		while(i < k-1)
		{
			int leftChildIdx = (2*i) + 1;
			int rightChildIdx = (2*i) + 2;
			int largest = i;
			
			// left child larger than root
			if (leftChildIdx < k && c.compare(records[leftChildIdx], records[largest]) > 0)
			{
				largest = leftChildIdx;
			}
			
			// right child larger than largest
			if (rightChildIdx < k && c.compare(records[rightChildIdx], records[largest]) > 0)
			{
				largest = rightChildIdx;
			}
			
			// largest is not root
			if (largest != i)
			{
				T swap = records[i];
				records[i] = records[largest];
				records[largest] = swap;				
			}
			else
				break;
			
			i = largest;
		}
	}
}
