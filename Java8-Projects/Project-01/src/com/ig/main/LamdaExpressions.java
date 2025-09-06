package com.ig.main;

import com.ig.interfaces.IDoAnythingWithOneParam;
import com.ig.interfaces.IDoAnythingWithTwoParam;

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
	
	public static void main(String[] args) 
	{
		LamdaExpressions p =  new LamdaExpressions();
		p.FindSquareUsingOneParamInterface(5);
		p.FindSumUsingTwoParamInterface(5,10);
	}
	
}
