package com.ig.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class Program2 
{
	private String dbUrl = "jdbc:mysql://localhost/ingenuity";
	private String dbuser = "root";
	private String dbpass = "1234";
	
	Scanner sc = new Scanner(System.in);
	
	
	void meth1()
	{
		System.out.println("");
		try
		{
			RowSetFactory rsf =  RowSetProvider.newFactory();
		    JdbcRowSet jrs = rsf.createJdbcRowSet();
		    
		    jrs.setUrl(dbUrl);
		    jrs.setUsername(dbuser);
		    jrs.setPassword(dbpass);
		    
		    jrs.setCommand("select * from concurrentable");
		    jrs.execute();
		    
		    System.out.println("waiting for inputs...");
		    String s = sc.nextLine();
		    System.out.println(s);
		    
		    int updateId = 2;
		    String updateData = "line 2 updated";
		    
		    while(jrs.next())
		    {
		    	int id =  jrs.getInt("id");

		    	if(id == updateId)
		    	{
		    		jrs.updateString("description",updateData);
		    		jrs.updateRow();
		    	}
		    	
		    	int cols = jrs.getMetaData().getColumnCount();
			    for(int i=1;i<=cols;i++)
			    {
			    	System.out.print(jrs.getObject(i));
			    	System.out.print(" ");
			    }
			    System.out.println();
		    }
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	void meth2()
	{
		System.out.println("Implementing CachedRowSet interface\n");
		try
		{
			RowSetFactory rsf = RowSetProvider.newFactory();
			CachedRowSet crs = rsf.createCachedRowSet();
			
			// here we are configuring connecting....
			crs.setUrl(dbUrl);
			crs.setUsername(dbuser);
			crs.setPassword(dbpass);
			
			// here we are setting command...
			crs.setCommand("select * from concurrentable");
			
			// connection ========> start
			crs.execute();
			// connection ========> ends
			
			System.out.println("waiting for inputs...");
			String s = sc.nextLine();
			System.out.println(s);
			 
			int updateId = 2;
			String updateData = "line 2";
			    
			while(crs.next())
			{
				int id =  crs.getInt("id");
				if(id == updateId)
		    	{
		    		crs.updateString("description",updateData);
		    		crs.updateRow();
		    	}
				
				int cols = crs.getMetaData().getColumnCount();
				for(int i=1;i<=cols;i++)
				{
					System.out.print(crs.getObject(i));
					System.out.print(" ");
				}
				System.out.println();
			}
			
			crs.acceptChanges();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) 
	{
		Program2 pro2 = new Program2();
		pro2.meth1();
		//pro2.meth2();
	}
	
}
