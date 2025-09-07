package com.ig.streamapi;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

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
	
	
	public static void main(String[] args) 
	{
		Program1 p1 = new Program1();
		p1.testConsumer();
		p1.testSupplier();
		p1.testFunction();
		p1.testPredicate();
	}
}
