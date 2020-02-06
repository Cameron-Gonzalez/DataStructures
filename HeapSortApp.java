import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;

//TODO: necessary imports

/**
 * This Java application reads records from an input file and sort them using heapsort.
 * 
 * @author Cameron Gonzalez
 *
 */
public class HeapSortApp 
{
	/**
	 * Private student record class.
	 * 
	 * @author some one.
	 * 
	 */
	private static class StudentRecord
	{
		private int id;
		private String name;
		
		/**
		 * Constructor.
		 * @param aID
		 * @param aName
		 */
		public StudentRecord(int aID, String aName)
		{
			id = aID;
			name = aName;
		}
		
		@Override
		public String toString()
		{
			return "(" + id + ", " + name + ")";
		}
	}
	
	private static class StudentRecordComparator implements Comparator<StudentRecord>
	{
		@Override
		public int compare(StudentRecord o1, StudentRecord o2)
		{
			return o1.id - o2.id;
		}
	}
	
	/**
	 * Main method that reads student data records from an input data file
	 * and then sorts the records.
	 * 
	 * Input file format (containing unsorted records):
	 * 
	 *     33                    <-- number of records
	 *     1   John Smith        <-- Each line contains exactly one student record (id and name)
	 *     33       Jane Fonda   <-- There are any number of whitespace in between id and name
	 *     25    Vincent Foxx    <-- So use "smart" methods that automatically skip whitespace
	 *     ... ...
	 *     
	 * @param args The command line arguments are in this array.
	 *             The input file name is given by the first argument.
	 *             
	 */
	public static void main(String[] args)
	{
		StudentRecord[] records = null;
		
		try
		{
			records = readRecordsFromFile(args[0]);
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Input file not found.");
		}
		printRecords("Records read from input file:", records);
		
		// sort the records using in place heapsort
		SortAlgorithms.heapsort(records, new StudentRecordComparator());
		printRecords("Sorted records:", records);
	}
	
	private static StudentRecord[] readRecordsFromFile(String inFileName) throws FileNotFoundException
	{
		Scanner sc = new Scanner(new File(inFileName));
		int nRecords;    // number of records
		int studentID;
		String studentName;
		StudentRecord[] records;
		
		// read number of records using scanner
		nRecords = sc.nextInt();
		records = new StudentRecord[nRecords];
		
		// read each record using scanner
		for (int i = 0; i < nRecords; i++)
		{
			// read id
			studentID = sc.nextInt();
			
			// Note a name can contain multiple words -- which method do you use??
			// If you read a line, you need to trim the leading and trailing spaces
			studentName = sc.nextLine().trim();
			
			//System.out.println(studentID + studentName);
			
			records[i] = new StudentRecord(studentID, studentName);
		}
		
		// finish using scanner
		sc.close();
		
		return records;
	}
	
	private static void printRecords(String hdr, StudentRecord[] recs)
	{
		int i = 0;
		System.out.print(hdr + "\n\t[ ");
		for (StudentRecord r: recs)
		{
			System.out.print(r + " ");
			i++;
			if(3 == i)
			{
				System.out.println();
				System.out.print("\t ");
				i = 0;
			}
		}
		System.out.println("]");
	}
}
