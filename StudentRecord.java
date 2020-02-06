//Part of the SortSearch Project
//Cameron Gonzalez

import java.util.Comparator;

public class StudentRecord implements Comparable<StudentRecord>
{
	private int grade;
	private String iD;
	private char letterGrade;
	
	public StudentRecord()
	{
		grade = 0;
		iD = "";
		letterGrade = ' ';
	}
	
	public StudentRecord(String tempID, int tempGrade)
	{
		iD = tempID;
		grade = tempGrade;
	}
	
	public String getID()
	{
		return iD;
	}
	
	public int getGrade()
	{
		return grade;
	}
	
	public char getLetterGrade()
	{
		if(getGrade() >= 90)
		{
			letterGrade = 'A';
		}
		else if(getGrade() >= 80)
		{
			letterGrade = 'B';
		}
		else if(getGrade() >= 70)
		{
			letterGrade = 'C';
		}
		else if(getGrade() >= 60)
		{
			letterGrade = 'D';
		}
		else
		{
			letterGrade = 'F';
		}
		return letterGrade;
	}
	
	public int compareTo(StudentRecord o)
	{
		return getID().compareTo(o.getID());
	}
	
	public static class DescendingGradeComparator implements Comparator<StudentRecord>
	{
		public int compare(StudentRecord o1, StudentRecord o2)
		{
			return o2.grade - o1.grade;
		}
	}
}
