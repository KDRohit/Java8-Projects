package com.ig.streamapi;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Program2 
{
	Predicate<Float> moreToOneLack = x->x>100000;
	Function<Integer,Float> DollarToRupee = x->x*88.16f;
	Function<Integer,String> appendDollarSymbol = x->"\u0024"+x;
	Function<Float,String> appendRupeeSymbol = x->"\u20B9"+x;
	
	void meth1()
	{
		List<Integer> dollars = new ArrayList<Integer>();
		
		dollars.add(2000);
		dollars.add(1200);
		dollars.add(999);
		dollars.add(111);
		dollars.add(888);
		dollars.add(777);
		dollars.add(666);
		dollars.add(555);
		dollars.add(222);
		dollars.add(333);
		dollars.add(444);
		
		System.out.println("Dollars:-"+dollars.stream().map(appendDollarSymbol).toList());
		System.out.println("Rupees:-"+dollars.stream().map(DollarToRupee).map(appendRupeeSymbol).toList());
		
		System.out.println("-----------------------------------------------------------------------");
		
		System.out.println("Dollars(sorted):"+dollars.stream().sorted().map(appendDollarSymbol).toList());
		System.out.println("Rupees(sorted):"+dollars.stream().sorted().map(DollarToRupee).map(appendRupeeSymbol).toList());
		
		System.out.println("-----------------------------------------------------------------------");
		
		Long NumberMoreThanOneLackRupee  = dollars.stream().map(DollarToRupee).filter(moreToOneLack).count();
		System.out.println("Number More Than One Lack Rupee (Dollars): "+NumberMoreThanOneLackRupee);
		
	}
	
	void meth2()
	{
		List<String> names = new ArrayList<String>();
		names.add("rohit");
		names.add("amit");
		names.add("alok");
		names.add("mom");
		names.add("dad");
		
		System.out.println(names.stream().map(String::toUpperCase).toList());
		
		Predicate<String> filterEndWithT = name->name.endsWith("t");
		
		System.out.println(names.stream().filter(filterEndWithT).toList());
		
	}
	
	
	public static void main(String[] args) 
	{
		Program2 p2 = new Program2();
		//p2.meth1();
		p2.meth2();
	}
}
