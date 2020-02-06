//Part of the SortSearch Project
//Cameron Gonzalez

import java.util.*;
import java.io.*;

public class SortSearchApp 
{
	//creates a customized binarySearch which searches the entire list for a specific student ID
	public static StudentRecord binarySearch(List<StudentRecord> records, String aID)
	{
		int low = 0;
		int high = records.size() - 1; //remember - 1!!!
		int mid;
		boolean search = false;
		StudentRecord temp = new StudentRecord();
		while(low <= high)
		{
			mid = (high + low) / 2;
			if((records.get(mid).getID().equals(aID)))
			{
				search = true;
				temp = records.get(mid);
				break;
			}
			else if(aID.compareTo(records.get(mid).getID()) < 0)
			{
				high = mid - 1;
			}
			else
				low = mid + 1;
		}
		if(search == false)
			return null;
		else
			return temp;
	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
		//must have at least an input and output file
		if (args.length < 2)
		{
			System.err.println("Missing command line arguments");
			return;
		}
		
		File infile = new File (args[0]);
		File outfile = new File (args[1]);
		
		//read student records into a list
		List<StudentRecord> records = new ArrayList<StudentRecord>();
		Scanner kb = new Scanner(infile);

		//adding student records from file into an ArrayList named records
		String tempStr;
		StudentRecord tempRec;
		while(kb.hasNext())
		{
			tempStr = kb.nextLine();
			String[] tokens = tempStr.split("\\s+");
			tempRec = new StudentRecord(tokens[0], Integer.parseInt(tokens[1]));
			records.add(tempRec);
		}
		kb.close();
		
		//write it onto a new file
		PrintWriter pw = new PrintWriter(outfile);
		
		//sort by Id and  output result to file
		Collections.sort(records);
		pw.println("Student records in alphabetic order:");
		pw.println();
		for(StudentRecord aRec : records)
		{
			pw.println(String.format("    %-4s %4d", aRec.getID(), aRec.getGrade()));
		}
		pw.println();
		
		//do binary search for every id provided on the command line
		for(int i = 2; i < args.length; i++)
		{
			String aID = args[i];
			StudentRecord aRec;
			aRec = binarySearch(records, aID);
			if(aRec == null)
			{
				System.out.println(String.format("%-4s Not Found", aID));
			}
			else
			{
				System.out.println(String.format("%-4s %4d", aRec.getID(), aRec.getGrade()));
			}
		}
		
		//sort by grade (descending) and output result to file (appended)
		Collections.sort(records, new StudentRecord.DescendingGradeComparator());
		pw.println("Student records by letter grades:");
		pw.println();
		for(StudentRecord aRec : records)
		{
			pw.println(String.format("    %-4s %4d %2c", aRec.getID(), aRec.getGrade(), aRec.getLetterGrade()));
		}
		pw.close();
	}

}
