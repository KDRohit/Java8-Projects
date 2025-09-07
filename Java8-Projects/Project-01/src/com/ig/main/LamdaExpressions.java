package com.ig.main;

import com.ig.interfaces.IDoAnythingWithOneParam;
import com.ig.interfaces.IDoAnythingWithTwoParam;

interface DoAnythingWithOneParamString 
{
	String message(String name);
}

public class LamdaExpressions 
{
	void FindSumUsingTwoParamInterface(int a,int b)
	{	
		// interface implemented using inner class.
		IDoAnythingWithTwoParam SumFinder_With_InnerClass = new IDoAnythingWithTwoParam()
		{
			@Override
			public int DoAnythingWithTwoParam(int a, int b) {
				// TODO Auto-generated method stub
				return a+b;
			}
		};
		System.out.println("sum using inner class = "+SumFinder_With_InnerClass.DoAnythingWithTwoParam(a, b));
		
		
		// interface implemented using lambda expression.
		
		IDoAnythingWithTwoParam  SumFinder_With_Lambda = (x , y)-> x+y ;
		
		System.out.println("sum using lamda expression = "+SumFinder_With_Lambda.DoAnythingWithTwoParam(a, b));		
	}
	
	void FindSquareUsingOneParamInterface(int a)
	{
		IDoAnythingWithOneParam SquareFinder_With_Lambda = x->x*x;
		System.out.println("SquareFinder_With_Lambda = "+SquareFinder_With_Lambda.DoAnythingWithOneParam(a));
	}
	
	void printHelloMessage(String name)
	{
		// note:- 
		// 1)- param type not required => (int[incorrect] a)->a+a;
		// 2)- single line does not need return statement a->a*a.
		// 3)- multi line statement need {},return.
		
		DoAnythingWithOneParamString MessageGenerator = str->"Hello "+str+" !";
		DoAnythingWithOneParamString MessageGenerator1 = str->{return "Hello "+str+" !";};
		
		
		System.out.println(MessageGenerator.message(name));
	}
	
	int CompareTwoNumber(int a,int b,boolean isMin)
	{
		IDoAnythingWithTwoParam finder = (x,y)->
		{
			if(isMin)
			{
				if(y<x)
				{
					return y;
				}
				else if(x<y)
				{
					return x;
				}
			}
			else
			{
				if(y>x)
				{
					return y;
				}
				else if(x>y)
				{
					return x;
				}
			}
			return 0;
		};
		return finder.DoAnythingWithTwoParam(a, b);
	}
	
	public static void main(String[] args) 
	{
		LamdaExpressions p =  new LamdaExpressions();
		p.FindSquareUsingOneParamInterface(5);
		p.FindSumUsingTwoParamInterface(5,10);
		p.printHelloMessage("Rohit");
		int res = p.CompareTwoNumber(7,8,false);
		System.out.println("result:-"+res);
	}
	
}
