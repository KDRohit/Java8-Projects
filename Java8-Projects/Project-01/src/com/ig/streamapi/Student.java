package com.ig.streamapi;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Student
{
	List<StudentMetaInfo> students = new ArrayList<StudentMetaInfo>();
	
	class StudentMetaInfo implements Comparable<StudentMetaInfo>
	{
		public int id;
		public String name;
		
		public StudentMetaInfo(int id, String name)
		{
			this.id=id;
			this.name=name;
			
		}
		
		public String toString()
		{
			return "[id:"+id+",name:"+name+"]";
		}
		
		@Override
		public int compareTo(StudentMetaInfo other)
		{
			return other.id-this.id;
		}
	}
	
	public void meth1() 
	{
		StudentMetaInfo s1 = new StudentMetaInfo(1, "rohit");
		StudentMetaInfo s2 = new StudentMetaInfo(2, "amit");
		StudentMetaInfo s3 = new StudentMetaInfo(3, "alok");
		
		Consumer<StudentMetaInfo> StudentMetaInfoPrinter = s->System.out.println(s);
		
		students.add(s1);
		students.add(s2);
		students.add(s3);
		
		students.forEach(StudentMetaInfoPrinter);
		students.stream().sorted().toList().forEach(StudentMetaInfoPrinter);
		
	
	}
	
	public static void main(String[] args) 
	{
		Student pro = new Student();
		pro.meth1();
	}
	
}
