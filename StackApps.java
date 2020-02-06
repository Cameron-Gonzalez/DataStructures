//Part of the Stack Project
//By: Cameron Gonzalez
//Helped by: Dr. Hong

import java.util.Stack;
import java.util.Scanner;

public class StackApps 
{
	public static String int2Str(int aI)
	{
		Stack<Integer> myStack = new Stack<Integer>();
		if(aI == 0)
		{
			return "0";
		}
		String str = "";
		if(aI < 0)
		{
			str = "-";
			aI = -aI;
		}
		while(aI > 0)
		{
			int remainder = aI%10;
			myStack.push(remainder);
			aI = aI/10;
		}
		
		while(!myStack.isEmpty())
		{
			str += (char)(myStack.pop() + '0');//ask why this works
		}
		return str;
	}
	public static double evalPostfix(String ex)
	{
		Stack<Double> myStack = new Stack<Double>();
		String nextToken;
		final String OPERATOR_PATTERN = "[-+*/]";
		final String INTEGER_PATTERN = "\\d+"; //integer operand pattern
		final String REAL_PATTERN = "\\d+\\.\\d*"; //real number operand pattern
		final String OPERAND_PATTERN = REAL_PATTERN + "|" + INTEGER_PATTERN;
		final String TOKEN_PATTERN = OPERAND_PATTERN + "|" + OPERATOR_PATTERN;
		
		ex.replaceAll("\\s+", "");
		Scanner sc = new Scanner(ex);
		while((nextToken = sc.findInLine(TOKEN_PATTERN)) != null)
		{
			if((nextToken.equals("-") || nextToken.equals("+") || nextToken.equals("*") || nextToken.equals("/")))
			{
				double RHS = myStack.pop();
				//if there are no extra operators (normal execution)
				if(!myStack.isEmpty())
				{
					double LHS = myStack.pop();
					double result = 0;
					if(nextToken.equals("-"))
					{
						result = LHS - RHS;
					}
					else if(nextToken.equals("+"))
					{
						result = LHS + RHS;
					}
					else if(nextToken.equals("*"))
					{
						result = LHS * RHS;
					}
					else
					{
						result = LHS / RHS;
					}
					myStack.push(result);
				}
				//if the stack is empty (in other words, extra operators)
				else 
				{
					sc.close();
					return Double.NaN;
				}
			}
			//pushing a operand into the stack
			else
			{
				myStack.push(Double.parseDouble(nextToken));
			}
		}
		sc.close();
		
		//if there are extra operands
		if(myStack.size() != 1)
		{
			return Double.NaN;
		}
		//normal execution
		else
		{
			double answer = myStack.pop();
			return answer;
		}
	}
}
