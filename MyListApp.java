//Part of the LinkedList Project
//Cameron Gonzalez
//Help from: Stackoverflow and Dr. Hong

import java.util.*;
import java.io.*;

public class MyListApp
{
   public static void main(String[] args) throws IOException
   {
      System.out.println("Original list content:");
      if(args.length != 0)
      {
    	 //creating a doubly linked list called myList
         MyLinkedList<Double> myList = new MyLinkedList<Double>();
         //assigning variables for calculations
         double temp = 0.0;
         double sum = 0.0;
         double sum2 = 0.0;
         double average, variance, stdDev;
         double difference;
         
         //creating new file from first argument in command line
         File file = new File(args[0]);
         Scanner filename = new Scanner(file);
         
         //translating .txt file to a doubly linked list full of nodes containing doubles
         ListIterator<Double> tempListItr = null;
         tempListItr = myList.listIterator(true);
		 while(filename.hasNextDouble())
         {
            temp = filename.nextDouble();
            tempListItr.add(temp);
            sum += temp;
         }
         
		 //calculating the average of those doubles
         average = sum/myList.size();
         
         //closing the file. translation completed.
         filename.close();
         
         //creating new list iterator to print contents
         tempListItr = myList.listIterator(true);
         
         //printing out the list content of the doubly linked list
         while(tempListItr.hasNext())
     	 {
	         for(int i = 0; i < myList.size(); i++)
	         {
	        	 if(i < 10)
	        	 {
	        		 System.out.printf("  %d:  %.2f\n", i, tempListItr.next());
	        	 }
	             else if(i >= 10)
	             {
	                 System.out.printf(" %d:  %.2f\n", i, tempListItr.next());
	             }
	         }
         }
         
         //printing list size and average of the list contents
         System.out.println("The list size is " + myList.size());
         System.out.printf("The average is %.2f\n", average);
         
         //removing at index 0 and 2 (check to see if you have to call next before removing in this case)
         tempListItr = myList.listIterator(true);
         int i = 0;
         double x = 0;
         double y;
         //iterating to the index before the third node
         while(i++ < 3)
         {
        	 x = tempListItr.next();
         }
         //removes the third node
         tempListItr.remove();
         tempListItr = myList.listIterator(true);
         //removes the first node
         y = tempListItr.next();
         tempListItr.remove();
         
         //adding a new instance to the list at index 0
         tempListItr = myList.listIterator(true);
         tempListItr.add((x+y)/2.0);

         System.out.println("New list content:");
         
         //resetting sum
         sum = 0.0;
         sum2 = 0.0;
         
         //printing the new list content using a for loop
         tempListItr = myList.listIterator(true);
         int j = 0;
	     while(tempListItr.hasNext())
	     {
	    	 temp = tempListItr.next();
	    	 System.out.printf("%3d:  %.2f\n", j, temp);
	     		
	    	 sum += temp;
	     	 if(!tempListItr.hasNext())
	     	 {
	     		 break;
	     	 }
	     	 ++j;
	     }
         
         //calculating average of new list content
         average = sum/myList.size();
         
         //calculating difference to be used in sum2 calculation
         tempListItr = myList.listIterator(true);
     	 while(tempListItr.hasNext())
     	 {
     		 difference = tempListItr.next() - average;
     		 //calculating sum of differences to be used in variance calculation
     		 sum2 += difference*difference;
     	 }
         
         //calculating variance
         variance = sum2 / myList.size();
         stdDev = Math.sqrt(variance);
         
         System.out.println("The list size is " + myList.size());
         System.out.printf("The standard deviation is %.2f", stdDev);
      }
      else
      {
    	  System.out.println("Not enough command line arguments");
      }
   }
}