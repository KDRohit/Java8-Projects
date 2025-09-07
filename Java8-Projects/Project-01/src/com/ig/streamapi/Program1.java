package com.ig.streamapi;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

interface Supplier1<T>
{
	public T get();
}

interface Consumer1<T>
{
	public void accept(T t);
}

interface Function1<T1,T2>
{
	public T2 apply(T1 t);
}
interface Predicate1<T>
{
	public boolean test(T t);
}

interface UnaryOperator1<T>
{
	public T apply(T t);
}

interface BinaryOperator1<T>
{
	public T apply(T t1,T t2);
}

public class Program1 
{
	public String message = "Hello world!!!" ;
	
	void testSupplier()
	{
		Supplier<String> supplier = ()->message;
		String message = supplier.get();
		System.out.println(message);
	}
	
	void testConsumer()
	{
		Consumer<String> consumer = str->System.out.println(str);
		consumer.accept(message);
	}
	
	void testFunction()
	{
		Function<Integer,String> function = number->message+number;
		Function1<Integer,Integer> square = number->number*number;
		System.out.println(function.apply(10));
		System.out.println(square.apply(5));
	}
	
	void testPredicate()
	{
		Predicate<Integer> isEven = num->num%2==0?true:false;
		System.out.println(isEven.test(10));
		System.out.println(isEven.test(13));
	}
	
	void testUnaryOperator()
	{
		UnaryOperator<Integer> sqaureGenerator = number->number*number;
		System.out.println(sqaureGenerator.apply(12));
		
		UnaryOperator1<Integer> cubeGenerator = number->number*number*number;
		System.out.println(cubeGenerator.apply(12));
	}
	
	void testBinaryOperator()
	{
		BinaryOperator<Integer> sumFinder = (num1,num2)->num1+num2;
		System.out.println(sumFinder.apply(12, 12));
		
		BinaryOperator1<Integer> customSumFinder = (num1,num2)->num1+num2;
		System.out.println(customSumFinder.apply(13, 113));
	}
	
	public void showMessage(String str)
	{
		System.out.println(str);
	}
	
	public static void showStaticMessage(String str)
	{
		System.out.println(str);
	}
	
	void testMethodReferencing(String msg)
	{
		System.out.println("Program1.testMethodReferencing()");
		Consumer<String> con1 = this::showMessage;
		con1.accept(msg);
	}
	
	void testStaticMethodReferencing(String msg)
	{
		System.out.println("Program1.testStaticMethodReferencing()");
		Consumer<String> con1 = Program1::showStaticMessage;
		con1.accept(msg);
	}
	
	public static void main(String[] args) 
	{
		Program1 p1 = new Program1();
		p1.testConsumer();
		p1.testSupplier();
		p1.testFunction();
		p1.testPredicate();
		p1.testUnaryOperator();
		p1.testBinaryOperator();
		p1.testMethodReferencing("testing method refrencing");
	}
}
